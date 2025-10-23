package employee;

import customer_product.CustomerProduct;
import database.CustomerProductDatabase;
import database.ProductDatabase;
import product.Product;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
import utils.Validator;

public class EmployeeRoles {

    final String RESET = "\u001B[0m";
    final String RED = "\u001B[31m";
    final String GREEN = "\u001B[32m";

    
    private final ProductDatabase productsDb;
    private final CustomerProductDatabase custProdDb;

    public EmployeeRoles() {
        //E:\\OneDrive\\Desktop\\Programing\\Cursor\\Labs\\Lab4\\inventory\\Inventory_management_system\\product\\Product.txt
        //product/Product.txt
        //E:\\OneDrive\\Desktop\\Programing\\Cursor\\Labs\\Lab4\\inventory\\Inventory_management_system\\customer_product\\CustomerProduct.txt
        //customer_product/CustomerProduct.txt

        productsDb = new ProductDatabase("product/Product.txt");
        custProdDb = new CustomerProductDatabase("customer_product/CustomerProduct.txt");
        productsDb.readFromFile();
        custProdDb.readFromFile();
    }




    public void addProduct(String id, String name, String maker, String supplier, int qty) //NO PRICE IS SET , it set to ZERO
     {
        if (!productsDb.contains(id)) {
            Scanner scan = new Scanner(System.in);
            System.out.print("insert the product's price: ");
            String stringprice = scan.nextLine();
            if(!Validator.isFloat(stringprice))
            {
            System.out.println(RED + "Error The Price must be Positive Floats........");
            return;
            }
            Float price = Float.valueOf(stringprice);

            productsDb.insertRecord(new Product(id, name, maker, supplier, qty, price));
            System.out.println(GREEN + "The Product has been ADDED Successfully......" + RESET);
            productsDb.saveToFile();
        }
        else
        {
            System.out.println(RED + "The ID you Entered is Used by Other Product......" + RESET);

        }
    }




    public Product[] getListOfProducts() {
        ArrayList<Product> list = productsDb.returnAllRecords();
        return list.toArray(new Product[0]);
    }


    public CustomerProduct[] getListOfPurchasingOperations() {
        ArrayList<CustomerProduct> list = custProdDb.returnAllRecords();
        return list.toArray(new CustomerProduct[0]);
    }

    

    public boolean purchaseProduct(String ssn, String productID, LocalDate date) {
        Product p = productsDb.getRecord(productID);
        if (p == null || p.getQuantity() <= 0) {
            System.out.println(RED + "insuffisient Quantity of The Product OR Wrong ID....."+ RESET);
            return false;
        }

        p.setQuantity(p.getQuantity() - 1);
        CustomerProduct cs = new CustomerProduct(ssn, productID, date);
        cs.setPaid(false);
        custProdDb.insertRecord(cs);
        productsDb.saveToFile();
        custProdDb.saveToFile();
        System.out.println(GREEN + "Purshasing Product Operation Done Successfully.........."+ RESET);
        return true;
    }



    public double returnProduct(String ssn, String productID, LocalDate purchaseDate, LocalDate returnDate) { // returns the price of returend product.  // takes date in Local date formate
       
        if (returnDate.isBefore(purchaseDate)) {
            System.out.println(RED+ "The Date you entered may be wrong....... " + RESET );
            return -1;
        }

        Product p = productsDb.getRecord(productID);
        if (p == null) {
            System.out.println(RED+ "The Product with ID: " +productID+" Couldn't found....... "+ RESET);
            return -1;
        }

        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = purchaseDate.format(formatter);
        String key = ssn + "," + productID + "," + date;
        CustomerProduct target = custProdDb.getRecord(key);

        if (target == null) {
            System.out.println(RED+ "This Purshasing operation Details Couldn't found: " + RESET + key);
            return -1;
        }
        
        long days = ChronoUnit.DAYS.between(purchaseDate, returnDate);
        if (days > 14) {
            System.out.println(RED+ "Sorry the 14 DAYS Limit to return the product Has Already PASSED.......... " + RESET);
            System.out.println("The product was Purshased on: " + date);
            return -1;
        }
        
        p.setQuantity(p.getQuantity() + 1);
        custProdDb.returnAllRecords().remove(target);
        productsDb.saveToFile();
        custProdDb.saveToFile();
        return p.getPrice();
    }


    public boolean applyPayment(String ssn, LocalDate date) { // takes date in Local date formate
        for (CustomerProduct c : custProdDb.returnAllRecords()) {
            if (c.getCustomerSSN().equals(ssn) && c.getPurchaseDate().equals(date) && !c.isPaid()) {
                c.setPaid(true);
                custProdDb.saveToFile();
                System.out.println(GREEN + "Purshasig operation has been Paid Successfully......." + RESET);
                return true;
            }
        }
        System.out.println(RED + "ERROR, Operation wasn't found or the operation already Paid........" + RESET);
        return false;
    }

    public void logout() {
        productsDb.saveToFile();
        custProdDb.saveToFile();
    }
}