package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PayrollCalculator {
    public static void main(String[] args) throws IOException {
        //  Scanner scanner = new Scanner(System.in);
        ArrayList<TimeCard> employees = new ArrayList<>();

        try {
            FileReader filereader = new FileReader("employees.csv");
            BufferedReader bufReader = new BufferedReader(filereader);
            String line;
            bufReader.readLine();
            while ((line = bufReader.readLine()) != null) {
                String[] newLine = line.split(",");

                if (newLine.length < 4) {
                    System.out.println("Invalid line format: " + line);
                    continue;
                }
                 String employeeId = newLine[0].trim();
                 String employeeName = newLine[1].trim();
                 double hoursWorked;
                try {
                    hoursWorked = Double.parseDouble(newLine[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price hours worked: ");
                    continue; // Skip to the next line
                }
                 double payRate;

                try {
                    payRate = Double.parseDouble(newLine[3].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid format for pay rate: ");
                    continue; // Skip to the next line
                }

                TimeCard timeCard = new TimeCard(employeeId, employeeName, hoursWorked, payRate);
                employees.add(timeCard);

                // System.out.println(timeCard);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (TimeCard t: employees) {
            System.out.printf("%-25s $%7.2f\n",t.getEmployeeName(),t.getGrossPay());
        }
    }
}
