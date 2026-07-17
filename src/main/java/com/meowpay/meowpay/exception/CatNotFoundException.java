package com.meowpay.meowpay.exception;

import java.util.UUID;

public class CatNotFoundException extends RuntimeException {
    public CatNotFoundException(UUID id) {
        super("Cat not found: " + id);
    }
}
