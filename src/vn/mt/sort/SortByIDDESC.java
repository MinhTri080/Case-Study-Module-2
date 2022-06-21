package vn.mt.sort;

import vn.mt.model.Product;

import java.util.Comparator;

public class SortByIDDESC implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o2.getProductID() - o1.getProductID());
    }
}
