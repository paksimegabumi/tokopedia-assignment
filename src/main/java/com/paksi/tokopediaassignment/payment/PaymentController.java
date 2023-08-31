package com.paksi.tokopediaassignment.payment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paksi.tokopediaassignment.common.Constants;
import com.paksi.tokopediaassignment.payment.model.Payment;
import com.paksi.tokopediaassignment.payment.model.dto.PaymentRequestDTO;
import com.paksi.tokopediaassignment.payment.model.dto.PaymentResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/payments")
    public ResponseEntity<PaymentResponseDTO> create(@Valid @RequestBody PaymentRequestDTO paymentRequestDTO) {
        Payment newPayment = paymentRequestDTO.convertToEntity();

        Payment savedPayment = this.paymentService.create(newPayment);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment.convertToDTO());
    }

    @PutMapping("/payments/{id}")
    public ResponseEntity<PaymentResponseDTO> update(@PathVariable("id") Long id,
            @RequestBody PaymentRequestDTO paymentRequestDTO) {
        Payment payment = paymentRequestDTO.convertToEntity();
        payment.setId(id);

        Payment savedPayment = this.paymentService.update(payment);

        return ResponseEntity.ok(savedPayment.convertToDTO());
    }

    @DeleteMapping("/payments/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {

        this.paymentService.delete(id);

        return ResponseEntity.ok(Constants.Message.PAYMENT_DELETED);
    }

    @GetMapping("/payments")
    public ResponseEntity<Page<PaymentResponseDTO>> get(
            @RequestParam(name = Constants.Filter.CUSTOMER_ID) String customerId,
            @RequestParam(name = Constants.Filter.PAYMENT_TYPE_NAME) String paymentTypeName,
            @RequestParam(name = Constants.Filter.AMOUNT_FROM, required = false) Optional<String> amountFrom,
            @RequestParam(name = Constants.Filter.AMOUNT_TO, required = false) Optional<String> amountTo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);

        Map<String, String> filters = this.mapFilters(customerId, paymentTypeName, amountFrom, amountTo);

        Page<Payment> existingPayments = this.paymentService.get(filters, paging);
        Page<PaymentResponseDTO> paymentResponseDTO = existingPayments.map(Payment::convertToDTO);
        return ResponseEntity.ok(paymentResponseDTO);
    }

    private Map<String, String> mapFilters(String customerId, String paymentTypeName, Optional<String> amountFrom,
            Optional<String> amountTo) {
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(Constants.Filter.CUSTOMER_ID, customerId);
        requestParameters.put(Constants.Filter.PAYMENT_TYPE_NAME, paymentTypeName);

        if (amountFrom.isPresent()) {
            requestParameters.put(Constants.Filter.AMOUNT_FROM, amountFrom.get());
        }

        if (amountTo.isPresent()) {
            requestParameters.put(Constants.Filter.AMOUNT_TO, amountTo.get());
        }

        return requestParameters;
    }
}