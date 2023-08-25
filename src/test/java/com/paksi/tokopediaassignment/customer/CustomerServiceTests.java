package com.paksi.tokopediaassignment.customer;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTests {
    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void findById_shouldCallCustomerRepositoryFindByIdOnce_whenGivenIdIs1() {
        Customer customer = Customer.builder().id(1L).build();
        Optional<Customer> existingOptionalCustomer = Optional.of(customer);
        Mockito.when(this.customerRepository.findById(customer.getId())).thenReturn(existingOptionalCustomer);

        this.customerService.findById(customer.getId());

        Mockito.verify(this.customerRepository, Mockito.times(1)).findById(customer.getId());
    }

    @Test
    void findById_shouldThrowCustomerNotFoundException_whenGivenIdIs1AndCustomerDoesNotExistsInDatabase() {
        Customer customer = Customer.builder().id(1L).build();
        Optional<Customer> existingOptionalCustomer = Optional.empty();
        Mockito.when(this.customerRepository.findById(customer.getId())).thenReturn(existingOptionalCustomer);
        
        Assertions.assertThrows(CustomerNotFoundException.class, () -> this.customerService.findById(customer.getId()));
    }
}
