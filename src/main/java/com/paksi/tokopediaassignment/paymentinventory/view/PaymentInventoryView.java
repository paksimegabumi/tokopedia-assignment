package com.paksi.tokopediaassignment.paymentinventory.view;

import java.time.LocalDateTime;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;
import org.springframework.data.annotation.Immutable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vpayment_inventory")
@Getter
@Setter
@Builder
@Immutable
@NoArgsConstructor
@AllArgsConstructor
@Subselect("select gen_random_uuid() as id, p.id as \"payment_id\", c.name as \"customer_name\", p.amount, i.name as \"inventory_name\", pt.name as \"payment_type_name\", p.date from payment_inventory pi inner join payment p on p.id = pi.payment_id inner join inventory i on i.id = pi.inventory_id inner join customer c on c.id = p.customer_id inner join payment_type pt on pt.id = p.payment_type_id")
@Synchronize({"payment_inventory", "payment", "inventory", "customer"})
public class PaymentInventoryView {
    @Id
    private String id;
    private Long paymentId;
    private String customerName;
    private double amount;
    private String inventoryName;
    private String paymentTypeName;
    private LocalDateTime date;
}
