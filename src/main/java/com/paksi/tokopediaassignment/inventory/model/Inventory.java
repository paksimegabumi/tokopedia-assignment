package com.paksi.tokopediaassignment.inventory.model;

import com.paksi.tokopediaassignment.inventory.model.dto.InventoryResponseDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int quantity;

    private double price;

    public InventoryResponseDTO convertToDTO() {
        return InventoryResponseDTO.builder().id(this.id).name(this.name).quantity(this.quantity).price(this.price).build();
    }
}
