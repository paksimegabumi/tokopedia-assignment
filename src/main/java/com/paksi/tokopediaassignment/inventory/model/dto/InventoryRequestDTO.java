package com.paksi.tokopediaassignment.inventory.model.dto;

import com.paksi.tokopediaassignment.inventory.model.Inventory;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequestDTO {
    @NotBlank
    private Long id;

    private String name;

    @Min(1)
    private int quantity;

    @NotBlank
    private double price;

    public Inventory convertToEntity() {
        return Inventory.builder().id(this.id).name(this.name).quantity(this.quantity).price(this.price).build();
    }
}
