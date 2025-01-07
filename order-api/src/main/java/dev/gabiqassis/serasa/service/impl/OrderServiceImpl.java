package dev.gabiqassis.serasa.service.impl;

import dev.gabiqassis.serasa.domain.entity.Order;
import dev.gabiqassis.serasa.domain.mapper.OrderMapper;
import dev.gabiqassis.serasa.domain.request.OrderCreaterRequest;
import dev.gabiqassis.serasa.domain.request.OrderUpdateRequest;
import dev.gabiqassis.serasa.domain.response.OrderResponse;
import dev.gabiqassis.serasa.domain.response.OrderUpdateResponse;
import dev.gabiqassis.serasa.repository.OrderRepository;
import dev.gabiqassis.serasa.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    @Transactional
    @Override
    public OrderResponse create(OrderCreaterRequest orderCreateRequest) {
        logger.info("Criando novo pedido para o usuário com ID {}", orderCreateRequest.getUserId());

        hasUser(Long.valueOf(orderCreateRequest.getUserId()));

        Order order = orderMapper.map(orderCreateRequest);

        order.setUpdatedAt(null);
        calculateTotal(order);

        Order savedOrder = orderRepository.save(order);

        logger.info("Pedido com ID {} criado com sucesso, associado ao usuário com ID {}", savedOrder.getId(),savedOrder.getUserId());
        return orderMapper.map(savedOrder);
    }

    @Transactional
    @Override
    public OrderResponse findById(Long id) {
        logger.info("Buscando pedido com ID: {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Pedido com ID {} não encontrado", id);
                    return new EntityNotFoundException("Pedido não encontrado");
                });

        logger.info("Pedido encontrado: {}", order);
        return orderMapper.map(order);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        logger.info("Deletando pedido com ID {}", id);

        orderRepository.findById(id)
                .ifPresentOrElse(
                        order -> {
                            orderRepository.delete(order);
                            logger.info("Pedido com ID {} deletado com sucesso", id);
                        },
                        () -> {
                            logger.error("Pedido com ID {} não encontrado para deleção", id);
                            throw new EntityNotFoundException("Pedido não encontrado");
                        }
                );
    }

    @Transactional
    @Override
    public OrderUpdateResponse update(Long id, OrderUpdateRequest orderUpdateRequest) {
        logger.info("Atualizando pedido com ID {}", id);

        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Pedido com ID {} não encontrado", id);
                    return new EntityNotFoundException("Pedido não encontrado");
                });

        Long currentUserId = Long.valueOf(existingOrder.getUserId());
        Long newUserId = Long.valueOf(orderUpdateRequest.getUserId());

        if (!currentUserId.equals(newUserId)) {
            logger.info("Mudança no userID detectada de {} para {}", currentUserId, newUserId);
            hasUser(newUserId);
        }

        existingOrder.setItemPrice(orderUpdateRequest.getItemPrice());
        existingOrder.setItemQuantity(orderUpdateRequest.getItemQuantity());
        calculateTotal(existingOrder);

        updateUpdatedAt(existingOrder);

        Order updatedOrder = orderRepository.save(existingOrder);

        logger.info("Pedido com ID {} atualizado com sucesso", updatedOrder.getId());
        return orderMapper.mapToUpdate(updatedOrder);
    }

    @Override
    public List<OrderResponse> findAll() {
        logger.info("Buscando todos os pedidos");
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::map).toList();
    }

    @Override
    public List<OrderResponse> findByUserId(Long userId) {
        logger.info("Buscando pedidos para o usuário com ID {}", userId);
        List<Order> orders = orderRepository.findByUserId(String.valueOf(userId));

        return orders.stream().map(orderMapper::map).toList();
    }

    private void calculateTotal(Order order) {
        double total = order.getItemPrice() * order.getItemQuantity();
        order.setTotalValue(total);
        logger.info("Cálculo total feito: {} * {} = {}", order.getItemPrice(), order.getItemQuantity(), total);
    }

    private void hasUser(Long userId) {
        logger.info("Verificando se o usuário com ID {} existe", userId);
    }


    private void updateUpdatedAt(Order order) {
        order.setUpdatedAt(LocalDateTime.now());
        logger.info("Campo updatedAt setado para: {}", order.getUpdatedAt());
    }

}


