package com.meowpay.meowpay.exception;

public class SelfTransferException extends RuntimeException {
    public SelfTransferException() {
        super("Sender and recipient must be different cats");
    }
}
