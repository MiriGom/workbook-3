package com.pluralsight;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SearchEngineLogger {

    static Scanner scanner = new Scanner(System.in); //globalizing the scanner
    private static final String loggingFile = "logs.text"; //globalizing the file
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //sets all formatters to this format

    public static void main(String args[]) {
        SearchEngineLogger logger = new SearchEngineLogger();

        System.out.println("Please type your full name to launch the Search Engine Application and press ENTER");
        String fullName = scanner.nextLine().trim();
        logger.logAction("launch");
        String[] userFullName = fullName.split(" ");
        String firstName = "";
        String middleName = "(none)";
        String lastName = "";

        if (userFullName.length > 0) {
            firstName = userFullName[0];
        }
        if (userFullName.length == 3) {
            middleName = userFullName[1];
            lastName = userFullName[2];
        } else if (userFullName.length == 2) {
            lastName = userFullName[1];
        }


        System.out.println("Welcome " + firstName + " to the Search Engine Application!");
        String userTerm;

        do {
            System.out.println("Please type a term you would like to search or type \"X\" to exit application");
             userTerm = scanner.nextLine().trim();

            if (!userTerm.equalsIgnoreCase("x")) {
                logger.logAction("search : " + userTerm);
            }
        } while (!userTerm.equalsIgnoreCase("x"));

        logger.logAction("exit");
        scanner.close();
    }
    private void logAction(String action) {
        String timeStamp = LocalDateTime.now().format(formatter);
        String logEntry = String.format("%s %s%n", timeStamp, action);

        try (BufferedWriter bufWriter = new BufferedWriter(new FileWriter("logs.txt", true))){
            bufWriter.write(logEntry);
           // bufWriter.newLine(); //adds a new line after writing logEntry
            bufWriter.close();
        } catch (IOException e) {
            System.out.println("ERROR: An unexpected error occured");
            e.getStackTrace();
        }
    }
}
