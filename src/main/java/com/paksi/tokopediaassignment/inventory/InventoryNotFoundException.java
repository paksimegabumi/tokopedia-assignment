package com.paksi.tokopediaassignment.inventory;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InventoryNotFoundException extends RuntimeException {

}
