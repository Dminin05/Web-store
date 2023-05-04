package com.minin.web.controllers;


import com.minin.web.dtos.ProductDto;
import com.minin.web.model.Cart;
import com.minin.web.model.CartItem;
import com.minin.web.service.CartService;
import com.minin.web.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("/showAll")
    public List<CartItem> getAllProductsInCart() {
        return cartService.getCurrentCart().getProductsInCart();
    }

    @GetMapping
    public Cart getCart() {
        return cartService.getCurrentCart();
    }

    @GetMapping("/add")
    public ResponseEntity<?> addProduct (@RequestParam Long id) {
        cartService.addProductToCart(new ProductDto(productService.findProductById(id).get()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteProduct (@RequestParam Long id) {
        cartService.deleteProductFromCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
