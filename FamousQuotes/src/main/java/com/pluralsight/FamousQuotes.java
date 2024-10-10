package com.pluralsight;

import java.util.Arrays;
import java.util.Scanner;
public class FamousQuotes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            String[] quotes = {
                    "\"The only way to do great work is to love what you do.\" — Steve Jobs",
                    "\"Believe you can and you're halfway there.\"  — Theodore Roosevelt",
                    "\"Success is not final, failure is not fatal: It is the courage to continue that counts.\" — Winston S. Churchill",
                    "\"Your time is limited, so don’t waste it living someone else’s life.\" — Steve Jobs",
                    "\"The future belongs to those who believe in the beauty of their dreams.\" — Eleanor Roosevelt",
                    "\"It does not matter how slowly you go as long as you do not stop.\" — Confucius",
                    "\"The best way to predict the future is to create it.\" — Peter Drucker",
                    "\"You are never too old to set another goal or to dream a new dream.\" — C.S. Lewis",
                    "\"What lies behind us and what lies before us are tiny matters compared to what lies within us.\" — Ralph Waldo Emerson", "\"Keep your face always toward the sunshine—and shadows will fall behind you.\" — Walt Whitman"
            };
            System.out.println("\nSelect and enter a number between 1 to 10");
            int index = scanner.nextInt();
            index--;

            System.out.println(quotes[index]);

        } catch (Exception e) {
            System.out.println("Your number was out of range!");
            e.printStackTrace();
        }
    }
}
