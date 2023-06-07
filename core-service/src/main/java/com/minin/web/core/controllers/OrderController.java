package com.minin.web.core.controllers;

import com.minin.web.api.dtos.CartDto;
import com.minin.web.api.dtos.CartItemDto;
import com.minin.web.api.dtos.OrderDto;
import com.minin.web.api.dtos.ProductDto;
import com.minin.web.core.converters.OrderConverter;
import com.minin.web.core.entities.Order;
import com.minin.web.core.entities.Product;
import com.minin.web.core.integrations.CartServiceIntegrations;
import com.minin.web.core.service.OrderService;
import com.minin.web.core.service.ProductService;
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
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @GetMapping
    public List<OrderDto> getAllOrders(@RequestHeader String username) {
        return orderService.getOrdersByUsername(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/info")
    public List<ProductDto> getOrderListById(@RequestParam Long id) {
        return orderService.getOrderListById(id);
    }

    @GetMapping("/createOrder")
    public ResponseEntity<?> createOrder (@RequestHeader String username) {
//        Order order = new Order();
//        int price = 0;
//        List<Product> productsInOrder = new ArrayList<>();
//        CartDto cartDto = cartServiceIntegrations.getCurrentCart();
//        List<CartItemDto> cartItems = cartDto.getItems();
//        for (CartItemDto cartItem : cartItems) {
//            price = price + cartItem.getPrice();
//            for (int i = 0; i < cartItem.getQuantity(); i++) {
//                productsInOrder.add(productService.findProductById(cartItem.getId()).get());
//            }
//        }
//        User user = userService.findByUsername(username).get();
//        order.setUser(user);
//        order.setPrice(price);
//        order.setProducts(productsInOrder);
//        orderService.createOrder(order);
//        cartServiceIntegrations.deleteCart();
        orderService.createOrder(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
