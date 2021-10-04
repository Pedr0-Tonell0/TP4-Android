package com.learn2crack.swipeview;

public class Products {
    private Integer Id;
    private String Product;
    private Integer Stock;
    private Integer Category;
    private String CategoryName;
    public Products() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public Integer getStock() {
        return Stock;
    }

    public void setStock(Integer stock) {
        Stock = stock;
    }

    public Integer getCategory() {
        return Category;
    }

    public void setCategory(Integer category) {
        Category = category;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
