package ek.osnb.jpa.orders.service;

import ek.osnb.jpa.orders.dto.CategoryDto;
import ek.osnb.jpa.orders.dto.CategoryMapper;
import ek.osnb.jpa.orders.dto.CategoryUpdateDto;
import ek.osnb.jpa.orders.dto.ProductDto;
import ek.osnb.jpa.orders.model.Category;
import ek.osnb.jpa.orders.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (var category : categories) {
            categoryDtos.add(CategoryMapper.toDto(category));
        }
        return categoryDtos;
    }


    @Override
    public CategoryDto getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return CategoryMapper.toDto(category.get());
        }
            throw new RuntimeException("Category not found with id " +id);
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = CategoryMapper.toEntity(categoryDto);
        category.setId(null);
        return CategoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            Category category = CategoryMapper.toEntity(categoryDto);
            Category updateCategory = existingCategory.get();
            updateCategory.setName(category.getName());
            updateCategory.setProducts(category.getProducts());
            for (var product : category.getProducts()) {
                updateCategory.addProduct(product);
            }
            return CategoryMapper.toDto(categoryRepository.save(updateCategory));
        }

        throw new RuntimeException("Category not found with id " + id);
    }

    @Override
    public void deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new RuntimeException("Category not found with id " + id);
        }

    }

    // TODO få lige forklaret hvad fanden der foregår her!!!
    @Override
    public CategoryDto addProduct(Long categoryId, ProductDto productDto) {
        return null;
    }

    @Override
    public CategoryDto removeProduct(Long categoryId, Long productId) {
        return null;
    }

    @Override
    public CategoryDto updateProduct(Long id, CategoryUpdateDto categoryUpdateDto) {
        return null;
    }

}

