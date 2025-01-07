package dev.gabiqassis.serasa.controller.impl;

import dev.gabiqassis.serasa.controller.OrderController;
import dev.gabiqassis.serasa.domain.request.OrderCreaterRequest;
import dev.gabiqassis.serasa.domain.request.OrderUpdateRequest;
import dev.gabiqassis.serasa.domain.response.OrderResponse;
import dev.gabiqassis.serasa.domain.response.OrderUpdateResponse;
import dev.gabiqassis.serasa.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;

    @Override
    public ResponseEntity<List<OrderResponse>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @Override
    public ResponseEntity<OrderResponse> create(OrderCreaterRequest orderCreateRequest) {
        return ResponseEntity.status(CREATED).body(orderService.create(orderCreateRequest));
    }

    @Override
    public ResponseEntity<OrderResponse> findById(Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @Override
    public ResponseEntity<OrderUpdateResponse> update(Long id, OrderUpdateRequest orderUpdateRequest) {
        OrderUpdateResponse response = orderService.update(id, orderUpdateRequest);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<OrderResponse>> findByUserId(Long userId) {
        return ResponseEntity.ok(orderService.findByUserId(userId));
    }
}
