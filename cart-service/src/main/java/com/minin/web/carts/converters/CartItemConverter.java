package com.minin.web.carts.converters;

import com.minin.web.api.dtos.CartItemDto;
import com.minin.web.carts.model.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemConverter {
    public CartItemDto entityToDto(CartItem c) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(c.getId());
        cartItemDto.setTitle(c.getTitle());
        cartItemDto.setPrice(c.getPrice());
        cartItemDto.setCategoryTitle(c.getCategoryTitle());
        cartItemDto.setPricePerProduct(c.getPricePerProduct());
        cartItemDto.setQuantity(c.getQuantity());
        return cartItemDto;
    }
}
