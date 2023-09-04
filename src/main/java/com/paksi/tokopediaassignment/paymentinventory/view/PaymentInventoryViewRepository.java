package com.paksi.tokopediaassignment.paymentinventory.view;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.paksi.tokopediaassignment.common.ReadOnlyRepository;

public interface PaymentInventoryViewRepository extends ReadOnlyRepository<PaymentInventoryView, Long> {
    Page<PaymentInventoryView> findAll(Pageable pageable);
    Page<PaymentInventoryView> findAll(Example<PaymentInventoryView> paymentInventory, Pageable pageable);
}
