package dto;

public class OrderedProduct {
    ProductType productType;
    int num;

    public OrderedProduct(ProductType productType, int num){
        this.productType = productType;
        this.num = num;
    }

    public ProductType getProductType() {
        return productType;
    }

    public int getNum() {
        return num;
    }
}