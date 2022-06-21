package vn.mt.sort;

import vn.mt.model.Product;

import java.util.Comparator;

public class SortByIDASC implements Comparator<Product> {
    Product product = new Product();
    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o1.getProductID() - o2.getProductID());
    }
}
