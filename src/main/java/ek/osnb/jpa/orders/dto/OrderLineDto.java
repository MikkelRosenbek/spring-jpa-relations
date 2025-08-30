package ek.osnb.jpa.orders.dto;

public record OrderLineDto(Long id, double unitPrice, int quantity, String product) {
}
