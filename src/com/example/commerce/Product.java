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
        return stockQuantity >= quantity;
    }

    void decreaseStock(int quantity) {
        if(!hasStock(quantity)) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }

        stockQuantity -= quantity;
    }

    public String getDisplayInfo() {
        return String.format(
                "%s | %,d원 | %s",
                name,
                price,
                description
        );
    }

    public String getDisplayInfoWithStock() {
        return String.format(
                "%s | %,d원 | %s | 재고: %d개",
                name,
                price,
                description,
                stockQuantity
        );
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

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}