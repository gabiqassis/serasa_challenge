package dev.gabiqassis.serasa.domain.mapper;

import dev.gabiqassis.serasa.domain.entity.Order;
import dev.gabiqassis.serasa.domain.request.OrderCreateRequest;
import dev.gabiqassis.serasa.domain.response.OrderResponse;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-20T18:48:27-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order map(OrderCreateRequest orderCreateRequest) {
        if ( orderCreateRequest == null ) {
            return null;
        }

        Order order = new Order();

        order.setUserId( orderCreateRequest.userId() );
        order.setItemDescription( orderCreateRequest.itemDescription() );
        order.setItemQuantity( orderCreateRequest.itemQuantity() );
        order.setItemPrice( orderCreateRequest.itemPrice() );

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
}
