package com.meowpay.meowpay.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

import com.meowpay.meowpay.exception.InsufficientFundsException;

@Entity
@Table(name = "cat")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "treat", nullable = false)
    private Long treat;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    protected Cat() {
        // required by JPA
    }

    public Cat(String name) {
        this.name = name;
        this.treat = 0L;
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }

    public void credit(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Credit amount must be positive");
        }
        this.treat += amount;
    }

    public void debit(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Debit amount must be positive");
        }
        if (this.treat - amount < 0) {
            throw new InsufficientFundsException("Insufficient treats: has " + this.treat + ", needs " + amount);
        }
        this.treat -= amount;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getTreat() {
        return treat;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}