package com.minin.web.controllers;

import com.minin.web.dtos.ProductDto;
import com.minin.web.exceptions.ResourceNotFoundException;
import com.minin.web.model.Category;
import com.minin.web.model.Product;
import com.minin.web.service.CategoryService;
import com.minin.web.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public Page<ProductDto> findAllProducts(@RequestParam(defaultValue = "1", name = "p") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAllProducts(pageIndex - 1, 10).map(ProductDto::new);
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        Product product = productService.findProductById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id = " + id + " not found."));
        return new ProductDto(product);
    }

    @PostMapping
    public ProductDto save(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category with title = " + productDto.getCategoryTitle() + " not found."));
        product.setCategory(category);
        productService.save(product);
        return new ProductDto(product);
    }

    @GetMapping("/delete")
    public void delete(@RequestParam Long id) {
        productService.delete(productService.findProductById(id).get());
    }

    @PutMapping
    public void updateProduct(@RequestBody ProductDto productDto) {
        productService.updateProduct(productDto);
    }

}
