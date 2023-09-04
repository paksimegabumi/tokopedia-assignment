package com.paksi.tokopediaassignment.paymentinventory.view;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentInventoryViewService {
	private final PaymentInventoryViewRepository paymentInventoryViewRepository;

	public Page<PaymentInventoryView> findAll(Pageable pageable) {
		return this.paymentInventoryViewRepository.findAll(pageable);
	}

	public Page<PaymentInventoryView> findEmployeeProjectsExampleMatcher(String keyword, Pageable pageable) {
		PaymentInventoryView paymentInventoryView = PaymentInventoryView.builder()
				.customerName(keyword)
				.inventoryName(keyword)
				.build();
		ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("customerName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("inventoryName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

		Example<PaymentInventoryView> paymentInventory = Example.of(paymentInventoryView, customExampleMatcher);
		return paymentInventoryViewRepository.findAll(paymentInventory, pageable);
	}
}
