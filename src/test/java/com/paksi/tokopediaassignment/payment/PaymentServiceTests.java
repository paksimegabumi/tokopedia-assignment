package com.paksi.tokopediaassignment.payment;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paksi.tokopediaassignment.common.Constants;
import com.paksi.tokopediaassignment.customer.Customer;
import com.paksi.tokopediaassignment.customer.CustomerService;
import com.paksi.tokopediaassignment.payment.model.Payment;
import com.paksi.tokopediaassignment.paymenttype.PaymentType;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTests {
    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private CustomerService customerService;

    @Test
    void create_shouldCallPaymentRepositorySave_whenCustomerExists() {
        Customer customer = Customer.builder().id(1L).build();
        PaymentType paymentType = PaymentType.builder().name(Constants.PaymentType.BANK_TRANSFER).build();
        double amount = 20000;
        Payment payment = Payment.builder().amount(amount).customer(customer).date(LocalDateTime.now())
                .paymentType(paymentType).build();
        Payment paymentWithId = Payment.builder().id(1L).amount(amount).customer(customer).date(LocalDateTime.now())
                .paymentType(paymentType).build();
        Mockito.when(this.customerService.findById(customer.getId())).thenReturn(customer);
        Mockito.when(this.paymentRepository.save(payment)).thenReturn(paymentWithId);

        this.paymentService.create(payment);

        Mockito.verify(this.customerService, Mockito.times(1)).findById(customer.getId());
        Mockito.verify(this.paymentRepository, Mockito.times(1)).save(payment);
    }

    @Test
    void update_shouldUpdatePaymentAndCallPaymentRepositoryFindById_whenInvoked() {
        Customer customer = Customer.builder().id(1L).build();
        PaymentType paymentType = PaymentType.builder().id(1L).name(Constants.PaymentType.BANK_TRANSFER).build();
        double amount = 20000;
        double updatedAmount = 15000;
        Payment payment = Payment.builder().amount(amount).customer(customer).date(LocalDateTime.now())
                .paymentType(paymentType).build();
        Payment updatedPayment = Payment.builder().amount(updatedAmount).customer(customer).date(LocalDateTime.now())
                .paymentType(paymentType).build();
        Mockito.when(this.paymentRepository.findById(payment.getId())).thenReturn(Optional.of(payment));
        Mockito.when(this.customerService.findById(customer.getId())).thenReturn(customer);

        this.paymentService.update(updatedPayment);

        Mockito.verify(this.paymentRepository, Mockito.times(1)).findById(payment.getId());
        Mockito.verify(this.customerService, Mockito.times(1)).findById(customer.getId());
    } 
}
