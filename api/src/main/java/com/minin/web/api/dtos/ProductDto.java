package com.minin.web.api.dtos;

import java.util.Objects;

public class ProductDto {

    private Long id;
    private String title;
    private int price;
    private String categoryTitle;
    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return price == that.price && quantity == that.quantity && Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(categoryTitle, that.categoryTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, categoryTitle, quantity);
    }
}
