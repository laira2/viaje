package com.viaje.viaje.controller;


import com.viaje.viaje.dto.PointTransactionDTO;
import com.viaje.viaje.model.Users;
import com.viaje.viaje.service.PointTransactionService;
import com.viaje.viaje.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Controller
public class WidgetController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PointTransactionService pointTransactionService;
    private final UserService userService;

    @Value("${payment.toss.secret-key}")
    private String tossSecretKey;

    public WidgetController(PointTransactionService pointTransactionService, UserService userService) {
        this.pointTransactionService = pointTransactionService;
        this.userService = userService;
    }

    @PostMapping(value = "/confirm")
    public ResponseEntity<JSONObject> confirmPayment(@RequestBody String jsonBody) throws Exception {

        JSONParser parser = new JSONParser();
        String orderId;
        String amount;
        String paymentKey;
        try {
            // 클라이언트에서 받은 JSON 요청 바디입니다.
            JSONObject requestData = (JSONObject) parser.parse(jsonBody);
            paymentKey = (String) requestData.get("paymentKey");
            orderId = (String) requestData.get("orderId");
            amount = (String) requestData.get("amount");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ;
        JSONObject obj = new JSONObject();
        obj.put("orderId", orderId);
        obj.put("amount", amount);
        obj.put("paymentKey", paymentKey);

        logger.info("위젯 컨드롤러", obj.toString());

        // 토스페이먼츠 API는 시크릿 키를 사용자 ID로 사용하고, 비밀번호는 사용하지 않습니다.
        // 비밀번호가 없다는 것을 알리기 위해 시크릿 키 뒤에 콜론을 추가합니다.
        String widgetSecretKey = tossSecretKey;
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((widgetSecretKey + ":").getBytes(StandardCharsets.UTF_8));
        String authorizations = "Basic " + new String(encodedBytes);

        // 결제를 승인하면 결제수단에서 금액이 차감돼요.
        URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", authorizations);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));

        int code = connection.getResponseCode();
        boolean isSuccess = code == 200;

        InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();

        // 결제 성공 및 실패 비즈니스 로직을 구현하세요.
        Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        responseStream.close();


        logger.info("Response Code: {}", code);
        logger.info("Response Body: {}", jsonObject.toString());
        return ResponseEntity.status(code).body(jsonObject);
    }
    @GetMapping("/toss_success")
    public String tossSuccess(@RequestParam("paymentKey") String paymentKey,
                              @RequestParam("orderId") String orderId,
                              @RequestParam("amount") String amount,
                              Model model,
                              HttpServletRequest request) {

        // 받은 파라미터들을 모델에 추가
        model.addAttribute("paymentKey", paymentKey);
        model.addAttribute("orderId", orderId);
        model.addAttribute("amount", amount);

        // 세션에서 현재 로그인한 사용자 정보 가져오기
        Users user = (Users) request.getSession().getAttribute("user_model");
        if (user == null) {
            // 사용자 정보가 없으면 에러 처리
            return "error";
        }

        // 결제 금액을 Integer로 변환
        Integer chargeAmount = Integer.parseInt(amount);

        // 충전할 포인트 계산 (예: 1원 = 1포인트)
        Integer chargePoint = chargeAmount;

        System.out.print("충전된 포인트 : "+chargeAmount);
        try {
            // 포인트 충전 및 거래 기록 저장
            PointTransactionDTO transactionDTO = pointTransactionService.chargePoints(user.getUserId(), chargeAmount, chargePoint);

            // 사용자 포인트 업데이트
            userService.updateUserPoints(user.getUserId(), chargePoint);

            // 성공 메시지 추가
            model.addAttribute("message", "포인트 충전이 완료되었습니다.");
            model.addAttribute("chargedPoints", chargePoint);

            System.out.print(user.getPoint());

        } catch (Exception e) {
            // 에러 처리
            model.addAttribute("error", "포인트 충전 중 오류가 발생했습니다: " + e.getMessage());
            return "error";
        }

        return "toss_success";  // toss_success.html 템플릿을 렌더링
    }
}