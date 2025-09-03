package ek.osnb.jpa.orders.service;

import ek.osnb.jpa.orders.dto.*;
import ek.osnb.jpa.orders.model.Product;
import ek.osnb.jpa.orders.repository.CategoryRepository;
import ek.osnb.jpa.orders.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (var product : products) {
            productDtos.add(ProductMapper.toDto(product));
        }
        return productDtos;
    }

    @Override
    public ProductDto getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ProductMapper.toDto(product.get());
        }
            throw new RuntimeException("Product not found with id " + id);
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = ProductMapper.toEntity(productDto);
        product.setId(null);
        return ProductMapper.toDto(productRepository.save(product));

    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = ProductMapper.toEntity(productDto);
            Product updateProduct = existingProduct.get();
            updateProduct.setName(product.getName());
            updateProduct.setPrice(product.getPrice());

            updateProduct.getCategories().clear();
            for (var category : product.getCategories()) {
                updateProduct.addCategory(category);
            }
            return ProductMapper.toDto(productRepository.save(updateProduct));
        }

            throw new RuntimeException("Product not found with id " + id);
    }

    @Override
    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product not found with id " + id);
        }
    }


    // TODO få lige forklaret hvad fanden der foregår her!!!
    @Override
    public ProductDto addCategory(Long productId, CategoryDto categoryDto) {
        return null;
    }

    @Override
    public ProductDto removeCategory(Long productId, Long categoryId) {
        return null;
    }

    @Override
    public ProductDto updateCategory(Long id, ProductUpdateDto productUpdateDto) {
        return null;
    }
}

