package com.minin.web.core.controllers;

import com.minin.web.api.dtos.CartDto;
import com.minin.web.api.dtos.CartItemDto;
import com.minin.web.api.dtos.OrderDto;
import com.minin.web.api.dtos.ProductDto;
import com.minin.web.api.exceptions.ResourceNotFoundException;
import com.minin.web.core.converters.OrderConverter;
import com.minin.web.core.entities.Order;
import com.minin.web.core.entities.Product;
import com.minin.web.core.entities.User;
import com.minin.web.core.integrations.CartServiceIntegrations;
import com.minin.web.core.service.OrderService;
import com.minin.web.core.service.ProductService;
import com.minin.web.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;
    private final OrderConverter orderConverter;
    private final CartServiceIntegrations cartServiceIntegrations;

    @GetMapping
    public List<OrderDto> getAllOrders(@RequestParam String username) {
        return userService.findByUsername(username).get().getOrders().stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/info")
    public List<ProductDto> getOrderListById(@RequestParam Long id) {
        return orderService.getOrderListById(id);
    }

    @GetMapping("/createOrder")
    public ResponseEntity<?> createOrder (@RequestParam String username) {
        Order order = new Order();
        int price = 0;
        List<Product> productsInOrder = new ArrayList<>();
        CartDto cartDto = cartServiceIntegrations.getCurrentCart();
        List<CartItemDto> cartItems = cartDto.getItems();
        for (CartItemDto cartItem : cartItems) {
            price = price + cartItem.getPrice();
            for (int i = 0; i < cartItem.getQuantity(); i++) {
                productsInOrder.add(productService.findProductById(cartItem.getId()).get());
            }
        }
        User user = userService.findByUsername(username).get();
        order.setUser(user);
        order.setPrice(price);
        order.setProducts(productsInOrder);
        orderService.createOrder(order);
        cartServiceIntegrations.deleteCart();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
