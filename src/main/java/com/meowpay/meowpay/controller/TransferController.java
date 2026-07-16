package com.meowpay.meowpay.controller;

import com.meowpay.meowpay.dto.TransferRequest;
import com.meowpay.meowpay.model.Transfer;
import com.meowpay.meowpay.service.TransferService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfers")
    public ResponseEntity<Transfer> createTransfer(@RequestBody TransferRequest request) {
        Transfer result = transferService.send(request.senderId, request.recipientId, request.amount);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
