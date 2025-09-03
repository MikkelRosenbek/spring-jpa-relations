package ek.osnb.jpa.orders.dto;

import ek.osnb.jpa.orders.model.Category;


import java.util.Set;

public record ProductDto(Long id, String name, Double price, Set<Category> categories) {
}
