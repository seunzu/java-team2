package dto;

public class OrderedProduct {
    ProductType productType;
    int num;

    OrderedProduct(ProductType productType, int num){
        this.productType = productType;
        this.num = 0;
    }
}