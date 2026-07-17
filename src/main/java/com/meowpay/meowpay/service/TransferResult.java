package com.meowpay.meowpay.service;

import com.meowpay.meowpay.model.Transfer;

public class TransferResult {
    private final Transfer transfer;
    private final boolean wasReplay;

    public TransferResult(Transfer transfer, boolean wasReplay) {
        this.transfer = transfer;
        this.wasReplay = wasReplay;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public boolean isWasReplay() {
        return wasReplay;
    }
}
