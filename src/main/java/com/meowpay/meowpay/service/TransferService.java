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

    private final CatRepository catRepository;
    private final TransferRepository transferRepository;

    public TransferService(CatRepository catRepository, TransferRepository transferRepository) {
        this.catRepository = catRepository;
        this.transferRepository = transferRepository;
    }

    @Transactional
    public Transfer send(UUID senderId, UUID recipientId, Long amount) {

        UUID firstId = senderId.compareTo(recipientId) < 0 ? senderId : recipientId;
        UUID secondId = senderId.compareTo(recipientId) < 0 ? recipientId : senderId;

        Cat first = catRepository.findByIdForUpdate(firstId)
            .orElseThrow(() -> new RuntimeException("Cat not found: " + firstId));
        Cat second = catRepository.findByIdForUpdate(secondId)
            .orElseThrow(() -> new RuntimeException("Cat not found: " + secondId));

        Cat sender = first.getId().equals(senderId) ? first : second;
        Cat recipient = sender == first ? second : first;

        sender.debit(amount);
        recipient.credit(amount);

        return transferRepository.save(new Transfer(senderId, recipientId, amount, UUID.randomUUID().toString()));
    }
}