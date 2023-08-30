package com.paksi.tokopediaassignment.payment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Given payment is not exists")
public class PaymentNotFoundException extends RuntimeException {

}
