package com.example.commerce;
import java.util.List;

public class Category {

    private String categoryName;
    private List<Product> products;

    public Category(String categoryName, List<Product> products) {
        this.categoryName = categoryName;
        this.products = products;
    }

    public void addProduct(Product product) {
        if(hasProductName(product.getName())) {
            throw new IllegalArgumentException("이미 존재하는 상품입니다. ");
        }
    }

    public boolean hasProductName(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public Product findProductByName(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<Product> getProducts() {
        return products;
    }
}
