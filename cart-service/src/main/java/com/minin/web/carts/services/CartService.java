package com.minin.web.carts.services;

import com.minin.web.api.dtos.ProductDto;
import com.minin.web.api.exceptions.ResourceNotFoundException;
import com.minin.web.carts.integrations.ProductServiceIntegrations;
import com.minin.web.carts.model.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductServiceIntegrations productServiceIntegrations;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void addProductToCart(Long id) {
        ProductDto productDto = productServiceIntegrations.getProductById(id);
        cart.addProduct(productDto);
    }

    public void deleteProductFromCart(Long id) {
        cart.deleteProduct(id);
    }

    public void deleteCart() {
        cart.deleteCart();
    }

}
