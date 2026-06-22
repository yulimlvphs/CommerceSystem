package com.example.commerce;

import java.util.List;
import java.util.Scanner;

public class AdminService {
    private List<Category> categories;
    private Cart cart;
    private Scanner scanner;

    public AdminService(List<Category> categories, Cart cart, Scanner scanner) {
        this.categories = categories;
        this.cart = cart;
        this.scanner = scanner;
    }

    public void startAdminMode() {
        while (true) {
            printAdminMenu();

            int input = scanner.nextInt();
            scanner.nextLine();

            if (input == 0) {
                System.out.println("메인으로 돌아갑니다.");
                return;
            }

            if (input == 1) {
                addProduct();
            } else if (input == 2) {
                updateProduct();
            } else if (input == 3) {
                deleteProduct();
            } else if (input == 4) {
                printAllProducts();
            } else {
                System.out.println("유효하지 않은 관리자 메뉴 번호입니다.");
            }
        }
    }

    private void printAdminMenu() {
        System.out.println();
        System.out.println("[ 관리자 모드 ]");
        System.out.println("1. 상품 추가");
        System.out.println("2. 상품 수정");
        System.out.println("3. 상품 삭제");
        System.out.println("4. 전체 상품 현황");
        System.out.println("0. 메인으로 돌아가기");
    }

    private void addProduct() {
        Category category = selectCategory();

        if (category == null) {
            return;
        }

        System.out.println();
        System.out.println("[ " + category.getCategoryName() + " 카테고리에 상품 추가 ]");

        System.out.print("상품명을 입력해주세요: ");
        String name = scanner.nextLine();

        if (category.hasProductName(name)) {
            System.out.println("같은 카테고리에 이미 존재하는 상품명입니다.");
            return;
        }

        System.out.print("가격을 입력해주세요: ");
        int price = scanner.nextInt();
        scanner.nextLine();

        System.out.print("상품 설명을 입력해주세요: ");
        String description = scanner.nextLine();

        System.out.print("재고수량을 입력해주세요: ");
        int stockQuantity = scanner.nextInt();
        scanner.nextLine();

        Product newProduct = new Product(name, price, description, stockQuantity);

        System.out.println();
        System.out.println(newProduct.getDisplayInfoWithStock());
        System.out.println("위 정보로 상품을 추가하시겠습니까?");
        System.out.println("1. 확인    2. 취소");

        int input = scanner.nextInt();
        scanner.nextLine();

        if (input == 1) {
            category.addProduct(newProduct);
            System.out.println("상품이 성공적으로 추가되었습니다!");
        } else {
            System.out.println("상품 추가를 취소했습니다.");
        }
    }

    private void updateProduct() {
        System.out.print("수정할 상품명을 입력해주세요: ");
        String productName = scanner.nextLine();

        Product product = findProductByName(productName);

        if (product == null) {
            System.out.println("해당 상품을 찾을 수 없습니다.");
            return;
        }

        System.out.println("현재 상품 정보: " + product.getDisplayInfoWithStock());

        System.out.println();
        System.out.println("수정할 항목을 선택해주세요:");
        System.out.println("1. 가격");
        System.out.println("2. 설명");
        System.out.println("3. 재고수량");

        int input = scanner.nextInt();
        scanner.nextLine();

        if (input == 1) {
            int oldPrice = product.getPrice();

            System.out.printf("현재 가격: %,d원%n", oldPrice);
            System.out.print("새로운 가격을 입력해주세요: ");

            int newPrice = scanner.nextInt();
            scanner.nextLine();

            product.setPrice(newPrice);

            System.out.printf(
                    "%s의 가격이 %,d원 → %,d원으로 수정되었습니다.%n",
                    product.getName(),
                    oldPrice,
                    newPrice
            );
        } else if (input == 2) {
            String oldDescription = product.getDescription();

            System.out.println("현재 설명: " + oldDescription);
            System.out.print("새로운 설명을 입력해주세요: ");

            String newDescription = scanner.nextLine();

            product.setDescription(newDescription);

            System.out.println(product.getName() + "의 설명이 수정되었습니다.");
        } else if (input == 3) {
            int oldStock = product.getStockQuantity();

            System.out.println("현재 재고수량: " + oldStock + "개");
            System.out.print("새로운 재고수량을 입력해주세요: ");

            int newStock = scanner.nextInt();
            scanner.nextLine();

            product.setStockQuantity(newStock);

            System.out.printf(
                    "%s의 재고수량이 %d개 → %d개로 수정되었습니다.%n",
                    product.getName(),
                    oldStock,
                    newStock
            );
        } else {
            System.out.println("유효하지 않은 수정 항목입니다.");
        }
    }

    private void deleteProduct() {
        System.out.print("삭제할 상품명을 입력해주세요: ");
        String productName = scanner.nextLine();

        Category category = findCategoryByProductName(productName);

        if (category == null) {
            System.out.println("해당 상품을 찾을 수 없습니다.");
            return;
        }

        Product product = category.findProductByName(productName);

        System.out.println("삭제할 상품 정보: " + product.getDisplayInfoWithStock());
        System.out.println("정말 삭제하시겠습니까?");
        System.out.println("1. 확인    2. 취소");

        int input = scanner.nextInt();
        scanner.nextLine();

        if (input == 1) {
            category.removeProduct(product);
            cart.removeProduct(product);

            System.out.println(product.getName() + " 상품이 삭제되었습니다.");
            System.out.println("장바구니에 해당 상품이 있었다면 함께 제거되었습니다.");
        } else {
            System.out.println("상품 삭제를 취소했습니다.");
        }
    }

    private void printAllProducts() {
        System.out.println();
        System.out.println("[ 전체 상품 현황 ]");

        for (Category category : categories) {
            System.out.println();
            System.out.println("[ " + category.getCategoryName() + " ]");

            List<Product> products = category.getProducts();

            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);

                System.out.printf(
                        "%d. %s%n",
                        i + 1,
                        product.getDisplayInfoWithStock()
                );
            }
        }
    }

    private Category selectCategory() {
        System.out.println("어느 카테고리에 상품을 추가하시겠습니까?");

        for (int i = 0; i < categories.size(); i++) {
            System.out.printf(
                    "%d. %s%n",
                    i + 1,
                    categories.get(i).getCategoryName()
            );
        }

        int input = scanner.nextInt();
        scanner.nextLine();

        if (input < 1 || input > categories.size()) {
            System.out.println("유효하지 않은 카테고리 번호입니다.");
            return null;
        }

        return categories.get(input - 1);
    }

    private Product findProductByName(String productName) {
        for (Category category : categories) {
            Product product = category.findProductByName(productName);

            if (product != null) {
                return product;
            }
        }

        return null;
    }

    private Category findCategoryByProductName(String productName) {
        for (Category category : categories) {
            Product product = category.findProductByName(productName);

            if (product != null) {
                return category;
            }
        }

        return null;
    }
}