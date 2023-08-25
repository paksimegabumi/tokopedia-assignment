package com.paksi.tokopediaassignment.payment.model.dto;

import java.time.LocalDateTime;

import com.paksi.tokopediaassignment.customer.Customer;
import com.paksi.tokopediaassignment.paymenttype.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {
    private Long id;
    private double amount;
    private PaymentType paymentType;
    private LocalDateTime date;
    private Customer customer;
}
