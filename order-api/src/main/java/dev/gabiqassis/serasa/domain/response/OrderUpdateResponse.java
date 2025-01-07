package dev.gabiqassis.serasa.domain.response;

import java.time.LocalDateTime;

public record OrderUpdateResponse(
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long id,
        String userId,
        String itemDescription,
        int itemQuantity,
        double itemPrice,

        double totalValue
){}
