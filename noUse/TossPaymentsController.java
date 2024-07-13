//package com.viaje.viaje.controller;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.viaje.viaje.service.TossPaymentsService;
//import com.viaje.viaje.dto.TossPaymentsDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Map;
//
//@Controller
//public class TossPaymentsController {
//
//    private final TossPaymentsService tossPaymentsService;
//
//    @Autowired
//    public TossPaymentsController(TossPaymentsService tossPaymentsService){
//        this.tossPaymentsService = tossPaymentsService;
//    }
//
//    @PostMapping("/confirm")
//    public ResponseEntity<?> confirmPayment(@RequestBody Map<String, String> payload) {
//        try {
//            String paymentKey = payload.get("paymentKey");
//            String orderId = payload.get("orderId");
//            Long amount = Long.parseLong(payload.get("amount"));
//
//            JsonNode responseNode = tossPaymentsService.confirmPayment(paymentKey, orderId, amount);
//
//            if (responseNode != null && responseNode.has("status") && "DONE".equals(responseNode.get("status").asText())) {
//                TossPaymentsDTO paymentDTO = TossPaymentsDTO.builder()
//                        .paymentKey(paymentKey)
//                        .type(responseNode.get("type").asText())
//                        .orderId(orderId)
//                        .orderName(responseNode.get("orderName").asText())
//                        .status(responseNode.get("status").asText())
//                        .totalAmount(responseNode.get("totalAmount").asLong())
//                        .method(responseNode.get("method").asText())
//                        .requestedAt(LocalDateTime.parse(responseNode.get("requestedAt").asText().replace("+09:00", ""), DateTimeFormatter.ISO_DATE_TIME))
//                        .approvedAt(LocalDateTime.parse(responseNode.get("approvedAt").asText().replace("+09:00", ""), DateTimeFormatter.ISO_DATE_TIME))
//                        .build();
//
//                tossPaymentsService.savePayments(paymentDTO);
//
//                return ResponseEntity.ok(responseNode);
//            } else {
//                return ResponseEntity.badRequest().body(responseNode);
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }
//
//    @RequestMapping("/toss_fail")
//    public String failPayment(@RequestParam String message, @RequestParam String code, Model model) {
//        model.addAttribute("message", message);
//        model.addAttribute("code", code);
//        return "toss_fail";
//    }
//
//    @RequestMapping("/virtual-account/callback")
//    @ResponseStatus(HttpStatus.OK)
//    public void handleVirtualAccountCallback(@RequestBody CallbackPayload payload) {
//        if ("DONE".equals(payload.getStatus())) {
//            // handle deposit result
//        }
//    }
//
//    private static class CallbackPayload {
//        private String secret;
//        private String status;
//        private String orderId;
//
//        public String getSecret() {
//            return secret;
//        }
//
//        public void setSecret(String secret) {
//            this.secret = secret;
//        }
//
//        public String getStatus() {
//            return status;
//        }
//
//        public void setStatus(String status) {
//            this.status = status;
//        }
//
//        public String getOrderId() {
//            return orderId;
//        }
//
//        public void setOrderId(String orderId) {
//            this.orderId = orderId;
//        }
//    }
//
//}
