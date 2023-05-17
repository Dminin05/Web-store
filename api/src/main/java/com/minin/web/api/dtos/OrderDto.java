package com.minin.web.api.dtos;

import java.util.List;

public class OrderDto {

    private Long id;
    private String username;
    private int price;
    private List<ProductDto> productDtos;

//    public OrderDto(Order order) {
//        this.id = order.getId();
//        this.username = order.getUser().getUsername();
//        this.price = order.getPrice();
//        this.productDtos = order.getProducts().stream().map(ProductDto::new).collect(Collectors.toList());
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<ProductDto> getProductDtos() {
        return productDtos;
    }

    public void setProductDtos(List<ProductDto> productDtos) {
        this.productDtos = productDtos;
    }
}
