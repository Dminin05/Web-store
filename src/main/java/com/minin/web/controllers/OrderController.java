package com.minin.web.controllers;

import com.minin.web.dtos.OrderDto;
import com.minin.web.dtos.ProductDto;
import com.minin.web.service.OrderService;
import com.minin.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @GetMapping
    public List<OrderDto> getAllOrders(@RequestParam String username) {
        return userService.findByUsername(username).get().getOrders().stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @GetMapping("/info")
    public List<ProductDto> getOrderListById(@RequestParam Long id) {
        return orderService.getOrderListById(id);
    }

}
