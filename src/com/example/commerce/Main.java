package com.example.commerce;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Product> electronics = new ArrayList<>();
        electronics.add(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 10));
        electronics.add(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 8));
        electronics.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 5));
        electronics.add(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 15));

        List<Product> clothes = new ArrayList<>();
        clothes.add(new Product("반팔 티셔츠", 25000, "면 100% 티셔츠", 30));
        clothes.add(new Product("청바지", 59000, "슬림핏 청바지", 15));

        List<Product> foods = new ArrayList<>();
        foods.add(new Product("사과", 3000, "국내산 사과", 50));
        foods.add(new Product("우유", 2500, "1L 우유", 20));

        Category electronicCategory = new Category("전자제품", electronics);
        Category clothesCategory = new Category("의류", clothes);
        Category foodCategory = new Category("식품", foods);

        List<Category> categories = new ArrayList<>();

        categories.add(electronicCategory);
        categories.add(clothesCategory);
        categories.add(foodCategory);

        CommerceSystem commerceSystem = new CommerceSystem(categories);

        commerceSystem.start();
    }
}