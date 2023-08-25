package com.paksi.tokopediaassignment.payment;

import org.springframework.stereotype.Service;

import com.paksi.tokopediaassignment.customer.Customer;
import com.paksi.tokopediaassignment.customer.CustomerService;
import com.paksi.tokopediaassignment.payment.model.Payment;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentReposity;
    private final CustomerService customerService;

    public Payment create(Payment newPayment) {
        Customer existingCustomer = this.customerService.findById(newPayment.getCustomer().getId());
        newPayment.setCustomer(existingCustomer);
        return this.paymentReposity.save(newPayment);
    }
}