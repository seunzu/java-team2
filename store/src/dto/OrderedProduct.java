package dto;

public class OrderedProduct {
    ProductType productType = ProductType.RAMEN;
    int num = 0;

    OrderedProduct(ProductType productType, int num){
        this.productType = productType;
        this.num = num;
    }
}
