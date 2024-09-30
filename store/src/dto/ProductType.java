package dto;

public enum ProductType {
    RAMEN(5000),
    SNACK(2000),
    DRINK(1500);

    private final int price;

    ProductType(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}