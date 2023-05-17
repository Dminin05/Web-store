package com.minin.web.carts.integrations;

import com.minin.web.api.dtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegrations {

    private final RestTemplate restTemplate;

    public ProductDto getProductById(Long id) {
        return restTemplate.getForObject("http://localhost:8189/market/api/v1/products/" + id,  ProductDto.class);
    }

}
