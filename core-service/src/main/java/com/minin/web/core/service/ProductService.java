package com.minin.web.core.service;

import com.minin.web.api.dtos.ProductDto;
import com.minin.web.api.exceptions.ResourceNotFoundException;
import com.minin.web.core.entities.Category;
import com.minin.web.core.entities.Product;
import com.minin.web.core.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;


    public Page<Product> findAllProducts(int pageIndex, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Transactional
    public void updateProduct(ProductDto productDto) {
        Product product = findProductById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Product with id = " + productDto.getId() + " not found"));
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        if (!product.getCategory().getTitle().equals(productDto.getCategoryTitle())) {
            Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category with title = " + productDto.getCategoryTitle() + " not found"));
            product.setCategory(category);
        }
    }


}
