package com.paksi.tokopediaassignment.paymenttype;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
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
public class PaymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank
    private Long id;
    
    private String name;
}
