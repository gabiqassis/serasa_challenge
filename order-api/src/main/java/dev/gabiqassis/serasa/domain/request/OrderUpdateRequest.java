package dev.gabiqassis.serasa.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderUpdateRequest {
    private String userId;
    private String itemDescription;
    private int itemQuantity;
    private double itemPrice;
}

