package com.paksi.tokopediaassignment.paymenttype;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentTypeService {
    private final PaymentTypeRepository paymentTypeRepository;

    public PaymentType findById(Long id) {
        return this.paymentTypeRepository.findById(id).orElseThrow(PaymentTypeNotFoundException::new);
    }

    public PaymentType findByName(String paymentTypeName) {
        return this.paymentTypeRepository.findByName(paymentTypeName).orElseThrow(PaymentTypeNotFoundException::new);
    }
}
