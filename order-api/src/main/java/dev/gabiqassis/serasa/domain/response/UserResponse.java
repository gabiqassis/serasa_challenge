package dev.gabiqassis.serasa.domain.response;

import java.time.LocalDateTime;

public record UserResponse(
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    Long id,
    String name,
    String cpf,
    String email,
    String phoneNumber
        ){}
