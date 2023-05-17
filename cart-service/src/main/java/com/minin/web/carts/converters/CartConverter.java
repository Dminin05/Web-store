package com.minin.web.carts.converters;

import com.minin.web.api.dtos.CartDto;
import com.minin.web.carts.model.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {

    private final CartItemConverter cartItemConverter;

    public CartDto entityToDto(Cart c) {
        return new CartDto(c.getItems().stream().map(cartItemConverter::entityToDto).collect(Collectors.toList()), c.getTotalPrice());
    }
}
