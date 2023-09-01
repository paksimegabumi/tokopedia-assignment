package com.paksi.tokopediaassignment.paymenttype;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {
    public Optional<PaymentType> findByName(String name);
}
