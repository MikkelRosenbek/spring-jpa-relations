package ek.osnb.jpa.orders.dto;

import ek.osnb.jpa.orders.model.Order;
import ek.osnb.jpa.orders.model.OrderLine;
import ek.osnb.jpa.orders.model.OrderStatus;
import ek.osnb.jpa.orders.model.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static OrderDto toDto(Order order) {
        List<OrderLineDto> orderLines = new ArrayList<>();

        for (var line : order.getOrderLines()) {
            orderLines.add(toDto(line));
        }
        return new OrderDto(order.getId(), order.getOrderDate(), order.getStatus().name(), orderLines);
    }

    public static OrderLineDto toDto(OrderLine orderLine) {
        return new OrderLineDto(orderLine.getId(), orderLine.getQuantity(), orderLine.getProduct());
    }

//    public static Order toEntity(OrderDto orderDto) {
//        Order order = new Order();
//        order.setOrderDate(orderDto.orderDate());
//        order.setStatus(OrderStatus.valueOf(orderDto.status()));
//
//        for (var lineDto : orderDto.orderLines()) {
//            var line = toEntity(lineDto, product);
//            order.addOrderLine(line);
//        }
//        return order;
//    }
//
//    public static OrderLine toEntity(OrderLineDto orderLineDto, Product product) {
//        OrderLine orderLine = new OrderLine();
//        orderLine.setProduct(orderLineDto.product());
//
//        orderLine.setQuantity(orderLineDto.quantity());
//        return orderLine;
//    }

    // Convert OrderDto to Order entity
    public static Order toEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderDate(orderDto.orderDate());
        order.setStatus(OrderStatus.valueOf(orderDto.status()));

        // Cannot fully map OrderLine here without ProductRepository
        // Service layer should handle linking OrderLine → Product
        return order;
    }

    // Convert OrderLineDto to OrderLine entity (Product must be provided)
    public static OrderLine toEntity(OrderLineDto dto, ek.osnb.jpa.orders.model.Product product) {
        OrderLine line = new OrderLine();
        line.setId(dto.id());
        line.setQuantity(dto.quantity());
        line.setProduct(product); // fetched from DB
        return line;
    }


}
