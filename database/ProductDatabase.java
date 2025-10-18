package database;

import product.Product;


public class ProductDatabase extends Database<Product> {

    public ProductDatabase(String fileName) {
        super(fileName);
    }

    @Override
    public Product createRecordFrom(String line) {
        String[] parts = line.split(",");
        return new Product(parts[0], parts[1], parts[2], parts[3], Integer.valueOf(parts[4]), Float.valueOf(parts[5]));
    }
}

