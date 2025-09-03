package ek.osnb.jpa.orders.dto;

import ek.osnb.jpa.orders.model.Category;
import ek.osnb.jpa.orders.model.Product;


import java.util.HashSet;
import java.util.Set;

public class ProductMapper {
    public static ProductDto toDto(Product product) {
        Set<Category> categories = new HashSet<>();

        for (var category : product.getCategories()) {
            categories.add(category);
        }
        return new ProductDto(product.getId(), product.getName(), product.getPrice(), categories);
    }

    public static Product toEntity(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.name());
        product.setPrice(productDto.price());

        for (var category : productDto.categories()) {
            product.addCategory(category);
        }
        return product;
    }
}
