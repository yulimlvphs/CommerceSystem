package com.example.commerce;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    private List<Category> categories;
    Scanner scanner = new Scanner(System.in);

    public CommerceSystem(List<Category> categories) {
        this.categories = categories;
    }

    public void start() {
        while (true) {
            printMainMenu();

            int input = scanner.nextInt();

            if (input == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                return;
            }

            Category selectedCategory = categories.get(input - 1);

            showCategory(selectedCategory);
        }
    }

    private void printMainMenu() {
        System.out.println();
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");

        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);

            System.out.printf(
                    "%d. %s%n",
                    i + 1,
                    category.getCategoryName()
            );
        }

        System.out.println("0. 종료      | 프로그램 종료");
    }

    private void showCategory(Category category) {
        printProducts(category);

        int input = scanner.nextInt();

        if (input == 0) {
            return;
        }

        List<Product> products = category.getProducts();
        Product selectedProduct = products.get(input - 1);

        printSelectedProduct(selectedProduct);
    }

    private void printProducts(Category category) {
        System.out.println();
        System.out.println("[ " + category.getCategoryName() + " 카테고리 ]");

        List<Product> products = category.getProducts();

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);

            System.out.printf(
                    "%d. %-14s | %,d원 | %s%n",
                    i + 1,
                    product.getName(),
                    product.getPrice(),
                    product.getDescription()
            );
        }

        System.out.println("0. 뒤로가기");
    }

    private void printSelectedProduct(Product product) {
        System.out.printf(
                "선택한 상품: %s | %,d원 | %s | 재고: %d개%n",
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getStockQuantity()
        );
    }
}

