package com.paksi.tokopediaassignment.payment.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paksi.tokopediaassignment.customer.Customer;
import com.paksi.tokopediaassignment.payment.model.dto.PaymentResponseDTO;
import com.paksi.tokopediaassignment.paymentinventory.PaymentInventory;
import com.paksi.tokopediaassignment.paymentinventory.PaymentInventoryResponseDTO;
import com.paksi.tokopediaassignment.paymenttype.PaymentType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @JsonIgnore
    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List<PaymentInventory> paymentInventories;

    public PaymentResponseDTO convertToDTO() {
        List<PaymentInventoryResponseDTO> paymentInventoryResponseDTOs = this.paymentInventories.stream()
                .map(PaymentInventory::convertToDTO).toList();
        return PaymentResponseDTO.builder().id(this.id).amount(this.amount).paymentType(this.paymentType)
                .date(this.date).customer(this.customer).paymentInventoryResponseDTOs(paymentInventoryResponseDTOs)
                .build();
    }
}
