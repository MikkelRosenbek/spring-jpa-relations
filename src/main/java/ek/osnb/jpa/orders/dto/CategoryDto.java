package ek.osnb.jpa.orders.dto;

import ek.osnb.jpa.orders.model.Product;

import java.util.List;

public record CategoryDto(Long id, String name, List<Product> products) {
}
