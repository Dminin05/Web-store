package com.minin.web.core.service;

import com.minin.web.api.dtos.CartDto;
import com.minin.web.api.dtos.CartItemDto;
import com.minin.web.api.dtos.ProductDto;
import com.minin.web.core.converters.OrderConverter;
import com.minin.web.core.entities.Order;
import com.minin.web.core.entities.Product;
import com.minin.web.core.integrations.CartServiceIntegrations;
import com.minin.web.core.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;
    private final CartServiceIntegrations cartServiceIntegrations;
    private final ProductService productService;


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public void createOrder(String username) {
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
        order.setUsername(username);
        order.setPrice(price);
        order.setProducts(productsInOrder);
        cartServiceIntegrations.deleteCart();
        orderRepository.save(order);
    }

    public List<ProductDto> getOrderListById(Long id) {
        List<ProductDto> products = new ArrayList<>();
        Map<ProductDto, Integer> map = new HashMap<>();
        List<ProductDto> productDtos = orderConverter.entityToDto(getOrderById(id).get()).getProductDtos();
        for (ProductDto p : productDtos) {
            if (map.containsKey(p)) {
                map.put(p, map.get(p)+1);
            } else {
                map.put(p, 1);
            }
        }
        for (Map.Entry<ProductDto, Integer> entry : map.entrySet()) {
            ProductDto productDto = entry.getKey();
            productDto.setQuantity(entry.getValue());
            products.add(productDto);
        }
        return products;
    }

    public List<Order> getOrdersByUsername(String username){
        return orderRepository.findOrdersByUsername(username);
    }

}
