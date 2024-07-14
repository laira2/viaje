//package com.viaje.viaje.service;
//
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.viaje.viaje.dto.TossPaymentsDTO;
//import com.viaje.viaje.model.TossPayments;
//import com.viaje.viaje.repository.TossPaymentsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Base64;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class TossPaymentsService {
//    private final TossPaymentsRepository tossPaymentsRepository;
//    private final RestTemplate restTemplate;
//    private final ObjectMapper objectMapper;
//
//    @Value("${payment.toss.secret-key}")
//    private String tossSecretKey;
//
//    @Autowired
//    public TossPaymentsService(TossPaymentsRepository tossPaymentsRepository) {
//        this.tossPaymentsRepository = tossPaymentsRepository;
//        this.restTemplate = new RestTemplate();
//        this.objectMapper = new ObjectMapper();
//    }
//
//
//    public TossPaymentsDTO savePayments(TossPaymentsDTO paymentsDTO){
//        TossPayments payments= TossPayments.fromDTO(paymentsDTO);
//        payments = tossPaymentsRepository.save(payments);
//        return payments.toDTO();
//    }
//
//    public TossPaymentsDTO getPaymentByPaymentKey(String paymentKey) {
//        TossPayments payment = tossPaymentsRepository.findByPaymentKey(paymentKey);
//        return payment.toDTO();
//    }
//
//    public JsonNode confirmPayment(String paymentKey, String orderId, Long amount) throws Exception {
//        HttpHeaders headers = new HttpHeaders();
//        String encodedKey = Base64.getEncoder().encodeToString((tossSecretKey + ":").getBytes(StandardCharsets.UTF_8));
//        headers.set("Authorization", "Basic " + encodedKey);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        System.out.println("토스페이먼츠 서비스"+tossSecretKey);
//
//        Map<String, String> payloadMap = new HashMap<>();
//        payloadMap.put("orderId", orderId);
//        payloadMap.put("amount", String.valueOf(amount));
//
//        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(payloadMap), headers);
//
//        ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity(
//                "https://api.tosspayments.com/v2/payments/" + paymentKey, request, JsonNode.class);
//
//        return responseEntity.getBody();
//    }
//
//
//}