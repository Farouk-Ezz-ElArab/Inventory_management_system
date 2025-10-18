package product;

import database.ProductDatabase;

import java.util.ArrayList;

public class TestProduct {
    public static void main(String[] args) {

        ArrayList<Product> products;

        ProductDatabase db = new ProductDatabase("product/Product.txt");
        db.readFromFile();
        String newID="P6";
        if (db.isIdUsed(newID)) {
            System.out.println("❌ ID already exists. Please choose another one.");
            System.exit(0);
        }
        Product newProd = new Product(newID, "EarBuds","Huawei","TechSupplier",5,500);
        db.insertRecord(newProd);
        products=db.returnAllRecords();
        System.out.println(products);

        System.out.println(db.getRecord("P1"));

        db.returnAllRecords();

        System.out.println("Current products loaded from file:");
        for (Product e : db.returnAllRecords()) {
            System.out.println(e.lineRepresentation());
        }



        db.saveToFile();

        System.out.println("\nAfter adding EarBuds and saving:");
        for (Product e : db.returnAllRecords()) {
            System.out.println(e.lineRepresentation());
        }

        System.out.println(db.contains("P6"));

        db.deleteRecord("P5");

        db.saveToFile();
    }

}
