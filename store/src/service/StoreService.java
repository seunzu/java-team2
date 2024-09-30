package service;

import controller.CustomerController;
import controller.DisplayController;
import dto.ProductType;
import dto.Sales;
import dto.Store;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class StoreService {

    private final CustomerController customerController = CustomerController.getInstance();
    private final DisplayController displayController = DisplayController.getInstance();

    // 게임 시작 시 정보 출력
    public void startGameInfo(Store store) {
        System.out.println("DAY " + store.getDayCount());
        System.out.println("오늘의 시재금 : " + store.getVaultCash() + "원\n"
                + "오늘의 임대료 : " + store.getRent() + "원\n");

        // 재고 현황 출력
        System.out.println("==== 재고 현황 ====");
        Map<ProductType, Integer> stockList = store.getProducts();
        for (Map.Entry<ProductType, Integer> entry : stockList.entrySet()) {
            ProductType key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + ": " + value + "개");
        }
    }

    // 매출 장부 출력
    public void printSheet(Store store) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String nowDate = format.format(now);

        // 매출 내역과 총 매출액 가져오기
        Map<ProductType, Sales> sales = customerController.getSalesData();
        int totalSalesAmount = customerController.getTotalSalesAmount();

        System.out.println(
                "================장부=================\n"
                        + "=====================================\n"
                        + "            일시: " + nowDate + "\n"
                        + "            운영 시간: 30초\n"
                        + "            시재금: " + store.getVaultCash() + "\n"
                        + "=====================================\n"
                        + "판매내역\n"
                        + "  이름      | 개수 |  금액  |  총액\n"
                        + "--------------------------------------"
        );

        // 실제 판매 내역 출력
        for (Map.Entry<ProductType, Sales> entry : sales.entrySet()) {
            ProductType product = entry.getKey();
            Sales data = entry.getValue();
            System.out.printf("  %-8s |  %2d   | %4d원  | %5d원\n", product, data.getQuantity(), data.getPrice(), data.getTotalAmount());
        }

        System.out.println("=====================================");
        System.out.printf("매출액\n                              %5d원\n", totalSalesAmount);
        System.out.println("=====================================");

        // 임대료와 순수익 계산
        int rent = store.getRent();
        int netProfit = totalSalesAmount - rent;

        System.out.printf("임대료\n                           -%d원\n", rent);
        System.out.println("=====================================");
        System.out.printf("순수익\n                              %5d원\n", netProfit);
        System.out.println("=====================================");
        System.out.printf("보유 금액\n                           %d원\n", store.getVaultCash());

        // 재고 현황 출력
        System.out.println("=====================================\n재고");
        System.out.println("  이름      | 개수 |  금액  |  총액\n-------------------------------------");
        for (Map.Entry<ProductType, Integer> entry : store.getProducts().entrySet()) {
            ProductType product = entry.getKey();
            int quantity = entry.getValue();
            int price = product.getPrice();
            System.out.printf("  %-8s |  %2d   | %4d원  | %5d원\n", product, quantity, price, price * quantity);
        }
        System.out.println("=====================================");
    }

    // 손님 구매 및 물건 구매 처리
    public void handlePurchase(Store store) {
        // 물건 구매
        displayController.start(store);
        // 손님 구매
        customerController.start(store);
    }
}