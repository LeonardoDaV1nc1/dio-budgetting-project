package com.dio.budgetting.main.domain;

import java.time.LocalDateTime;

import com.dio.budgetting.main.domain.id.TransactionId;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Transaction {
    private TransactionId id;
    private User user;
    private String description;
    private long amount;
    private Category category;
    private LocalDateTime createdAt;

    public Transaction(User user, String description, long amount, Category category, LocalDateTime createdAt) {
        this.id = new TransactionId();
        this.user = user;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.createdAt = createdAt;
    }

}
