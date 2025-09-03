package ek.osnb.jpa.orders.service;

import ek.osnb.jpa.orders.dto.CategoryDto;
import ek.osnb.jpa.orders.dto.CategoryUpdateDto;
import ek.osnb.jpa.orders.dto.ProductDto;
import ek.osnb.jpa.orders.model.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Long id);
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(Long id, CategoryDto categoryDto);
    void deleteCategory(Long id);

    // Product management
    CategoryDto addProduct(Long categoryId, ProductDto productDto);
    CategoryDto removeProduct(Long categoryId, Long productId);

    //Product update with specific fields
    CategoryDto updateProduct(Long id, CategoryUpdateDto categoryUpdateDto);




}
