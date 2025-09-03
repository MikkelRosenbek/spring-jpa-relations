package ek.osnb.jpa.orders.dto;

import ek.osnb.jpa.orders.model.Category;
import ek.osnb.jpa.orders.model.Product;

import java.util.ArrayList;
import java.util.List;


public class CategoryMapper {
    public static CategoryDto toDto(Category category) {
        List<Product> products = new ArrayList<>();

        for (var product : category.getProducts()) {
            products.add(product);
        }
        return new CategoryDto(category.getId(), category.getName(), products);
    }

    public static Category toEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.name());

        for (var product : categoryDto.products()) {
            category.addProduct(product);
        }
        return category;
    }

}
