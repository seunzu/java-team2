package dto;

public class Sales {
    private final int quantity;
    private final int price;
    private final int totalAmount;

    public Sales(int quantity, int price, int totalAmount) {
        this.quantity = quantity;
        this.price = price;
        this.totalAmount = totalAmount;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public int getTotalAmount() {
        return totalAmount;
    }
}