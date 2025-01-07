package dev.gabiqassis.serasa.domain.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class OrderCreaterRequest {
        @NotBlank(message = "ID do usuário é obrigatório")
        private String userId;

        @NotBlank(message = "Descrição do item é obrigatória")
        private String itemDescription;

        @NotNull(message = "Quantidade de itens é obrigatória")
        @Positive(message = "A quantidade de itens deve ser maior que zero")
        private Integer itemQuantity;

        @NotNull(message = "Preço do item é obrigatório")
        @DecimalMin(value = "0.01", inclusive = true, message = "O preço deve ser maior que zero")
        private Double itemPrice;

        public OrderCreaterRequest(String userId, String itemDescription, Integer itemQuantity, Double itemPrice) {
                this.userId = userId;
                this.itemDescription = itemDescription;
                this.itemQuantity = itemQuantity;
                this.itemPrice = itemPrice;
        }

        public String getUserId() {
                return userId;
        }

        public void setUserId(String userId) {
                this.userId = userId;
        }

        public String getItemDescription() {
                return itemDescription;
        }

        public void setItemDescription(String itemDescription) {
                this.itemDescription = itemDescription;
        }

        public Integer getItemQuantity() {
                return itemQuantity;
        }

        public void setItemQuantity(Integer itemQuantity) {
                this.itemQuantity = itemQuantity;
        }

        public Double getItemPrice() {
                return itemPrice;
        }

        public void setItemPrice(Double itemPrice) {
                this.itemPrice = itemPrice;
        }
}

