package com.example.commerce;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    public int getTotalPrice() {
        return product.getPrice() * quantity;
    }

    public String getDisplayInfo() {
        return String.format(
                "%s | %,d원 | %s | 수량: %d개",
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                quantity
        );
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

}
