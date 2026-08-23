package com.dio.budgetting.main.infratructure.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.dio.budgetting.main.domain.Category;
import com.dio.budgetting.main.domain.Transaction;
import com.dio.budgetting.main.domain.id.TransactionId;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {
	
    @Id
    private UUID id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    
    private String description;
    private long amount;

    @Enumerated(EnumType.STRING)
    private Category category;
    
    private LocalDateTime createdAt;

    public static TransactionEntity from(Transaction transaction) {
        return new TransactionEntity(
                transaction.getId().uuid(),
                UserEntity.from(transaction.getUser()),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getCategory(),
                transaction.getCreatedAt());
    }

    public Transaction toDomain() {
        return new Transaction(
                new TransactionId(this.id),
                this.user.toDomain(),
                this.description,
                this.amount,
                this.category,
                this.createdAt
        );
    }

}