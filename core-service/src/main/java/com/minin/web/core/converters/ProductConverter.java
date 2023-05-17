package com.minin.web.core.converters;

import com.minin.web.api.dtos.ProductDto;
import com.minin.web.core.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    public ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setPrice(product.getPrice());
        productDto.setCategoryTitle(product.getCategory().getTitle());
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        return productDto;
    }

}
