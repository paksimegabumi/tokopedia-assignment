package com.paksi.tokopediaassignment.customer;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer findById(Long id) {
        return this.customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }
}
