package utils;
import java.time.LocalDate;
import java.time.format.*;
import java.util.*;
import java.lang.InterruptedException;
import java.io.IOException;
import customer_product.*;
import employee.*;
import product.Product;
import Admin.AdminRole;
import static utils.Generator.generateCustomerRandomId;
import static utils.Validator.isInteger;

public class MainMenuFunctions {

    static String RESET = "\u001B[0m";
    static String RED = "\u001B[31m";
    static String GREEN = "\u001B[32m";
    static String YELLOW = "\u001B[33m";
    static String BLUE = "\u001B[34m";
    static String PURPLE = "\u001B[35m";
    static String CYAN = "\u001B[36m";
    static String WHITE = "\u001B[37m";

 //Admin Functions

    public static void AddEmployee(AdminRole admin)
    {
        Scanner scan = new Scanner(System.in);
        
        String ID = Generator.generateEmployeeRandomId();

        Boolean flag = true;

        System.out.print("Enter the Employee's Name: ");
        String Name = scan.nextLine();
        if(!Validator.isValidString(Name))
        {
        System.out.println(RED + "Error The name must be String........");
        flag = false;
        scan.nextLine();
        return;
        }
        



        System.out.print("Enter the Employee's Email: ");
        String email = scan.nextLine();
        if(!Validator.isValidEmail(email))
        {
        System.out.println(RED + "Error, Enter Valid Email........");
        flag = false;
        scan.nextLine();
        return;
        }
        
        System.out.print("Enter the Employee's address: ");
        String address = scan.nextLine();
       
        System.out.print("Enter the Employee's Phone Number: ");
        String Phone = scan.nextLine();
        if(!Validator.isValidPhoneNumber(Phone))
        {
        System.out.println(RED + "Error The Phone Number must be 11 numbers........");
        flag = false;
        scan.nextLine();
        return;
        }
        


        if(flag){
        admin.addEmployee(ID , Name, email, address,Phone );
        System.out.println( GREEN + "Employee was Added successfully...............");
        scan.nextLine();
        }
    } 

    public static void RemoveEmployee(AdminRole admin)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Removing employee from database: ");
        System.out.print("\nEnter the Employee's ID: ");
        String ID = scan.nextLine();
        admin.removeEmployee(ID);
        scan.nextLine();

    }


    public static void ShowAllEmployees(AdminRole admin)
    {
        EmployeeUser[] list = admin.getListOfEmployees();

        for(int i=0 ; i< list.length ; i++)
       {
        System.out.println(list[i].lineRepresentation());
       }

       Scanner scanner = new Scanner(System.in);
        System.out.println("Press Any key to Continue...........");
        scanner.nextLine(); 
    }

    


    //Employee Functions

    public static void addProduct(EmployeeRole emp)
    {
        Scanner scan = new Scanner(System.in);
        String ID = Generator.generateProductRandomId();



        System.out.println("Adding product to database ");
        System.out.print("Enter the Product's Name: ");
        String Name = scan.nextLine();
        if(!Validator.isValidString(Name))
        {
        System.out.println(RED + "Error The name must be String........");
        scan.nextLine();
        return;
        }

        System.out.print("Enter product maker: ");
        String maker = scan.nextLine();
        if(!Validator.isValidString(maker))
        {
        System.out.println(RED + "Error The maker must be String........");
        scan.nextLine();
        return;
        }

        System.out.print("Enter product supplier: ");
        String supplier = scan.nextLine();
        if(!Validator.isValidString(supplier))
        {
        System.out.println(RED + "Error The supplier must be String........");
        scan.nextLine();
        return;
        }

        System.out.print("Enter product quantity: ");
        String StringQuantity = scan.nextLine();
        if(!isInteger(StringQuantity))
        {
        System.out.println(RED + "Error The quantity must be Positive Integer........");
        scan.nextLine();
        return;
        }

        int quantity = Integer.valueOf(StringQuantity);
        
      
        emp.addProduct(ID , Name, maker , supplier, quantity);

        System.out.println("Press Any key to Continue...........");
        scan.nextLine();

    }


    public static void getListOfProducts(EmployeeRole employeeRole){
        Product[] list = employeeRole.getListOfProducts();
        for(int i = 0; i < list.length; i++){
            System.out.println(list[i].lineRepresentation());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Any key to Continue...........");
        scanner.nextLine();

    }




    public static void getListOfPurchasingOperations(EmployeeRole employeeRole){

        CustomerProduct [] list = employeeRole.getListOfPurchasingOperations();
        for(int i = 0; i < list.length; i++){
            System.out.println(list[i].lineRepresentation());
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Press Any key to Continue...........");
        scan.nextLine();

    }

    public static void purchaseProduct(EmployeeRole employeeRole){
        Scanner scanner = new Scanner(System.in);
        
        String SSN = generateCustomerRandomId();
        System.out.print("Enter Product ID: ");
        String productID = scanner.nextLine();
        
        LocalDate purchaseDate = LocalDate.now();
       
        employeeRole.purchaseProduct(SSN, productID, purchaseDate);

        System.out.println("Press Any key to Continue...........");
        scanner.nextLine();
    }



    public static void returnProduct(EmployeeRole Emp)
    {

        Scanner scan = new Scanner(System.in);
        LocalDate Returndate = LocalDate.now();

        System.out.print("Enter the CustomerSSN Code: ");
        String SSN = scan.nextLine();

        System.out.print("Enter The Product Code: ");
        String productID = scan.nextLine();

        System.out.print("Enter Date Of purchasing in form dd-mm-yyyy: ");
        String date = scan.nextLine();
        LocalDate purchaseDate = getLocalDateFormated(date);


        Double price = Emp.returnProduct(SSN, productID, purchaseDate, Returndate);
        if(price >= 0)
        {
            System.out.println(GREEN + "Returned Product's Price is: " + price + RESET);
           
        }
        System.out.println("Press Any key to Continue...........");
        scan.nextLine();
        
    }


    public static void applyPayment(EmployeeRole Emp)
    {
        Scanner scan = new Scanner(System.in);


        System.out.print("Enter the CustomerSSN Code: ");
        String SSN = scan.nextLine();


        System.out.print("Enter Date Of Requestin purchasing in form dd-mm-yyyy: ");
        String date = scan.nextLine();
        LocalDate requestDate = getLocalDateFormated(date);

        Emp.applyPayment(SSN, requestDate);
        System.out.println("Press Any key to Continue...........");
        scan.nextLine();
    }



    //helping Functions

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
