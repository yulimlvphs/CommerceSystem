package com.example.commerce;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addProduct(Product product, int quantity) {
        if(!product.hasStock(quantity)) {
            System.out.println("재고가 부족합니다. ");
            return;
        }

        for(CartItem item : items) { // 같은 제품 수량 추가
            if(item.getProduct() == product) {
                item.increaseQuantity(quantity);
                return;
            }
        }

        items.add(new CartItem(product, quantity));
    }

    public int calculateTotalPrice() {
        int total = 0;

        for(CartItem item : items) {
            total += item.getTotalPrice();
        }

        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }

    public List<CartItem> getItems() {
        return items;
    }
}
