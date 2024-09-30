package dto;

import service.StorageService;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private int vaultCash = 200_000; // 시재금
    private int rent = 100_000;// 임대료
    private int dayCount; // 날짜 세는 변수
    private final Map<ProductType, Integer> products;
    private final Storage storage;
    public final static int storeCapacity = 20;

    public Store() {
        this.dayCount = 0;
        this.products = new HashMap<>();
        for (ProductType product : ProductType.values()) {
            products.put(product, 0);
        }
        this.storage = new Storage();
    }

    public void addVaultCash(int price) {
        this.vaultCash += price;
    }

    private final StorageService storageService = new StorageService();

    // 발주했을 때 큐(Storage.order)에 저장, 돈 차감
    public void orderProduct(ProductType productType, int quantity) {
        int spend = storageService.orderProduct(productType, quantity, vaultCash, storage);
        vaultCash -= spend;
        System.out.println("남은 금액은 " + vaultCash + "원 입니다.");
    }

    // 큐(Storage.order)에 넣은 값을 빼서 진열대(Store.products)와 창고(Storage.storage)에 저장
    public void placeProduct() {
        // order 큐의 모든 값 빼서 진열대, 창고에 옮기기
        storageService.placeProduct(products, storeCapacity, storage);
    }

    public boolean reduceProduct(ProductType product, int quantity) {
        int currentQuantity = products.get(product);
        if (currentQuantity >= quantity) {
            products.put(product, currentQuantity - quantity);
            return true;
        } else {
            System.out.println("재고 부족");
            return false;
        }
    }

    public void showProducts() {
        System.out.println(ProductType.RAMEN + " " + products.get(ProductType.RAMEN) + "개");
        System.out.println(ProductType.DRINK + " " + products.get(ProductType.DRINK) + "개");
        System.out.println(ProductType.SNACK + " " + products.get(ProductType.SNACK) + "개");
    }

    public String listProducts() {
        ProductType[] types = {ProductType.RAMEN, ProductType.DRINK, ProductType.SNACK};
        String result = "";
        for (int i = 0; i < types.length; i++) {
            result += (i != 0 ? ", " : "") + types[i].toString() + " " + products.get(types[i]) + "개";
        }
        return result;
    }

//    public String listStorage(){
//        ProductType[] types = {ProductType.RAMEN, ProductType.DRINK, ProductType.SNACK};
//       String result = "";
//        for (int i = 0; i < types.length; i++) {
//            result += (i != 0 ? ", " : "")+ types[i].toString() + " " + storage+"개";
//        }
//        return result;
//}


    public void showVaultCash(){
        System.out.println("남은 금액은 "+vaultCash+"원 입니다.");
    }

    public int getVaultCash() {
        return vaultCash;
    }

    public void setVaultCash(int vaultCash) {
        this.vaultCash = vaultCash;
    }

    public int getRent() {
        return rent;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void addDayCount() {
        this.dayCount++;
    }

    public Map<ProductType, Integer> getProducts(){
        return products;
    };

    public Storage getStorage() {
        return storage;
    }
}