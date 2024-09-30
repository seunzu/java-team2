package service;

import dto.ProductType;
import dto.Store;

import java.util.Scanner;

public class DisplayService {
    public void controlStorage(Store store) {
        Scanner sc = new Scanner(System.in);
        TimeService setTime = new TimeService();
        setTime.start();
        while (true) {
            System.out.println("\n물품 진열 재고: " + store.listProducts());
            //System.out.println("창고 재고 : " +store.listStorage());
            System.out.println("발주 가능한 내역은 1.ramen 2.snack 3.drink");
            System.out.println("발주하기 ex) ramen 3, exit: 가계 열기");
            String move = sc.nextLine();
            if (!setTime.isRunning()) {
                return;
            }
            if(move.equals("exit")) {
                setTime.stopRunning();
                return;
            } else {
                try {
                    String[] products = move.split(" ");
                    String type = products[0].toUpperCase();
                    int quantity = Integer.parseInt(products[1]);
                    ProductType productType = ProductType.valueOf(type);
                    if(productType != null){
                        store.orderProduct(productType, quantity);
                        store.placeProduct();
                        System.out.println("주문 물품 : " + productType + " 수량 :" + quantity);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("물품명 및 수량이 옳바르지 않습니다.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("입력양식이 옳바르지 않습니다.");
                }
            }
        }
    }
}
