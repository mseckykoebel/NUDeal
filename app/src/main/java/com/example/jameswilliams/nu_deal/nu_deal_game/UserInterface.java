package com.example.jameswilliams.nu_deal.nu_deal_game;

import java.util.Scanner;

public class UserInterface {
    Scanner in;

    public UserInterface() {
        //Maybe select between text based and GUI
        in = new Scanner(System.in);
    }

    public String getInput(String message) {
        System.out.println(message);
        return in.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public int[] stringToIntArray(String s) {
        // The string you want to be an integer array.
        String[] integerStrings = s.split(" ");
        // Splits each spaced integer into a String array.
        int[] integers = new int[integerStrings.length];
        // Creates the integer array.
        for (int i = 0; i < integers.length; i++) {
            integers[i] = Integer.parseInt(integerStrings[i]);
            //Parses the integer for each string.
        }
        return integers;
    }
}