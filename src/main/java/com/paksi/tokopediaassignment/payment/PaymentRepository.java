package com.paksi.tokopediaassignment.payment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.paksi.tokopediaassignment.customer.Customer;
import com.paksi.tokopediaassignment.payment.model.Payment;
import com.paksi.tokopediaassignment.paymenttype.PaymentType;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    public Page<Payment> findAllByCustomerAndPaymentTypeAndAmountGreaterThanEqualAndAmountLessThanEqual(Customer customer, PaymentType paymentType, double amountFrom, double amountTo, Pageable page);
    public Page<Payment> findAllByCustomerAndPaymentType(Customer customer, PaymentType paymentType, Pageable page);
}
