package com.pluralsight;

public class InventoryItem {
    private String skuNumber;
    private String productName;
    private double price;
    private String department;

    public InventoryItem(String skuNumber, String productName, double price, String department) {
        this.skuNumber = skuNumber;
        this.price = price;
        this.productName = productName;
        this.department = department;
    }

    public String getSkuNumber() {
        return skuNumber;
    }

    public void setSkuNumber(String skuNumber) {
        this.skuNumber = skuNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "SKU: " + skuNumber +
                "|  " + productName +
                "|  price: $" + price +
                "|  Department: " + department + "\n";
    }
}

