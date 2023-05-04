package com.minin.web.service;

import com.minin.web.dtos.ProductDto;
import com.minin.web.model.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void addProductToCart(ProductDto productDto) {
        cart.addProduct(productDto);
    }

    public void deleteProductFromCart(Long id) {
        cart.deleteProduct(id);
    }

}
