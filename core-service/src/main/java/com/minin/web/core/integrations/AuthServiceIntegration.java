package com.minin.web.core.integrations;

import com.minin.web.api.dtos.CartDto;
import com.minin.web.api.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthServiceIntegration {

//    private final WebClient authServiceWebClient;
//
//    public CartDto getCurrentCart() {
//        return authServiceWebClient.get()
//                .uri("/api/v1/cart/")
//                .retrieve()
//                .onStatus(
//                        httpStatus -> httpStatus.value() == httpStatus.NOT_FOUND.value(),
//                        clientResponse -> Mono.error(new ResourceNotFoundException("Корзина не найдена"))
//                )
//                .bodyToMono(CartDto.class)
//                .block();
//    }

}
