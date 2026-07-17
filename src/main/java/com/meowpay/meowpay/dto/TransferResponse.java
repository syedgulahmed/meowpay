package com.meowpay.meowpay.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public class TransferResponse {
    public UUID id;
    public UUID senderId;
    public UUID recipientId;
    public Long amount;
    public Long senderBalance;
    public Long recipientBalance;
    public OffsetDateTime createdAt;
}
