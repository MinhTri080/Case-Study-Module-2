package vn.mt.service;

import vn.mt.model.Product;
import vn.mt.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService{
    List<Product> products = new ArrayList<>();
    private static String path = "data/product.csv";


    @Override
    public List<Product> getItem() {
        List<Product> newProducts = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            newProducts.add(new Product(record));
        }
        return products = newProducts;

    }




    @Override
    public void addItem(Product newProduct) {
        List<Product> products = getItem();
        products.add(newProduct);
        CSVUtils.write(path, products);
    }

    @Override
    public void update(Product newProduct) {
        List<Product> products = getItem();
        for (Product product : products) {
            if (product.getProductID() == newProduct.getProductID()) {
                product.setPrice(newProduct.getPrice());
                product.setQuantity(newProduct.getQuantity());
            }
        }
        CSVUtils.write(path, products);
    }

    @Override
    public void remove(long id) {
        List<Product> products = getItem();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductID() == id) {
                products.remove(i);
                break;
            }
        }
        CSVUtils.write(path, products);
    }

    public Product getProductByID(int id) {
        List<Product> products = getItem();
        for (Product product : products) {
            if (product.getProductID() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public boolean exists(int id) {
        return getProductByID(id) != null;
    }

}
