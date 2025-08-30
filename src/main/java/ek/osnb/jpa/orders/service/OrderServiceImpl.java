package ek.osnb.jpa.orders.service;

import ek.osnb.jpa.orders.dto.OrderDto;
import ek.osnb.jpa.orders.dto.OrderLineDto;
import ek.osnb.jpa.orders.dto.OrderMapper;
import ek.osnb.jpa.orders.model.Order;
import ek.osnb.jpa.orders.model.OrderLine;
import ek.osnb.jpa.orders.model.OrderStatus;
import ek.osnb.jpa.orders.repository.OrderLineRepository;
import ek.osnb.jpa.orders.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderLineRepository orderLineRepository) {
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
    }

    @Override
    public List<OrderDto> getAllOrders(OrderStatus status) {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();
        for (var order : orders) {
            orderDtos.add(OrderMapper.toDto(order));
        }
        return orderDtos;
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return OrderMapper.toDto(order.get());
        }
        throw new RuntimeException("Order not found with id: " + id);
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = OrderMapper.toEntity(orderDto); // Ensure the ID is null for new entities
        order.setId(null);
        return OrderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isPresent()) {
            Order order = OrderMapper.toEntity(orderDto);
            Order updatedOrder = existingOrder.get();
            updatedOrder.setOrderDate(order.getOrderDate());
            updatedOrder.setStatus(order.getStatus());
            updatedOrder.clearOrderLines();
            for (var line : order.getOrderLines()) {
                updatedOrder.addOrderLine(line);
            }

            return OrderMapper.toDto(orderRepository.save(updatedOrder));
        }
        throw new RuntimeException("Order not found with id: " + id);
    }

    @Override
    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new RuntimeException("Order not found with id: " + id);
        }
    }



    @Override
    public OrderDto addOrderLine(Long id, OrderLineDto orderLineDto) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new RuntimeException("Order not found with id: " + id);
        }
        Order existingOrder = order.get();
        var line = OrderMapper.toEntity(orderLineDto);
        existingOrder.addOrderLine(line);
        return OrderMapper.toDto(orderRepository.save(existingOrder));

    }

    @Override
    public OrderDto removeOrderLine(Long id, Long orderLineId) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new RuntimeException("Order not found with id: " + id);
        }
        Order existingOrder = order.get();
        OrderLine lineToRemove = null;

        for (var line : existingOrder.getOrderLines()) {
            if (line.getId().equals(orderLineId)) {
                lineToRemove = line;
                break;
            }
        }
        if (lineToRemove == null) {
            throw new RuntimeException(
                    "OrderLine not found with id: " + orderLineId + " in order " + id
            );
        }
        existingOrder.removeOrderLine(lineToRemove);
        return OrderMapper.toDto(orderRepository.save(existingOrder));
    }

}
