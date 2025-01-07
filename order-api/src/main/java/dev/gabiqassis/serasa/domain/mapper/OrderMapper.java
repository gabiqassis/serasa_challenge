package dev.gabiqassis.serasa.domain.mapper;

import dev.gabiqassis.serasa.domain.entity.Order;
import dev.gabiqassis.serasa.domain.request.OrderCreaterRequest;
import dev.gabiqassis.serasa.domain.request.OrderUpdateRequest;
import dev.gabiqassis.serasa.domain.response.OrderResponse;
import dev.gabiqassis.serasa.domain.response.OrderUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order map(OrderCreaterRequest orderCreateRequest);

    OrderResponse map(Order order);

    OrderUpdateResponse mapToUpdate(Order order);

    void map (OrderUpdateRequest orderUpdateRequest, @MappingTarget Order order);
}