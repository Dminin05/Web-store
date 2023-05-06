package com.minin.web.model;

import com.minin.web.repository.dtos.ProductDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItem {

    private Long id;
    private String title;
    private String categoryTitle;
    private int pricePerProduct;
    private int quantities;
    private int price;

    public CartItem(ProductDto productDto, int quantities) {
        this.id = productDto.getId();
        this.title = productDto.getTitle();
        this.categoryTitle = productDto.getCategoryTitle();
        this.pricePerProduct = productDto.getPrice();
        this.price = productDto.getPrice();
        this.quantities = quantities;
    }

    public void updateQuantities(int value) {
        quantities += value;
        price = pricePerProduct*quantities;
    }





}
