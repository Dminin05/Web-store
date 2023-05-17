package com.minin.web.carts.controllers;


import com.minin.web.api.dtos.CartDto;
import com.minin.web.carts.converters.CartConverter;
import com.minin.web.carts.model.Cart;
import com.minin.web.carts.model.CartItem;
import com.minin.web.carts.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@CrossOrigin("*")
public class CartController {

    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/showAll")
    public List<CartItem> getAllProductsInCart() {
        return cartService.getCurrentCart().getProductsInCart();
    }

    @GetMapping
    public CartDto getCart() {
        return cartConverter.entityToDto(cartService.getCurrentCart());
    }

    @GetMapping("/add")
    public void addProduct (@RequestParam Long id) {
        cartService.addProductToCart(id);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteProduct (@RequestParam Long id) {
        cartService.deleteProductFromCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public void deleteCart() {
        cartService.deleteCart();
    }

}
