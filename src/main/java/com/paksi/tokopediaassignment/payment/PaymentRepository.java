package com.paksi.tokopediaassignment.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paksi.tokopediaassignment.payment.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
