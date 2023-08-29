package com.paksi.tokopediaassignment.payment.model.dto;

import java.time.LocalDateTime;

import com.paksi.tokopediaassignment.customer.Customer;
import com.paksi.tokopediaassignment.payment.model.Payment;
import com.paksi.tokopediaassignment.paymenttype.PaymentType;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {
    private Long id;

    @Min(1000)
    private double amount;

    private PaymentType paymentType;

    private LocalDateTime date;

    private Customer customer;

    public Payment convertToEntity() {
        return Payment.builder().id(this.id).amount(this.amount).paymentType(this.paymentType).date(this.date)
                .customer(this.customer).build();
    }
}
