package services;

import models.entities.Client;
import models.entities.Product;
import models.entities.Sell;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class CreateSell {

    public static void addSell(Set<Sell> sells){

        Scanner sc = new Scanner(System.in);

        try{
            System.out.print("Enter the name of the client: ");
            String name = sc.nextLine();
            System.out.print("Enter the name of the product: ");
            String pName = sc.nextLine();
            System.out.print("Enter the price of the product: ");
            Double price = Double.parseDouble(sc.nextLine());
            System.out.print("Enter the quantity ");
            Integer quantity = Integer.parseInt(sc.nextLine());

            sells.add(new Sell(new Client(name), new Product(pName, price), quantity));

        } catch(InputMismatchException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
