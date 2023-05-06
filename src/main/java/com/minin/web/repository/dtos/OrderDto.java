package com.minin.web.repository.dtos;

import com.minin.web.entities.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private String username;
    private int price;
    private List<ProductDto> productDtos;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.username = order.getUser().getUsername();
        this.price = order.getPrice();
        this.productDtos = order.getProducts().stream().map(ProductDto::new).collect(Collectors.toList());
    }
}
