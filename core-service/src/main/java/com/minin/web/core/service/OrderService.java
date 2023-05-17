package com.minin.web.core.service;

import com.minin.web.api.dtos.ProductDto;
import com.minin.web.core.converters.OrderConverter;
import com.minin.web.core.entities.Order;
import com.minin.web.core.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public void createOrder(Order order) {
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

}
