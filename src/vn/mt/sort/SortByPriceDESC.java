package vn.mt.sort;

import vn.mt.model.Product;

import java.util.Comparator;

public class SortByPriceDESC implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        if(o2.getPrice() > o1.getPrice()){
            return 1;
        }else if(o1.getPrice() == o2.getPrice()){
            return 0;
        }else{
            return -1;
        }
    }
}
