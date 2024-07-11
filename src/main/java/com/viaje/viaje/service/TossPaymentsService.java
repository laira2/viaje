package com.viaje.viaje.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viaje.viaje.config.TossPaymentsConfig;
import com.viaje.viaje.dto.TossPaymentsDTO;
import com.viaje.viaje.model.TossPayments;
import com.viaje.viaje.repository.TossPaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class TossPaymentsService {
    private TossPaymentsRepository tossPaymentsRepository;
    private TossPaymentsConfig tossPaymentsConfig;
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    @Autowired
    public TossPaymentsService(TossPaymentsRepository tossPaymentsRepository, TossPaymentsConfig tossPaymentsConfig) {
        this.tossPaymentsRepository = tossPaymentsRepository;
        this.tossPaymentsConfig = tossPaymentsConfig;
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public TossPaymentsDTO savePayments(TossPaymentsDTO paymentsDTO){
        TossPayments payments= TossPayments.fromDTO(paymentsDTO);
        payments = tossPaymentsRepository.save(payments);
        return payments.toDTO();
    }

    public TossPaymentsDTO getPaymentByPaymentKey(String paymentKey) {
        TossPayments payment = tossPaymentsRepository.findByPaymentKey(paymentKey);
        return payment.toDTO();
    }

    public JsonNode confirmPayment(String paymentKey, String orderId, Long amount) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((tossPaymentsConfig.getTestSecretKey() + ":").getBytes()));
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("orderId", orderId);
        payloadMap.put("amount", String.valueOf(amount));

        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(payloadMap), headers);

        ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity(
                "https://api.tosspayments.com/v1/payments/" + paymentKey, request, JsonNode.class);

        return responseEntity.getBody();
    }


}