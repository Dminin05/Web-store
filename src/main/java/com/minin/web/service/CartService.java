package com.minin.web.service;


import com.minin.web.dtos.ProductDto;
import com.minin.web.model.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final Cart cart;

    public List<ProductDto> getProducts() {
        return cart.getProductList();
    }

    public void addProduct(ProductDto productDto) {
        cart.getProductList().add(productDto);
    }

    public void deleteProduct(ProductDto productDto) {
        cart.getProductList().remove(productDto);
    }




}
