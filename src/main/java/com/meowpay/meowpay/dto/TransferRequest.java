package com.meowpay.meowpay.dto;

import java.util.UUID;

public class TransferRequest {
    public UUID senderId;
    public UUID recipientId;
    public Long amount;
}