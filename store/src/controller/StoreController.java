package controller;

import dto.Store;
import service.StoreService;

import java.util.Scanner;

public class StoreController {

    private final StoreService storeService = new StoreService();

    public void start() {
        Store store = new Store();
        while (true) {
            startPage(store);
            // 게임이 끝나면 매출 장부 출력
            storeService.printSheet(store);
        }
    }

    public void startPage(Store store) {
        Scanner sc = new Scanner(System.in);
        System.out.println("======편의점 타이쿤======\n"
                + "게임을 시작하려면 'start'을 입력해주세요.");
        String start = sc.next();

        if (start.equals("start")) {
            System.out.println("----☆ 게임시작 ☆----");

            // 게임 시작 정보를 출력
            storeService.startGameInfo(store);

            // 손님 구매 및 물건 구매 처리
            storeService.handlePurchase(store);

        } else {
            System.out.println("다시 입력해주세요.");
        }
    }
}