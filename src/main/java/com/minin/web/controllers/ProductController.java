package com.minin.web.controllers;

import com.minin.web.dtos.ProductDto;
import com.minin.web.model.Category;
import com.minin.web.model.Product;
import com.minin.web.service.CategoryService;
import com.minin.web.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/products")
    public List<Product> findAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/products/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return new ProductDto(productService.findProductById(id).get());
    }

    @PostMapping("/products")
    public ProductDto save(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).get();
        product.setCategory(category);
        productService.save(product);
        return new ProductDto(product);
    }

}
