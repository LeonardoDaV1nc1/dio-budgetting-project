package com.dio.budgetting.main.domain.id;

import java.util.UUID;

public record UserId(UUID uuid) {
    public UserId() {
        this(UUID.randomUUID());
    }
}