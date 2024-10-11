package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;


public class OnlineStoreApplication {
    static boolean isRunning = true;
    static ArrayList<InventoryItem> products = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
    //Scanner scanner = new Scanner(System.in);

        //while application is running she home screen
        while (isRunning) {
            showStoreHomeScreen(); //method to print home screen and run function
            int command = scanner.nextInt();
            scanner.nextLine();
                switch (command) {
                    case 1:
                        showDisplayProductsScreen();
                        break;
                    case 2:
                        showDisplayCartScreen();
                        break;
                    case 3:
                        isRunning = false;
                        System.out.println("Bye-Bye");
                }
        }
    }

    public static void showStoreHomeScreen() {


        System.out.println("""
                ==================================
                   WELCOME TO YOUR ONLINE STORE
                           HOME SCREEN
                ==================================
                1. Display Products
                2. Display Cart
                3. Exit 
                """);
    }

    public static void showDisplayProductsScreen() throws IOException {
      //  Scanner scanner = new Scanner(System.in);
        //ArrayList<InventoryItem> products = new ArrayList<>();

        try {
            FileReader filereader = new FileReader("C:\\Users\\Student\\Desktop\\DataFiles\\DataFiles\\products.csv");
            BufferedReader bufReader = new BufferedReader(filereader);
            String line;

            while ((line = bufReader.readLine()) != null) {
                String[] newLine = line.split("\\|");

                if (newLine.length < 4) {
                    System.out.println("Invalid line format: " + line);
                    continue;
                }
                    String skuNumber = newLine[0].trim();
                    String productName = newLine[1].trim();
                    double price;

                    try {
                        price = Double.parseDouble(newLine[2].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid price format for product: " + productName);
                        continue; // Skip to the next line
                    }
                    String department = newLine[3].trim();


                    InventoryItem item = new InventoryItem(skuNumber, productName, price, department);
                    products.add(item);

                    //System.out.println(products);
                }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("""
                ================================
                             PRODUCTS
                ================================""");
                for (InventoryItem product : products) {
                    System.out.println("\nSKU:" + product.getSkuNumber() + " | " + product.getProductName() + "\n             price: $" + product.getPrice() + "\n");
                }
        System.out.println("""
                \n
                1. Search product
                2. Add a product to my cart
                3. Return to Home Screen
                """);
                int command = scanner.nextInt();
                scanner.nextLine();
                switch (command) {
                    case 1:
                        showSearchOptions();
                        break;
                    case 2:
                        addProductToCart();
                        break;
                    case 3:
                        isRunning = false;
                }
        }
    public static void showDisplayCartScreen() {
        System.out.println("""
                ==================================              
                           YOUR CART
                ==================================
                
                
                1. Checkout
                2. Remove Product
                3. Keep Shopping
                """);
    }
    public static void showSearchOptions(){
       // Scanner scanner = new Scanner(System.in);
        System.out.println("""
                ==================================
                        SEARCH PRODUCT BY...
                ==================================
                1. Product Name
                2. Department
                3. SKU NUMBER
                """);
        int searchCommand = scanner.nextInt();
        scanner.nextLine();
        switch (searchCommand) {
            case 1:
                System.out.println("Please enter the product name you are searching for\n");
                String searchProduct = scanner.nextLine().trim();
                for (InventoryItem product : products) {
                    if (searchProduct.equalsIgnoreCase(product.getProductName())) {
                        System.out.println(product);
                       System.out.println("Enter \"Y\" to add this item to your cart or enter \"X\" to return to search options");
                       String userInput2 = scanner.nextLine();
                       switch (userInput2.toLowerCase()) {
                           case "y":
                               addProductToCart();
                               break;
                           case "x":
                               break;
                       }
                      /* if (userInput2.equalsIgnoreCase("y")) {
                            addProductToCart();
                            break;
                        } else {
                            break;
                        }*/
                    } else {
                        System.out.println("Sorry couldn't find product");
                        break;
                    }
                }
                break;
            case 2:
                for (InventoryItem product : products) {
                    System.out.println(product.getDepartment());
                    }

        }
     /*   for (InventoryItem product : products) {
            if (searchCommand.equalsIgnoreCase(product.getSkuNumber())) {
                System.out.println("Added");
            }
        }*/

    }
    public static void addProductToCart(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the SKU number of the product you would like to buy OR enter x to go back");
        String userSkuInput = scanner.nextLine();
        if (userSkuInput.equalsIgnoreCase("x")) {

        }
        //once user enters input show the product and ask user again
        // when user says yes then add to cart
    }

}
