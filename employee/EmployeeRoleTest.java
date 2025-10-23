package employee;

import java.time.LocalDate;

import java.time.format.*;


public class EmployeeRoleTest {
    public static void main(String[] args) {



        EmployeeRole emp = new EmployeeRole();
        emp.addProduct("P141", "iPhone14", "Apple", "TechSupplier", 100);

       /* 
        Product[] list = emp.getListOfProducts();
        for(int i=0 ; i< list.length ; i++)
       {
        System.out.println(list[i].lineRepresentation());
       }
         */


       /* 
        CustomerProduct[] list = emp.getListOfPurchasingOperations();
        for(int i=0 ; i< list.length ; i++)
       {
        System.out.println(list[i].lineRepresentation());
       }
         */

         /* 
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

         System.out.println(emp.applyPayment("4151512231", LocalDate.parse("12-08-2023",formatter)));
         */



         




    }
}