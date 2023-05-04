package com.minin.web.model;

import com.minin.web.dtos.ProductDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItem {

    private Long id;
    private String title;
    private int pricePerProduct;
    private int quantities;
    private int price;

    public CartItem(ProductDto productDto, int quantities) {
        this.id = productDto.getId();
        this.title = productDto.getTitle();
        this.pricePerProduct = productDto.getPrice();
        this.price = productDto.getPrice();
        this.quantities = quantities;
    }

    public void updateQuantities(int value) {
        quantities += value;
        price = pricePerProduct*quantities;
    }





}
