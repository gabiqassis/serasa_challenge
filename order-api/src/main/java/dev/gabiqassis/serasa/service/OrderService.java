package dev.gabiqassis.serasa.service;

import dev.gabiqassis.serasa.domain.request.OrderCreaterRequest;
import dev.gabiqassis.serasa.domain.request.OrderUpdateRequest;
import dev.gabiqassis.serasa.domain.response.OrderResponse;
import dev.gabiqassis.serasa.domain.response.OrderUpdateResponse;

import java.util.List;

    public interface OrderService {

        OrderResponse create(OrderCreaterRequest orderCreateRequest);

        OrderResponse findById(Long id);

        OrderUpdateResponse update(Long id, OrderUpdateRequest orderUpdateRequest);

        void deleteById(Long id);

        List<OrderResponse> findAll();

        List<OrderResponse> findByUserId(Long userId);
    }

