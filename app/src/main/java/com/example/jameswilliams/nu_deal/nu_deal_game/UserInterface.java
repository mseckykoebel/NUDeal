package com.example.jameswilliams.nu_deal.nu_deal_game;

import com.example.jameswilliams.nu_deal.MainActivity;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    Scanner in;

    public UserInterface() {
        //Maybe select between text based and GUI
        in = new Scanner(System.in);
    }

    public String getLine(Player p, String message) {
        System.out.println(message);
        return MainActivity.getResponse();
    }

    public void displayMessage(String message) {
        MainActivity.addLine(message);
    }

    public void displayMessageToPlayer(Player p, String message)
    {
        MainActivity.addLine(message);
    }

    //Gets an integer from player p
    public int getInt(Player p, String message)
    {
        MainActivity.addLine(message);
        while(true)
        {
            try {
                String respose = MainActivity.getResponse();
                int ret = Integer.parseInt(respose);
                return ret;
            }catch(NumberFormatException e) {
                displayMessage("Invalid number, try again");
            }
        }
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

    //Prompts Player p to select cards from the list of given cards
    public ArrayList<Card> promptCardSelection(Player p, ArrayList<Card> list) {
        //Make the card prompt string
        String msg = "Select a card:\n";
        for (int i = 0; i < list.size(); i++) {
            msg += Integer.toString(i) + ". " + list.get(i).getName() + "\n";
        }

        //Send it to the terminal
        MainActivity.addLine(msg);

        //Get the response
        String response = MainActivity.getResponse();
        int[] choices = stringToIntArray(response);

        //Add those cards to the response list
        ArrayList<Card> ret = new ArrayList<Card>();

        for (int i = 0; i < choices.length; i++) {
            ret.add(list.get(choices[i]));
        }
        return ret;
    }

    //Prompts player p to select from a list of players and returns the selected player
    public Player promptPlayerSelection(Player p, ArrayList<Player> list) {
        String msg = "Select a player:\n";
        for (int i = 0; i < list.size(); i++) {
            msg += Integer.toString(i) + ". " + list.get(i).getName() + "\n";
        }
        while (true) {
            MainActivity.addLine(msg);

            //Get the response
            String response = MainActivity.getResponse();

            try {
                int choice = Integer.parseInt(response);
                if(choice >= list.size())
                {
                    MainActivity.addLine("Invalid choice, try again");
                }else{
                    return list.get(choice);
                }

            } catch (NumberFormatException e) {
                MainActivity.addLine("Invalid number, try again");
            }
        }
    }
}