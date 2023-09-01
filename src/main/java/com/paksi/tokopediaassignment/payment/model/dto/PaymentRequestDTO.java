package com.paksi.tokopediaassignment.payment.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.paksi.tokopediaassignment.customer.Customer;
import com.paksi.tokopediaassignment.payment.model.Payment;
import com.paksi.tokopediaassignment.paymentinventory.PaymentInventory;
import com.paksi.tokopediaassignment.paymentinventory.PaymentInventoryRequestDTO;
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

    private List<PaymentInventoryRequestDTO> paymentInventoryRequestDTOs;

    public Payment convertToEntity() {
        Payment payment = Payment.builder().id(this.id).amount(this.amount).paymentType(this.paymentType)
                .date(this.date)
                .customer(this.customer).build();
        List<PaymentInventory> paymentInventories = this.paymentInventoryRequestDTOs.stream()
                .map(paymentInventoryRequestDTO -> {
                    PaymentInventory paymentInventory = paymentInventoryRequestDTO.convertToEntity();
                    paymentInventory.setPayment(payment);
                    return paymentInventory;
                }).toList();
        payment.setPaymentInventories(paymentInventories);

        return payment;
    }
}
