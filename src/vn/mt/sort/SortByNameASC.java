package vn.mt.sort;

import vn.mt.model.Product;

import java.util.Comparator;

public class SortByNameASC implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o1.getName().compareTo(o2.getName());
    }

}
