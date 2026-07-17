package com.meowpay.meowpay.controller;

import com.meowpay.meowpay.dto.TransferRequest;
import com.meowpay.meowpay.dto.TransferResponse;
import com.meowpay.meowpay.model.Cat;
import com.meowpay.meowpay.model.Transfer;
import com.meowpay.meowpay.repository.CatRepository;
import com.meowpay.meowpay.service.TransferResult;
import com.meowpay.meowpay.service.TransferService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    private final TransferService transferService;
    private final CatRepository catRepository;

    public TransferController(TransferService transferService, CatRepository catRepository) {
        this.transferService = transferService;
        this.catRepository = catRepository;
    }

    @PostMapping("/transfers")
    public ResponseEntity<TransferResponse> createTransfer(
            @RequestBody TransferRequest request,
            @RequestHeader("Idempotency-Key") String idempotencyKey) {

        TransferResult result = transferService.send(request.senderId, request.recipientId, request.amount, idempotencyKey);
        Transfer transfer = result.getTransfer();

        TransferResponse response = new TransferResponse();
        response.id = transfer.getId();
        response.senderId = transfer.getSenderId();
        response.recipientId = transfer.getReceiverId();
        response.amount = transfer.getAmount();
        response.createdAt = transfer.getCreatedAt();

        Cat sender = catRepository.findById(transfer.getSenderId()).orElseThrow();
        Cat recipient = catRepository.findById(transfer.getReceiverId()).orElseThrow();
        response.senderBalance = sender.getTreat();
        response.recipientBalance = recipient.getTreat();

        HttpStatus status = result.isWasReplay() ? HttpStatus.OK : HttpStatus.CREATED;
        return ResponseEntity.status(status).body(response);
    }

}
