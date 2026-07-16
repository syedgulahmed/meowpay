package com.meowpay.meowpay.service;

import com.meowpay.meowpay.model.Cat;
import com.meowpay.meowpay.model.Transfer;
import com.meowpay.meowpay.repository.CatRepository;
import com.meowpay.meowpay.repository.TransferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TransferService {

    private final CatRepository catRepo;
    private final TransferRepository transferRepo;

    public TransferService(CatRepository catRepo, TransferRepository transferRepo) {
        this.catRepo = catRepo;
        this.transferRepo = transferRepo;
    }

    @Transactional
    public Transfer send(UUID senderId, UUID recipientId, Long amount) {
        
        Cat sender = catRepo.findById(senderId)
            .orElseThrow(() -> new RuntimeException("Sender not found"));
        Cat recipient = catRepo.findById(recipientId)
            .orElseThrow(() -> new RuntimeException("Recipient not found"));

        sender.debit(amount);
        recipient.credit(amount);

        return transferRepo.save(new Transfer(senderId, recipientId, amount, UUID.randomUUID().toString()));
    }
}