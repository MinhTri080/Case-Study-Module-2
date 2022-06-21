package vn.mt.service;

import vn.mt.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getItem();

    void addItem(Product newProduct);

    void update(Product newProduct);

    void remove(long id);

    boolean exists(int id);

    Product getProductByID(int productID);
}
