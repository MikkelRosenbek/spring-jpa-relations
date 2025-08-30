package ek.osnb.jpa.orders.service;


import ek.osnb.jpa.orders.dto.OrderDto;
import ek.osnb.jpa.orders.model.Order;
import ek.osnb.jpa.orders.model.OrderStatus;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders(OrderStatus status);
    OrderDto getOrderById(Long id);
    OrderDto createOrder(OrderDto orderDto);
    OrderDto updateOrder(Long id, OrderDto orderDto);
    void deleteOrder(Long id);
}
