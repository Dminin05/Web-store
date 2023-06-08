package com.minin.web.core.converters;

import com.minin.web.api.dtos.OrderDto;
import com.minin.web.core.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {

    private final ProductConverter productConverter;

    public OrderDto entityToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setPrice(order.getPrice());
        orderDto.setUsername(order.getUsername());
        orderDto.setProductDtos(order.getProducts().stream().map(productConverter::entityToDto).collect(Collectors.toList()));
        return orderDto;
    }

}
