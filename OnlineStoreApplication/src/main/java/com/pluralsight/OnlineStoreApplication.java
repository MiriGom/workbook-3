package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class OnlineStoreApplication {
    static boolean isRunning = true;
    static ArrayList<InventoryItem> products = new ArrayList<>();
    static ArrayList<InventoryItem>userCart = new ArrayList<>();
    static HashMap<String, InventoryItem> inventory = new HashMap<String, InventoryItem>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        loadInventory();
        //while application is running show home screen
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
        System.out.println("""
                ================================
                             PRODUCTS
                ================================""");
                for (InventoryItem product : products) {
                    //System.out.println(product);
                    System.out.println("SKU:" + product.getSkuNumber() + " |" + product.getProductName() + "\n             price: $" + product.getPrice() + "\n");
                }
        System.out.println("""
                1. Search product
                2. Add a product to your cart
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
                        return; //to go back to home screen
                }
        }
    public static void showDisplayCartScreen() {
        System.out.println("""
                ==================================              
                           YOUR CART
                ==================================
                """);
        double total = 0;
        for (InventoryItem product : userCart) {
            System.out.println(product);
            total += product.getPrice();
        }
        System.out.printf("Total: $%.2f%n", total);
        System.out.println("""
                \n
                1.Checkout
                2.Remove Product
                3.Add Another Product
                4.Go Back to Home Screen
                """);
        int command = scanner.nextInt();
        scanner.nextLine();
        switch (command) {
            case 1:
                checkOut();
                break;
            case 2:
                removeProductFromCart();
                break;
            case 3:
                addProductToCart();
                break;
            case 4:
                return;
        }
    }
    public static void checkOut() {
        System.out.println("Successful Checkout: Thank you for your purchase!");
        userCart.clear();
    }
    public static void removeProductFromCart() {
        System.out.println("Enter the SKU number of the product you want to remove");
        String skuInput = scanner.nextLine();
        InventoryItem productToRemove = null;
        for (InventoryItem product : userCart) {
            if (product.getSkuNumber().equalsIgnoreCase(skuInput)) {
                productToRemove = product;
                break;
            }
        }
        if (productToRemove != null) {
            userCart.remove(productToRemove);
            System.out.println((productToRemove.getProductName() + " has been removed from your cart"));
        } else {
            System.out.println("Product not found in cart");
        }
    }
    public static void showSearchOptions(){
        System.out.println("""
                ==================================
                        SEARCH PRODUCT BY...
                ==================================
                1. Product Name
                2. Department
                3. Price
                """);
        int searchCommand = scanner.nextInt();
        scanner.nextLine();
        switch (searchCommand) {
            case 1:
                boolean continueSearching = true; //to control loop

                while (continueSearching) {
                    System.out.print("What item are you interested in? ");
                    String keyword = scanner.nextLine().toLowerCase().trim();

                    for(InventoryItem product : products) {
                        if (product.getProductName().toLowerCase().contains(keyword)) {
                            System.out.println(product);
                        }
                    }
                    System.out.println("Type \"search\" to search again or \"add\" to add an item to your cart : ");
                    String userSearchAdd = scanner.nextLine().trim().toLowerCase();
                    if (userSearchAdd.equalsIgnoreCase("add")) {
                        addProductToCart();
                    }
                    continueSearching = userSearchAdd.equals("search");
/*
                    System.out.println("Do you want to search again? (yes/no): ");
                    String userInput = scanner.nextLine().trim().toLowerCase();
                    continueSearching = userInput.equals("yes");*/
                } break;
            case 2:
                boolean continueSearching2 = true;
                while (continueSearching2) {
                    String[] departmentArray = {"Electronics", "Games", "Computers", "Audio Video"};
                    for (int i = 0; i < departmentArray.length; i++) {
                        System.out.println(departmentArray[i]);
                    }
                    System.out.println("\nPlease type the department you would like to search in ");
                    String chosenDepartment = scanner.nextLine().toLowerCase();
                    //boolean found = false;
                    for (InventoryItem product : products) {
                        if (product.getDepartment().equalsIgnoreCase(chosenDepartment)) {
                            System.out.println(product);
                        }
                    }
                    System.out.println("Type \"search\" to search again or \"add\" to add an item to your cart : ");
                    String userSearchAdd = scanner.nextLine().trim().toLowerCase();
                    if (userSearchAdd.equalsIgnoreCase("add")) {
                        addProductToCart();
                    }
                    continueSearching2 = userSearchAdd.equals("search");
                }
                break;
        }
    }
    public static void addProductToCart(){
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Enter the SKU number of the product you would like to \"Add To Cart\" OR enter x to go back");
            String userSkuInput = scanner.nextLine().trim();

            if (userSkuInput.equalsIgnoreCase("x")) {
                break;
            }
            InventoryItem product = inventory.get(userSkuInput);

            if (product != null) {
                userCart.add(product);
                System.out.println(product.getProductName() + " has been added to your cart.");
            } else {
                System.out.println("Product not found.");
            }

            System.out.println("Do you want to add another product? (yes/no): ");
            String userInput = scanner.nextLine().trim().toLowerCase();

            if (!userInput.equalsIgnoreCase("yes")) {
                break;
            }
        }
        System.out.println("Would you like to checkout? (yes/no): ");
        String userCheckoutInput = scanner.nextLine().trim().toLowerCase();
        if (userCheckoutInput.equalsIgnoreCase("yes")) {
            showDisplayCartScreen();
        }
    }

    private static void loadInventory() {try {

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
            inventory.put(skuNumber, item);
        }

    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    }
}

