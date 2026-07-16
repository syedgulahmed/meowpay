package com.meowpay.meowpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.meowpay.meowpay.model.Cat;
import java.util.UUID;

public interface CatRepository extends JpaRepository<Cat, UUID> {
}