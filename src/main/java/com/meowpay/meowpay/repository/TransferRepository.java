package com.meowpay.meowpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.meowpay.meowpay.model.Transfer;
import java.util.UUID;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {
}
