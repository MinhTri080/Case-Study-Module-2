package Model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private long id;
    private String fullname;
    private double total;

    private Instant createdAt;
    List<OrderItem> orderItems = new ArrayList<>();

    public Order(long orderId, String name) {
        this.id = orderId;
        this.fullname = name;

    }

    public Order(long id, String fullname, double total) {
        this.id = id;
        this.fullname = fullname;
        this.total = total;
    }

    public Order() {

    }

    public static Order parse(String record) {
        //1655803736,Luon Em,203601.0,2022-06-21T09:29:04.583Z
        Order order = new Order();
        String[] field = record.split(",");
        order.id = Long.parseLong(field[0]);
        order.fullname = field[1];
        order.total = Double.parseDouble(field[2]);
        order.createdAt = Instant.parse(field[3]);
        return order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return
                id + "," + fullname + "," + total + "," + createdAt;

    }
}
