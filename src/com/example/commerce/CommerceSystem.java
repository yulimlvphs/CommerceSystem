package com.example.commerce;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    private List<Category> categories;
    private Cart cart;
    private Scanner scanner;

    public CommerceSystem(List<Category> categories) {
        this.categories = categories;
        this.cart = new Cart();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while(true) {
            printMainMenu();

            int input = scanner.nextInt();

            if(input == 0) {
                System.out.println("커머스 플랫폼을 종료합니다");
                return;
            }

            if(input >= 1 && input <= categories.size()) {
                Category selectCateGory = categories.get(input - 1);
                showCategory(selectCateGory);
            } else if (input == 4) {
                showCartAndOrder();
            } else if (input == 5) {
                cart.clear();
                System.out.println("진행중인 주문이 취소되었습니다. ");
            } else {
                System.out.println("유효하지 않은 메뉴 번호입니다.");
            }
        }
    }

    private void printMainMenu() {
        System.out.println();
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");

        for(int i = 0; i < categories.size(); i++) {
            System.out.printf(
                    "%d. %s%n",
                    i + 1,
                    categories.get(i).getCategoryName()
            );
        }

        System.out.println("0. 종료      | 프로그램 종료");

        if (!cart.isEmpty()) {
            System.out.println();
            System.out.println("[ 주문 관리 ]");
            System.out.println("4. 장바구니 확인    | 장바구니를 확인 후 주문합니다.");
            System.out.println("5. 주문 취소       | 진행중인 주문을 취소합니다.");
        }
    }

    private void showCategory(Category category) {
        printProducts(category);

        int input = scanner.nextInt();

        if (input == 0) {
            return;
        }

        List<Product> products = category.getProducts();

        if (input < 1 || input > products.size()) {
            System.out.println("유효하지 않은 상품 번호입니다.");
            return;
        }

        Product selectedProduct = products.get(input - 1);

        printSelectedProduct(selectedProduct);
        askAddToCart(selectedProduct);
    }

    private void printProducts(Category category) {
        System.out.println();
        System.out.println(" [ " + category.getCategoryName() + " 카테고리 ]");

        List<Product> products = category.getProducts();

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);

            System.out.printf(
                    "%d. %-14s | %,d원 | %s | 재고: %d개%n",
                    i + 1,
                    product.getName(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getStockQuantity()
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

    private void askAddToCart(Product product) {
        System.out.println();
        System.out.printf(
                "\"%s | %,d원 | %s\"%n",
                product.getName(),
                product.getPrice(),
                product.getDescription()
        );
        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");

        int input = scanner.nextInt();

        if (input == 1) {
            cart.addProduct(product, 1);
            System.out.println(product.getName() + "가 장바구니에 추가되었습니다. ");
        } else if (input == 2) {
            System.out.println("장바구니 추가를 취소했습니다. ");
        } else {
            System.out.println("유효하지 않은 입력입니다. ");
        }
    }

    private void showCartAndOrder() {
        System.out.println();
        System.out.println("아래와 같이 주문 하시겠습니까 ?");

        System.out.println();
        System.out.println(" [ 장바구니 내역 ] ");

        for(CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();

            System.out.printf(
                    "%s | %,d원 | %s | 수량: %d개%n",
                    product.getName(),
                    product.getPrice(),
                    product.getDescription(),
                    cartItem.getQuantity()
            );

            System.out.println();
            System.out.println(" [ 총 주문 금액 ] ");
            System.out.printf("%,d원%n", cart.calculateTotalPrice());
        }

        System.out.println();
        System.out.println("1.주문확정        2.메인으로 돌아가기");

        int input = scanner.nextInt();

        if(input == 1) {
            confirmOrder();
        } else if (input == 2) {
            System.out.println("메인으로 돌아갑니다. ");
        } else {
            System.out.println("유효하지 않은 입력입니다. ");
        }
    }

    private void confirmOrder() {
        for(CartItem item : cart.getItems()){
            Product product = item.getProduct();
            int beforeStock = product.getStockQuantity();

            product.decreaseStock(item.getQuantity());

            int afterStock = product.getStockQuantity();

            System.out.printf(
                    "%s 재고가 %d개 → %d개로 업데이트되었습니다.%n",
                    product.getName(),
                    beforeStock,
                    afterStock
            );
        }

        int totalPrice = cart.calculateTotalPrice();

        System.out.printf("주문이 완료되었습니다! 총 금액: %,d원%n", totalPrice);

        cart.clear();
    }

    private void cancelOrder() {
        if (cart.isEmpty()) {
            System.out.println("진행중인 주문이 없습니다.");
            return;
        }

        cart.clear();
        System.out.println("진행중인 주문이 취소되었습니다.");
    }
}