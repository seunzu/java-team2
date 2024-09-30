package dto;

import java.util.*;

public class Storage {
    private final Queue<OrderedProduct> storage;
    private final Queue<OrderedProduct> orders;

    public Storage() {
        this.storage = new LinkedList<>();
        this.orders = new LinkedList<>();
    }

    public Queue<OrderedProduct> getOrders() {
        return orders;
    }

    public Queue<OrderedProduct> getStorage(){
        return storage;
    }

    public void addOrder(OrderedProduct order) {
        orders.add(order);
    }
}