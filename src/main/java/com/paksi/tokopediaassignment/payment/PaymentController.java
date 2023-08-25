package com.paksi.tokopediaassignment.payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paksi.tokopediaassignment.payment.model.Payment;
import com.paksi.tokopediaassignment.payment.model.dto.PaymentRequestDTO;
import com.paksi.tokopediaassignment.payment.model.dto.PaymentResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    
    private final PaymentService paymentService;
    
    @PostMapping("/payments")
    public ResponseEntity<PaymentResponseDTO> create(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        Payment newPayment = paymentRequestDTO.convertToEntity();
        
        Payment savedPayment = this.paymentService.create(newPayment);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment.convertToDTO());
    }
}