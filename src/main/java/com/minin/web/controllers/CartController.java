package com.minin.web.controllers;


import com.minin.web.dtos.ProductDto;
import com.minin.web.model.Product;
import com.minin.web.service.CartService;
import com.minin.web.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProductsInCart() {
        return cartService.getProducts();
    }

    @GetMapping("/add")
    public ResponseEntity<?> addProduct (@RequestParam Long id) {
        cartService.addProduct(new ProductDto(productService.findProductById(id).get()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteProduct (@RequestParam Long id) {
        cartService.deleteProduct(new ProductDto(productService.findProductById(id).get()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
