package com.pluralsight;
import java.io.FileInputStream;
import java.io.*;
import java.util.Scanner;

public class BedtimesStories {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("""
                ===========================================
                                  FILE 
                ===========================================
                Goldilocks
                Hansel And Gretel
                Mary Had A Little Lamb
                
                Please type the story you would like to read
                """);
        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase("Goldilocks")) {
            String filename = "C:\\Users\\Student\\Desktop\\DataFiles\\DataFiles\\goldilocks.txt";
            //below we create a FileInputStream object to a specific file
            try (FileInputStream fis = new FileInputStream(filename);
                 //create a scanner to find the file to be read
                 Scanner scanner = new Scanner(fis)) {

                int lineNumber = 1;
                String story;//variable to hold the story the user chose
                while (scanner.hasNextLine()) {//method returns true if there are more lines
                    story = scanner.nextLine(); //scanner looks for user input
                    System.out.println(lineNumber + ": " + story);//prints out story line by line
                    lineNumber++;//increment the lineNumber in the newly printed story
                }
            } catch (IOException e) {
                System.out.println("An error occured while reading the file.");
                e.printStackTrace();
            }
        } else if (userInput.equalsIgnoreCase("Hansel And Gretel")) {
            String filename = "C:\\Users\\Student\\Desktop\\DataFiles\\DataFiles\\hansel_and_gretel.txt";

            try (FileInputStream fis = new FileInputStream(filename);
                 Scanner scanner = new Scanner(fis)) {
                String story;
                int lineNumber = 1;
                while (scanner.hasNextLine()) {
                    story = scanner.nextLine();
                    System.out.println(lineNumber + ": " + story);
                    lineNumber++;
                }
            } catch (IOException e) {
                System.out.println("An error occured while reading the file.");
                e.printStackTrace();
            }
        } else if (userInput.equalsIgnoreCase("Mary Had A Little Lamb")) {
            String filename = "C:\\Users\\Student\\Desktop\\DataFiles\\DataFiles\\mary_had_a_little_lamb.txt";

            try (FileInputStream fis = new FileInputStream(filename);
            Scanner scanner = new Scanner(fis)) {
                String story;
                int lineNumber = 1;
                while (scanner.hasNextLine()) {
                    story = scanner.nextLine();
                    System.out.println(lineNumber + ": " + story);
                    lineNumber++;
                }
            }catch (IOException e) {
                System.out.println("An error occured while reading the file.");
                e.printStackTrace();
            }
        }
    }
}
