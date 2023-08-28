package com.paksi.tokopediaassignment.paymenttype;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PaymentTypeNotFoundException extends RuntimeException {
    
}
