package dev.gabiqassis.serasa.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "serasa_orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    String userId;

    @Column(nullable = false)
    String itemDescription;

    @Column(nullable = false)
    int itemQuantity;

    @Column(nullable = false)
    double itemPrice;

    double totalValue;

    @Column(insertable = false, updatable = true)
    private LocalDateTime updatedAt;
}
