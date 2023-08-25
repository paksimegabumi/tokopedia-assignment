package com.paksi.tokopediaassignment.payment.model;

import java.time.LocalDateTime;

import com.paksi.tokopediaassignment.customer.Customer;
import com.paksi.tokopediaassignment.payment.model.dto.PaymentResponseDTO;
import com.paksi.tokopediaassignment.paymenttype.PaymentType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    @ManyToOne
    private PaymentType paymentType;

    private LocalDateTime date;

    @ManyToOne
    private Customer customer;

    public PaymentResponseDTO convertToDTO() {
        return PaymentResponseDTO.builder().id(this.id).amount(this.amount).paymentType(this.paymentType)
                .date(this.date).customer(this.customer).build();
    }
}