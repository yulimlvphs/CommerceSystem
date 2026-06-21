package com.example.commerce;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    private List<Product> products;

    public CommerceSystem(List<Product> products) {
        this.products = products;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);

            System.out.printf(
                    "%d. %-14s | %,d원 | %s%n",
                    i + 1,
                    product.name,
                    product.price,
                    product.description
            );
        }

        System.out.println("0. 종료           | 프로그램 종료");

        int input = scanner.nextInt();

        if (input == 0) {
            System.out.println();
            System.out.println("커머스 플랫폼을 종료합니다.");
        } else {
            System.out.println();
            System.out.println(input + "번 상품을 선택했습니다.");
        }
    }
}

