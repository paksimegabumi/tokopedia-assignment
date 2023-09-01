package com.paksi.tokopediaassignment.paymentinventory;

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
public class PaymentInventoryResponseDTO {
    private Long id;

    private Long paymentId;

    private Long inventoryId;

    private int quantity;

    private double totalPrice;
}
