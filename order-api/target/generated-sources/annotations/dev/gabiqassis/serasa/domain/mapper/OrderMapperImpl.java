package dev.gabiqassis.serasa.domain.mapper;

import dev.gabiqassis.serasa.domain.entity.Order;
import dev.gabiqassis.serasa.domain.request.OrderCreaterRequest;
import dev.gabiqassis.serasa.domain.request.OrderUpdateRequest;
import dev.gabiqassis.serasa.domain.response.OrderResponse;
import dev.gabiqassis.serasa.domain.response.OrderUpdateResponse;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-07T01:13:22-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order map(OrderCreaterRequest orderCreateRequest) {
        if ( orderCreateRequest == null ) {
            return null;
        }

        Order order = new Order();

        order.setUserId( orderCreateRequest.getUserId() );
        order.setItemDescription( orderCreateRequest.getItemDescription() );
        if ( orderCreateRequest.getItemQuantity() != null ) {
            order.setItemQuantity( orderCreateRequest.getItemQuantity() );
        }
        if ( orderCreateRequest.getItemPrice() != null ) {
            order.setItemPrice( orderCreateRequest.getItemPrice() );
        }

        return order;
    }

    @Override
    public OrderResponse map(Order order) {
        if ( order == null ) {
            return null;
        }

        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;
        Long id = null;
        String userId = null;
        String itemDescription = null;
        int itemQuantity = 0;
        double itemPrice = 0.0d;
        double totalValue = 0.0d;

        createdAt = order.getCreatedAt();
        updatedAt = order.getUpdatedAt();
        id = order.getId();
        userId = order.getUserId();
        itemDescription = order.getItemDescription();
        itemQuantity = order.getItemQuantity();
        itemPrice = order.getItemPrice();
        totalValue = order.getTotalValue();

        OrderResponse orderResponse = new OrderResponse( createdAt, updatedAt, id, userId, itemDescription, itemQuantity, itemPrice, totalValue );

        return orderResponse;
    }

    @Override
    public OrderUpdateResponse mapToUpdate(Order order) {
        if ( order == null ) {
            return null;
        }

        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;
        Long id = null;
        String userId = null;
        String itemDescription = null;
        int itemQuantity = 0;
        double itemPrice = 0.0d;
        double totalValue = 0.0d;

        createdAt = order.getCreatedAt();
        updatedAt = order.getUpdatedAt();
        id = order.getId();
        userId = order.getUserId();
        itemDescription = order.getItemDescription();
        itemQuantity = order.getItemQuantity();
        itemPrice = order.getItemPrice();
        totalValue = order.getTotalValue();

        OrderUpdateResponse orderUpdateResponse = new OrderUpdateResponse( createdAt, updatedAt, id, userId, itemDescription, itemQuantity, itemPrice, totalValue );

        return orderUpdateResponse;
    }

    @Override
    public void map(OrderUpdateRequest orderUpdateRequest, Order order) {
        if ( orderUpdateRequest == null ) {
            return;
        }

        order.setUserId( orderUpdateRequest.getUserId() );
        order.setItemDescription( orderUpdateRequest.getItemDescription() );
        order.setItemQuantity( orderUpdateRequest.getItemQuantity() );
        order.setItemPrice( orderUpdateRequest.getItemPrice() );
    }
}
