package com.paksi.tokopediaassignment.paymenttype;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paksi.tokopediaassignment.common.Constants;

@ExtendWith(MockitoExtension.class)
class PaymentTypeServiceTests {
    @InjectMocks
    private PaymentTypeService paymentTypeService;

    @Mock
    private PaymentTypeRepository paymentTypeRepository;

    @Test
    void findById_shouldReturnPaymentTypeIfExists_whenGivenIdIs1() {
        PaymentType paymentType = PaymentType.builder().id(1L).name(Constants.PaymentType.BANK_TRANSFER).build();
        Optional<PaymentType> optionalPaymentType = Optional.of(paymentType);
        Mockito.when(this.paymentTypeRepository.findById(paymentType.getId())).thenReturn(optionalPaymentType);

        this.paymentTypeService.findById(paymentType.getId());

        Mockito.verify(this.paymentTypeRepository, Mockito.times(1)).findById(paymentType.getId());
    }

    @Test
    void findById_shouldThrowPaymentNotFoundException_whenPaymentTypeDoesNotExists() {
        Long id = 1L;
        Optional<PaymentType> optionalPaymentType = Optional.empty();
        Mockito.when(this.paymentTypeRepository.findById(id)).thenReturn(optionalPaymentType);

        Assertions.assertThrows(PaymentTypeNotFoundException.class, () -> this.paymentTypeService.findById(id));
    }
}
