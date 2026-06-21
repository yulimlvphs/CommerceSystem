package com.example.commerce;

public class Product {
    private String name;
    private int price;
    private String description;
    private int stockQuantity;

    public Product(String name, int price, String description, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stockQuantity = stockQuantity;
    }

    boolean hasStock(int quantity){
        return stockQuantity >= quantity ? true : false;
    }

    void decreaseStock(int quantity) {
        if(!hasStock(quantity)) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }

        stockQuantity -= quantity;
    }

    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }
}