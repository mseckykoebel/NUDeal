package com.example.jameswilliams.nu_deal.nu_deal_game;

import com.example.jameswilliams.nu_deal.MainActivity;

import java.util.ArrayList;
import java.util.Scanner;

public class DummyUserInterface extends UserInterface {

    Scanner in;
    ArrayList<String> gameOutput;
    ArrayList<Integer> nextCardSelection;
    ArrayList<Integer> nextPlayerSelection;
    ArrayList<String> nextColorSelection;

    public DummyUserInterface() {
        //Maybe select between text based and GUI
        in = new Scanner(System.in);
        gameOutput = new ArrayList<>();
        nextCardSelection = new ArrayList<>();
        nextPlayerSelection = new ArrayList<>();
        nextColorSelection = new ArrayList<>();
    }

    public DummyUserInterface(ArrayList<String> go, ArrayList<Integer> nc, ArrayList<Integer> np, ArrayList<String> ncs){
        in = new Scanner(System.in);
        gameOutput = go;
        nextCardSelection = nc;
        nextPlayerSelection = np;
        nextColorSelection = ncs;
    }

    public String getLine(Player p, String message) {
        System.out.println(message);
        return MainActivity.getResponse();
    }

    public void displayMessage(String message) {
        gameOutput.add(message);
    }

    public void displayMessageToPlayer(Player p, String message) { gameOutput.add(message); }

    //Gets an integer from player p
    public int getInt(Player p, String message) {
        MainActivity.addLine(message);
        while (true) {
            try {
                String respose = MainActivity.getResponse();
                int ret = Integer.parseInt(respose);
                return ret;
            } catch (NumberFormatException e) {
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
        ArrayList<Card> ret = new ArrayList<>();
        ret.add(list.get(nextCardSelection.remove(0)));
        return ret;
    }

    //Prompts player p to select from a list of players and returns the selected player
    public Player promptPlayerSelection(Player p, ArrayList<Player> list) {
        return list.get(nextPlayerSelection.remove(0));
    }

    public String promptColorSelection(Player p, ArrayList<String> colors) {
        return nextColorSelection.remove(0);
    }

    //Checks if l has string c
    public boolean hasString(ArrayList<String> l, String c) {
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i) == c) {
                return true;
            }
        }
        return false;
    }


}
