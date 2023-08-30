package com.paksi.tokopediaassignment.payment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "There is a difference within amount of payment")
public class AmountIsInvalidException extends RuntimeException {

}
