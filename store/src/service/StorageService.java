package service;

import dto.OrderedProduct;
import dto.ProductType;
import dto.Storage;

import java.util.Map;

public class StorageService {
    // 발주했을 때 큐(Storage.order)에 저장, 돈 차감
    public int orderProduct(ProductType product, int orderCount, int vaultCash, Storage storage) {
        String productName = "";
        switch (product) {
            case RAMEN:
                productName = "라면";
                break;
            case SNACK:
                productName = "과자";
                break;
            case DRINK:
                productName = "음료";
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(product));
        }
        // 살 수 있는 만큼만 주문
        int totalCost = product.getPrice() * orderCount;
        if (totalCost <= vaultCash) {
            System.out.println(productName+" "+orderCount+"개를 주문하였습니다.");
            storage.addOrder(new OrderedProduct(product, orderCount));
            return totalCost;
        }
        else{
            System.out.println("소지한 금액 내에서 주문해주세요.");
            return 0;
        }
    }

    // 큐(Storage.order)에 넣은 값을 빼서 진열대(Store.products)와 창고(Storage.storage)에 저장
    public void placeProduct(Map<ProductType, Integer> products, int storeCapacity, Storage storage){
        while(!storage.getOrders().isEmpty()){
            OrderedProduct order = storage.getOrders().poll();
            ProductType type = order.getProductType();
            int num = order.getNum();
            int prevAmount = products.get(type);
            System.out.println(prevAmount);

            if (prevAmount + num > storeCapacity) {
                // prevAmount + num - storeCapacity를 창고에 set
                // 진열대를 storeCapacity로 set
                int excess = prevAmount + num - storeCapacity;

                // 창고storage에 저장
                products.put(type, storeCapacity);
                storage.getStorage().add(new OrderedProduct(type, excess));

                System.out.println("창고에 " + excess + "개의 " + type + "이 추가");
                System.out.println("진열대에 "+type+" "+(storeCapacity-prevAmount)+"개 추가");

            }
            else {
                // 진열대products에 저장
                products.put(type, prevAmount + num);
                System.out.println("진열대에 "+type+" "+num+"개 추가");
            }
        }
    }

    public void moveStorageToProducts(Map<ProductType, Integer> products, int storeCapacity, Storage storage){
        // 창고에 남아있는 물건을 진열대에 배치
        // 작동 시점 (미정, 택 1)
        // 물품 발주 직전, 장사 종료 직후
        // 물품 발주 직후는 선입선출을 위해서 배제

        for (OrderedProduct product : storage.getStorage()){
            int typeCount = products.get(product.getProductType());
            int shortfall = storeCapacity - typeCount;

            if (shortfall >= product.getNum()){
                // 부족분이 창고에서 뺀 양 이상일 때
                // products에 product.num 전부 저장
                products.put(product.getProductType(), typeCount + product.getNum());
            }
            else if (shortfall < 0){
                // 부족하지 않을 때
                // 다시 storage에 저장
                storage.getStorage().add(product);
            }
            else {
                // 부족분이 0 보다 크고 창고에서 뺀 양보다 작을 때
                // 부족분만큼 products에 넣고 나머지를 storage에 저장
                products.put(product.getProductType(), shortfall);
                storage.getStorage().add(new OrderedProduct(product.getProductType(), product.getNum() - shortfall));
            }
        }
    }

}
