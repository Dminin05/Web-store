package com.minin.web.model;

import com.minin.web.dtos.ProductDto;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class Cart {

    private List<ProductDto> productList;

    public Cart() {
        productList = new ArrayList<>();
    }

}
