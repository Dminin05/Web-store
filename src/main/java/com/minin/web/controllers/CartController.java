package com.minin.web.controllers;


import com.minin.web.dtos.ProductDto;
import com.minin.web.entities.Order;
import com.minin.web.entities.Product;
import com.minin.web.entities.User;
import com.minin.web.model.Cart;
import com.minin.web.model.CartItem;
import com.minin.web.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final ProductService productService;
    private final CartService cartService;
    private final OrderService orderService;
    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping("/showAll")
    public List<CartItem> getAllProductsInCart() {
        return cartService.getCurrentCart().getProductsInCart();
    }

    @GetMapping
    public Cart getCart() {
        return cartService.getCurrentCart();
    }

    @GetMapping("/add")
    public void addProduct (@RequestParam Long id) {
        cartService.addProductToCart(new ProductDto(productService.findProductById(id).get()));
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteProduct (@RequestParam Long id) {
        cartService.deleteProductFromCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/createOrder")
    public ResponseEntity<?> createOrder (@RequestParam String username) {
        Order order = new Order();
        int price = 0;
        List<Product> productsInOrder = new ArrayList<>();
        List<CartItem> cartItems = cartService.getCurrentCart().getProductsInCart();
        for (CartItem cartItem : cartItems) {
            price = price + cartItem.getPrice();
            for (int i = 0; i < cartItem.getQuantities(); i++) {
                productsInOrder.add(productService.findProductById(cartItem.getId()).get());
            }
        }
        User user = userService.findByUsername(username).get();
        order.setUser(user);
        order.setPrice(price);
        order.setProducts(productsInOrder);
        orderService.createOrder(order);
        cartService.deleteCart();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
