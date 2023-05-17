package com.minin.web.carts.model;

import com.minin.web.api.dtos.ProductDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private List<CartItem> items = new ArrayList<>();
    private int totalPrice;

    public List<CartItem> getProductsInCart() {
        return items;
    }

    public void deleteCart() {
        items = new ArrayList<>();
        totalPrice = 0;
    }

    public void addProduct(ProductDto productDto) {
        for (CartItem cartItem : items) {
            if (cartItem.getId().equals(productDto.getId())) {
                cartItem.updateQuantities(1);
                updateTotalPrice();
                return;
            }
        }
        items.add(new CartItem(productDto, 1));
        updateTotalPrice();
    }

    public void deleteProduct(Long id) {
        if (items.removeIf(cartItem -> cartItem.getId().equals(id))) {
            updateTotalPrice();
        }
    }

    public void updateTotalPrice() {
        totalPrice = 0;
        for (CartItem cartItem : items) {
            totalPrice = totalPrice +  cartItem.getPricePerProduct()*cartItem.getQuantity();
        }
    }




}
