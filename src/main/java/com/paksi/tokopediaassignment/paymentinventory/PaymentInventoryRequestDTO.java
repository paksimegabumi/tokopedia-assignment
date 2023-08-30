package com.paksi.tokopediaassignment.paymentinventory;

import com.paksi.tokopediaassignment.inventory.model.Inventory;

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
public class PaymentInventoryRequestDTO {
    private Long inventoryId;

    @Min(1)
    private int quantity;

    public PaymentInventory convertToEntity() {
        Inventory inventory = Inventory.builder().id(this.inventoryId).build();

        return PaymentInventory.builder().inventory(inventory).quantity(this.quantity).build();
    }
}
