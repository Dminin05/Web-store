package com.minin.web.model;

import com.minin.web.dtos.ProductDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private List<CartItem> cart = new ArrayList<>();
    private int totalPrice;

    public List<CartItem> getProductsInCart() {
        return cart;
    }

    public void addProduct(ProductDto productDto) {
        for (CartItem cartItem : cart) {
            if (cartItem.getId().equals(productDto.getId())) {
                cartItem.updateQuantities(1);
                updateTotalPrice();
                return;
            }
        }
        cart.add(new CartItem(productDto, 1));
        updateTotalPrice();
    }

    public void deleteProduct(Long id) {
        if (cart.removeIf(cartItem -> cartItem.getId().equals(id))) {
            updateTotalPrice();
        }
    }

    public void updateTotalPrice() {
        totalPrice = 0;
        for (CartItem cartItem : cart) {
            totalPrice = totalPrice +  cartItem.getPricePerProduct()*cartItem.getQuantities();
        }
    }




}
