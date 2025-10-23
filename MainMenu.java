import java.util.*;
import Admin.AdminRole;
import utils.MainMenuFunctions;
import java.lang.InterruptedException;
import java.io.IOException;
import employee.*;

public class MainMenu {

    static String RESET = "\u001B[0m";
    static String RED = "\u001B[31m";
    static String GREEN = "\u001B[32m";
    static String YELLOW = "\u001B[33m";
    static String BLUE = "\u001B[34m";
    static String PURPLE = "\u001B[35m";
    static String CYAN = "\u001B[36m";
    static String WHITE = "\u001B[37m";

    public static void main(String[] args) {
        LoginMenu();
    }

    public static void LoginMenu() {
        System.out.println(BLUE + "================= Inventory Management System =================" + RESET);
        System.out.println(GREEN + "1. Admin Login" + RESET);
        System.out.println(GREEN + "2. Employee Login" + RESET);
        System.out.println(GREEN + "3. Exit" + RESET);
        System.out.println(BLUE + "==============================================================" + RESET);
        System.out.print(YELLOW + "Please select an option (1 --> 3): " + RESET);

        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();

        switch (choice) {
            case 1:
                clearScreen();
                adminMenu();
                break;

            case 2:
                clearScreen();
                EmployeeMenu();
                break;

            case 3:
                clearScreen();
                System.exit(0);
                break;

            default:
                clearScreen();
                System.out.println(RED + "Invalid Choice! Please try again." + RESET);
                LoginMenu();
                break;
        }

    }

    public static void adminMenu() {
        clearScreen();
        System.out.println(BLUE + "================= Inventory Management System =================" + RESET);
        System.out.println(GREEN + "1. Add New Employee" + RESET);
        System.out.println(GREEN + "2. Remove Employee" + RESET);
        System.out.println(GREEN + "3. View All Employees" + RESET);
        System.out.println(GREEN + "4. Logout" + RESET);
        System.out.println(BLUE + "===============================================================" + RESET);
        System.out.print(YELLOW + "Please select an option (1 --> 4): " + RESET);

        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        AdminRole admin = new AdminRole();

        switch (choice) {
            case 1:
                clearScreen();
                MainMenuFunctions.AddEmployee(admin);
                adminMenu();
                // adding new employee
                break;
            case 2:
                clearScreen();
                MainMenuFunctions.RemoveEmployee(admin);
                adminMenu();
                break;
            case 3:
                clearScreen();
                MainMenuFunctions.ShowAllEmployees(admin);
                adminMenu();
                // view all employees
                break;
            case 4:
                clearScreen();
                admin.logout();
                LoginMenu();
                break;
            default:
                System.out.println(RED + "Invalid Choice! Please try again." + RESET);
                adminMenu();
                break;
        }

    }


    public static void EmployeeMenu(){
        clearScreen();
        EmployeeRole employee = new EmployeeRole();
        System.out.println(BLUE + "================= Inventory Management System =================" + RESET);
        System.out.println(GREEN + "1. Add Product " + RESET);
        System.out.println(GREEN + "2. Get List Of Products" + RESET);
        System.out.println(GREEN + "3. Get List Of Purchasing Operations" + RESET);
        System.out.println(GREEN + "4. Purchase Product" + RESET);
        System.out.println(GREEN + "5. Return Product" + RESET);
        System.out.println(GREEN + "6. applyPayment" + RESET);
        System.out.println(GREEN + "7. Logout" + RESET);
        System.out.println(BLUE + "===============================================================" + RESET);
        System.out.print(YELLOW + "Please select an option (1 --> 7): " + RESET);

        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();

        switch (choice) {
            case 1:
                clearScreen();
                MainMenuFunctions.addProduct(employee);
                EmployeeMenu();
                break;
            case 2:
                clearScreen();
                MainMenuFunctions.getListOfProducts(employee);
                EmployeeMenu();
                break;


            case 3:
                clearScreen();
                MainMenuFunctions.getListOfPurchasingOperations(employee);
                EmployeeMenu();
                break;


            case 4:
            clearScreen();
            MainMenuFunctions.purchaseProduct(employee);  
            EmployeeMenu();
            break;

            case 5:
            clearScreen();
            MainMenuFunctions.returnProduct(employee);
            EmployeeMenu();

            break;


            case 6:
            clearScreen();
            MainMenuFunctions.applyPayment(employee);
            EmployeeMenu();
            break;

            case 7:
            clearScreen();
                employee.logout();
                LoginMenu();
                break;

            default:
                System.out.println(RED + "Invalid Choice! Please try again." + RESET);
                adminMenu();
                break;
        }

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
