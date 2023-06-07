package com.minin.web.core.integrations;

import com.minin.web.api.dtos.CartDto;
import com.minin.web.api.exceptions.ResourceNotFoundException;
import com.minin.web.core.properties.CartServiceIntegrationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CartServiceIntegrations {

    private final WebClient cartServiceWebClient;

    public CartDto getCurrentCart() {
        return cartServiceWebClient.get()
                .uri("/api/v1/cart/")
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == httpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Корзина не найдена"))
                )
                .bodyToMono(CartDto.class)
                .block();
    }

    public Void deleteCart() {
        return cartServiceWebClient.delete()
                .uri("/api/v1/cart")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
