package com.minin.web.carts.model;

import com.minin.web.api.dtos.ProductDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItem {

    private Long id;
    private String title;
    private String categoryTitle;
    private int pricePerProduct;
    private int quantity;
    private int price;

    public CartItem(ProductDto productDto, int quantity) {
        this.id = productDto.getId();
        this.title = productDto.getTitle();
        this.categoryTitle = productDto.getCategoryTitle();
        this.pricePerProduct = productDto.getPrice();
        this.price = productDto.getPrice();
        this.quantity = quantity;
    }

    public void updateQuantities(int value) {
        quantity += value;
        price = pricePerProduct*quantity;
    }





}
