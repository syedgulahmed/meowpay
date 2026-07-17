package com.meowpay.meowpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.meowpay.meowpay.model.Cat;
import jakarta.persistence.LockModeType;

import java.util.Optional;
import java.util.UUID;

public interface CatRepository extends JpaRepository<Cat, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from Cat c where c.id = :id")
    Optional<Cat> findByIdForUpdate(@Param("id") UUID id);

}