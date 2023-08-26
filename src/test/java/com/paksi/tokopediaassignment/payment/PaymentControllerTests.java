package com.paksi.tokopediaassignment.payment;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paksi.tokopediaassignment.common.Constants;
import com.paksi.tokopediaassignment.customer.Customer;
import com.paksi.tokopediaassignment.customer.CustomerRepository;
import com.paksi.tokopediaassignment.payment.model.dto.PaymentRequestDTO;
import com.paksi.tokopediaassignment.payment.model.dto.PaymentResponseDTO;
import com.paksi.tokopediaassignment.paymenttype.PaymentType;
import com.paksi.tokopediaassignment.paymenttype.PaymentTypeRepository;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerTests {
        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private CustomerRepository customerRepository;

        @Autowired
        private PaymentTypeRepository paymentTypeRepository;

        @Test
        void create_shouldSaveNewPaymentToDatabase_whenCalled() throws JsonProcessingException, Exception {
                Customer customer = Customer.builder().id(1L).build();
                Customer savedCustomer = this.customerRepository.save(customer);
                PaymentType paymentType = PaymentType.builder().name(Constants.PaymentType.BANK_TRANSFER).build();
                PaymentType savedPaymentType = this.paymentTypeRepository.save(paymentType);
                LocalDateTime dateNow = LocalDateTime.now();
                double amount = 20000;
                PaymentRequestDTO requestDTO = PaymentRequestDTO.builder().amount(amount).customer(savedCustomer)
                                .date(dateNow).paymentType(savedPaymentType).build();
                PaymentResponseDTO expectedResponseDTO = PaymentResponseDTO.builder().amount(amount)
                                .customer(savedCustomer).date(dateNow).paymentType(savedPaymentType).build();
                RequestBuilder request = MockMvcRequestBuilders
                                .post("/payments")
                                .content(objectMapper.writeValueAsString(requestDTO))
                                .contentType(MediaType.APPLICATION_JSON);

                MvcResult mvcResult = this.mockMvc.perform(request)
                                .andExpect(MockMvcResultMatchers.status().isCreated())
                                .andReturn();
                PaymentResponseDTO actualResponseDTO = this.objectMapper.readValue(
                                mvcResult.getResponse().getContentAsString(),
                                PaymentResponseDTO.class);
                expectedResponseDTO.setId(actualResponseDTO.getId());

                Assertions.assertEquals(expectedResponseDTO, actualResponseDTO);
        }
}
