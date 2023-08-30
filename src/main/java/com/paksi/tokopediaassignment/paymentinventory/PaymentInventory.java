package com.paksi.tokopediaassignment.paymentinventory;

import com.paksi.tokopediaassignment.inventory.model.Inventory;
import com.paksi.tokopediaassignment.payment.model.Payment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;

    private int quantity;

    private double totalPrice;

    public PaymentInventoryResponseDTO convertToDTO() {
        return PaymentInventoryResponseDTO.builder().id(this.id).paymentId(this.payment.getId())
                .inventoryId(this.getInventory().getId()).quantity(this.quantity).totalPrice(this.totalPrice).build();
    }
}
