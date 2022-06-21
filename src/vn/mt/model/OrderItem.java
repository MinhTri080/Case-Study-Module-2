package vn.mt.model;

import java.time.Instant;

public class OrderItem {
    private long id;
    private long orderID;
    private long productID;
    private String productName;
    private double price;
    private int quantity;
    private double total;
    private Instant createdAt;

    public OrderItem() {
    }

    public OrderItem(long id, long orderID, long productID, String productName, double price, int quantity, double total) {
        this.id = id;
        this.orderID = orderID;
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public OrderItem(String record) {
        String[] fields = record.split(",");
        orderID = Long.parseLong(fields[0]);
        id = Long.parseLong(fields[1]);
        price = Double.parseDouble(fields[2]);
        quantity = Integer.parseInt(fields[3]);
        productID = Integer.parseInt(fields[4]);
        productName = fields[5];
        total = Double.parseDouble(fields[6]);
        createdAt = Instant.parse(fields[7]);
    }


    public Instant getCreatedAt() {

        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {

        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


    @Override
    public String toString() {
        return String.format("%s,%s,%s,%d,%s,%s,%s,%s", orderID, id, price, quantity, productID, productName, total, createdAt);
    }
}
