package controller;

import dto.ProductType;
import dto.Sales;
import dto.Store;
import service.CustomerService;

import java.util.Map;

public class CustomerController {

    private static CustomerController customerController;
    private final CustomerService customerService;

    public CustomerController() {
        customerService = new CustomerService();
    }

    public static CustomerController getInstance() {
        if (customerController == null) {
            customerController = new CustomerController();
        }
        return customerController;
    }

    public void customerPurchase(Store store) {
        customerService.purchase(store);
    }

    public Map<ProductType, Sales> getSalesData() {
        return customerService.getSalesData();
    }

    public int getTotalSalesAmount() {
        return customerService.getTotalSalesAmount();
    }

    public void start(Store store) {
        customerPurchase(store);
    }
}