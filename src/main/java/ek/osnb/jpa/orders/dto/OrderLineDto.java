package ek.osnb.jpa.orders.dto;

import ek.osnb.jpa.orders.model.Product;

public record OrderLineDto(Long id, int quantity, Product product) {
}
