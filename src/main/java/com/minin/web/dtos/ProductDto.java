package com.minin.web.dtos;

import com.minin.web.entities.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;
    @NotNull(message = "У товара должно быть название")
    private String title;
    @NotNull(message = "У товара должна быть цена")
    @Min(1)
    private int price;
    @NotNull(message = "У товара должна быть категория")
    private String categoryTitle;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.categoryTitle = product.getCategory().getTitle();
    }
}
