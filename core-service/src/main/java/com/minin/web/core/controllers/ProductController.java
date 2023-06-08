package com.minin.web.core.controllers;

import com.minin.web.api.dtos.ProductDto;
import com.minin.web.api.exceptions.ResourceNotFoundException;
import com.minin.web.api.exceptions.ValidationException;
import com.minin.web.core.converters.ProductConverter;
import com.minin.web.core.entities.Category;
import com.minin.web.core.entities.Product;
import com.minin.web.core.service.CategoryService;
import com.minin.web.core.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
//@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductConverter productConverter;

    @GetMapping
    public Page<ProductDto> findAllProducts(@RequestParam(defaultValue = "1", name = "p") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAllProducts(pageIndex - 1, 10).map(productConverter::entityToDto);
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        Product product = productService.findProductById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id = " + id + " not found."));
        return productConverter.entityToDto(product);
    }

    @PostMapping
    public ProductDto save(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Ошибка валидации");
        }
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category with title = " + productDto.getCategoryTitle() + " not found."));
        product.setCategory(category);
        productService.save(product);
        return productConverter.entityToDto(product);
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
