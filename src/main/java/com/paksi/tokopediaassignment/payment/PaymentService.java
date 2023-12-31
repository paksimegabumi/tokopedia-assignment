package com.paksi.tokopediaassignment.payment;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.paksi.tokopediaassignment.common.Constants;
import com.paksi.tokopediaassignment.customer.Customer;
import com.paksi.tokopediaassignment.customer.CustomerService;
import com.paksi.tokopediaassignment.inventory.InventoryNotFoundException;
import com.paksi.tokopediaassignment.inventory.InventoryService;
import com.paksi.tokopediaassignment.inventory.model.Inventory;
import com.paksi.tokopediaassignment.payment.model.Payment;
import com.paksi.tokopediaassignment.paymentinventory.PaymentInventory;
import com.paksi.tokopediaassignment.paymenttype.PaymentType;
import com.paksi.tokopediaassignment.paymenttype.PaymentTypeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentReposity;
    private final CustomerService customerService;
    private final InventoryService inventoryService;
    private final PaymentTypeService paymentTypeService;

    public Payment create(Payment newPayment) {
        this.validateCustomer(newPayment);
        this.validatePaymentType(newPayment);
        List<Inventory> existingInventories = this.getExistingInventories(newPayment);
        this.setTotalPrice(newPayment, existingInventories);
        this.validateTotalPrice(newPayment);

        return this.paymentReposity.save(newPayment);
    }

    public Payment update(Payment payment) {
        Payment existingPayment = this.paymentReposity.findById(payment.getId())
                .orElseThrow(PaymentNotFoundException::new);
        this.validateCustomer(payment);
        payment.setId(existingPayment.getId());

        return this.paymentReposity.save(payment);
    }

    public void delete(Long id) {
        Payment existingPayment = this.paymentReposity.findById(id)
                .orElseThrow(PaymentNotFoundException::new);
        this.paymentReposity.delete(existingPayment);
    }

    public Page<Payment> get(Map<String, String> filters, Pageable page) {
        Long customerId = Long.valueOf(filters.get(Constants.Filter.CUSTOMER_ID));
        String paymentTypeName = filters.get(Constants.Filter.PAYMENT_TYPE_NAME);
        Customer customer = Customer.builder().id(customerId).build();
        PaymentType paymentType = this.paymentTypeService.findByName(paymentTypeName);

        if (filters.containsKey(Constants.Filter.AMOUNT_FROM)
                && filters.containsKey(Constants.Filter.AMOUNT_TO)) {
            double amountFrom = Double.parseDouble(filters.get(Constants.Filter.AMOUNT_FROM));
            double amountTo = Double.parseDouble(filters.get(Constants.Filter.AMOUNT_TO));

            return this.paymentReposity.findAllByCustomerAndPaymentTypeAndAmountGreaterThanEqualAndAmountLessThanEqual(customer, paymentType,
                    amountFrom, amountTo, page);
        }

        return this.paymentReposity.findAllByCustomerAndPaymentType(customer, paymentType, page);
    }

    private void validatePaymentType(Payment payment) {
        PaymentType existingPaymentType = this.paymentTypeService.findById(payment.getPaymentType().getId());
        payment.setPaymentType(existingPaymentType);
    }

    private void validateCustomer(Payment payment) {
        Customer existingCustomer = this.customerService.findById(payment.getCustomer().getId());
        payment.setCustomer(existingCustomer);
    }

    private List<Inventory> getExistingInventories(Payment payment) {
        List<Long> inventoryIds = payment.getPaymentInventories().stream()
                .map(paymentInventory -> paymentInventory.getInventory().getId()).toList();
        return this.inventoryService.findAllIn(inventoryIds);
    }

    private void setTotalPrice(Payment payment, List<Inventory> existingInventories) {
        payment.getPaymentInventories().stream().forEach(paymentInventory -> {
            Inventory inventory = existingInventories.stream()
                    .filter(item -> item.getId().equals(paymentInventory.getInventory().getId())).findFirst()
                    .orElseThrow(InventoryNotFoundException::new);
            double price = paymentInventory.getQuantity() * inventory.getPrice();
            paymentInventory.setTotalPrice(price);
        });
    }

    private void validateTotalPrice(Payment payment) {
        double totalPrice = payment.getPaymentInventories().stream().mapToDouble(PaymentInventory::getTotalPrice).sum();

        if (totalPrice != payment.getAmount()) {
            throw new AmountIsInvalidException();
        }
    }
}
