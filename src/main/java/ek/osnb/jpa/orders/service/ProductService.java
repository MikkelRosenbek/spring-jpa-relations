package ek.osnb.jpa.orders.service;

import ek.osnb.jpa.orders.dto.CategoryDto;
import ek.osnb.jpa.orders.dto.ProductDto;
import ek.osnb.jpa.orders.dto.ProductUpdateDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);


    // Category management
    ProductDto addCategory(Long productId, CategoryDto categoryDto);
    ProductDto removeCategory(Long productId, Long categoryId);

    //Product update with specific fields
    ProductDto updateCategory(Long id, ProductUpdateDto productUpdateDto);



}
