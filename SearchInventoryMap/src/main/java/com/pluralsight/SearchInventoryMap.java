package com.pluralsight;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class SearchInventoryMap {
    static HashMap<String, Product> inventory = new HashMap<>();
    public static void main(String[] args) {
        loadInventory();
        Scanner scanner = new Scanner(System.in);
        boolean continueSearching = true; //to control loop

        while (continueSearching) {
            System.out.print("What item are you interested in? ");
            String name = scanner.nextLine().trim();

            Product matchedProduct = inventory.get(name);
            if (matchedProduct == null) {
                System.out.println("we dont carry that product");
            } else {
                System.out.printf("We carry %s (ID: %d) and the price is $%.2f", matchedProduct.getName(), matchedProduct.getId(), matchedProduct.getPrice());
            }
            System.out.println("Do you want to search again? (yes/no): ");
            String userInput = scanner.nextLine().trim().toLowerCase();
            continueSearching = userInput.equals("yes");
        }
    }
    //method to load inventory from csv
    private static void loadInventory() {
        try{        // filereader to read csv inventory file.
            FileReader fileReader = new FileReader("C:\\\\pluralsight\\\\workbook-3\\\\SearchInventory\\\\inventory.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);
            String line; //variable for later to save line so we can make into an array and split it

            while ((line = bufReader.readLine()) != null) {//creating a loop to read each line
            String[] newLine = line.split("\\|");// creating an array to be able to access index

                //checking that each line has at least 3 parameters
                if (newLine.length < 3) {
                    System.out.println("invalid line format" + line);
                    continue;  // causes the loop to skip the remaining code if there are less than 3 parameters
                }
                int id;
                try {
                    id = Integer.parseInt(newLine[0].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid format for sku" + newLine[0]);
                    continue;
                }
                String name = newLine[1].trim();
                float price;
                try {
                    price = Float.parseFloat(newLine[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price format: " + newLine[2]);
                    continue;
                }
               // Product product = new Product(id, name, price);
                inventory.put(name, new Product(id, name, price));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

