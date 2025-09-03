package ek.osnb.jpa.orders.service;

import ek.osnb.jpa.orders.model.Category;
import ek.osnb.jpa.orders.model.Product;
import ek.osnb.jpa.orders.repository.CategoryRepository;
import ek.osnb.jpa.orders.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepositroy, CategoryRepository categoryRepository) {
        this.productRepository = productRepositroy;
        this.categoryRepository = categoryRepository;
    }


    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new RuntimeException("Product not found");
        }
        return product.get();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product createProduct(Product product) {
        product.setId(null);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product existingProduct = findById(id);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());

        // Get category ids
        List<Long> categoryIds = new ArrayList<>();
        for (Category category : product.getCategories()) {
            categoryIds.add(category.getId());
        }

        // Find existing categories
        Set<Category> newCategories = new HashSet<>(categoryRepository.findAllById(categoryIds));

        // Update the categories
        existingProduct.getCategories().clear();
        existingProduct.getCategories().addAll(newCategories);

        return productRepository.save(existingProduct);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }









}

