package com.minin.web.carts.integrations;

import com.minin.web.api.dtos.ProductDto;
import com.minin.web.carts.properties.ProductServiceIntegrationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
@RequiredArgsConstructor
public class ProductServiceIntegrations {

    private final ProductServiceIntegrationProperties productServiceIntegrationProperties;

    private final WebClient productServiceWebClient;

    public ProductDto getProductById(Long id) {
        return productServiceWebClient.get()
                .uri("/api/v1/products/" + id)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
    }

}
