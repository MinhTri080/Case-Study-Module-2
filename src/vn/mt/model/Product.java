package vn.mt.model;

import java.util.Comparator;

public class Product implements Comparator<Product> {
    private long productID;
    private String name;
    private double price;
    private int quantity;
    public Product() {
    }

    public Product(String record) {
        String[] fields = record.split(",");
        productID = Integer.parseInt(fields[0]);
        name = fields[1];
        price = Double.parseDouble(fields[2]);
        quantity = Integer.parseInt(fields[3]);
    }

    public Product(int productID, String name, double price, int quantity) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;

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

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return productID +
                "," + name +
                "," + price +
                "," + quantity;
    }

    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o1.getProductID() - o2.getProductID());
    }

}
