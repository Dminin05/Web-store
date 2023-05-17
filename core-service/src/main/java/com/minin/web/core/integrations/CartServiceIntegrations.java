package com.minin.web.core.integrations;

import com.minin.web.api.dtos.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegrations {

    private final RestTemplate restTemplate;

    public Optional<CartDto> getCurrentCart() {
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8190/market-carts/api/v1/cart", CartDto.class));
    }

    public void deleteCart() {
        restTemplate.delete("http://localhost:8190/market-carts/api/v1/cart");
    }

}
