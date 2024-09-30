package service;

import controller.CustomerController;
import dto.ProductType;
import dto.Sales;
import dto.Store;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;


public class CustomerService{

    // 무인 편의점
    // 손님: 1 ~ 5명/ 상품, 개수 랜덤 구매
    private final Random random = new Random();
    private final Map<ProductType, Integer> totalPurchaseCount = new LinkedHashMap<>();
    private final Map<ProductType, Integer> totalPurchasePrice = new LinkedHashMap<>();
    private int totalPrice = 0;

    public ProductType getRandomProduct() {
        ProductType[] products = ProductType.values();
        return products[random.nextInt(products.length)];
    }

    public void purchase(Store store) {
        int num = random.nextInt(5) + 1;
        System.out.println("보유 금액: " + store.getVaultCash() + "원");
        System.out.println("손님 수: " + num);

        for (int i = 1; i < num + 1; i++) {
            Map<ProductType, Integer> purchase = new HashMap<>();
            int price = 0;
            int productCount = random.nextInt(3) + 1;
            new CustomerServiceThread().start();

            for (int j = 0; j < productCount; j++) {
                ProductType product = getRandomProduct();
                int quantity = random.nextInt(5) + 1;

                if (store.reduceProduct(product, quantity)) {
                    purchase.put(product, purchase.getOrDefault(product, 0) + quantity);
                    price += product.getPrice() * quantity;

                    totalPurchaseCount.put(product, totalPurchaseCount.getOrDefault(product, 0) + quantity);
                    totalPurchasePrice.put(product, product.getPrice());
                }
            }
            System.out.println();
            if(!purchase.isEmpty()) {
                store.addVaultCash(price);
                System.out.println("손님 " + i + ": " + purchase + ", 총 " + price + "원");
            }
        }
    }

    public Map<ProductType, Sales> getSalesData() {
        Map<ProductType, Sales> salesDataMap = new LinkedHashMap<>();

        for (ProductType product : totalPurchaseCount.keySet()) {
            int quantity = totalPurchaseCount.get(product);
            int price = totalPurchasePrice.get(product);
            int total = price * quantity;

            salesDataMap.put(product, new Sales(quantity, price, total));
        }
        return salesDataMap;
    }

    public int getTotalSalesAmount() {
        return totalPrice;
    }
}