package utils;
import java.time.LocalDate;
import java.time.format.*;
import java.util.*;
import java.lang.InterruptedException;
import java.io.IOException;
import database.*;
import customer_product.*;
import employee.*;
import product.Product;
import Admin.AdminRole;
import utils.Validator;
import utils.Generator;
import java.util.ArrayList;

import static utils.Generator.generateEmployeeRandomId;
import static utils.Validator.*;

public class MainMenuFunctions {




    public static void AddEmployee(AdminRole admin)
    {
        Scanner scan = new Scanner(System.in);
        
        String ID = Generator.generateProductRandomId();

        System.out.print("Enter the Employee's Name: ");
        String Name = scan.nextLine();

        System.out.print("Enter the Employee's Email: ");
        String email = scan.nextLine();
        Validator.isValidEmail(email);

        System.out.print("Enter the Employee's address: ");
        String address = scan.nextLine();
       
        System.out.print("Enter the Employee's Phone Number: ");
        String Phone = scan.nextLine();
        Validator.isValidPhoneNumber(Phone);

        admin.addEmployee(ID , Name, email, address,Phone );
    }
    
    public static void RemoveEmployee(AdminRole admin)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Removing employee from database: ");
        System.out.print("\nEnter the Employee's ID: ");
        String ID = scan.nextLine();
        admin.removeEmployee(ID);

    }


    public static void ShowAllEmployees(AdminRole admin)
    {
        EmployeeUser[] list = admin.getListOfEmployees();

        for(int i=0 ; i< list.length ; i++)
       {
        System.out.println(list[i].lineRepresentation());
       }
        
    }
    public static void addProduct(EmployeeRoles emp)
    {
        Scanner scan = new Scanner(System.in);
        String ID = Generator.generateEmployeeRandomId();

        System.out.println("Adding product to database: ");
        System.out.print("\nEnter the Product's Name: ");
        String Name = scan.nextLine();
        isValidString(Name);

        System.out.println("\nEnter product maker: ");
        String maker = scan.nextLine();
        isValidString(maker);

        System.out.println("\nEnter product supplier: ");
        String supplier = scan.nextLine();
        isValidString(supplier);

        System.out.println("\nEnter product quantity: ");
        int quantity = scan.nextInt();
        scan.nextLine();
        isPositiveNumber(quantity);

        emp.addProduct(ID , Name, maker , supplier, quantity);

    }
    
    public static void getListOfPurchasingOperations(EmployeeRoles employeeRole){
        CustomerProduct [] list = employeeRole.getListOfPurchasingOperations();
        for(int i = 0; i < list.length; i++){
            System.out.println(list[i].lineRepresentation());
        }
    }


    public static String getDateFormated(LocalDate Date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = Date.format(formatter);
        return date;
    }

    public static LocalDate getLocalDateFormated(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate Date = LocalDate.parse(date,formatter);
        return Date;
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }

    }
}
