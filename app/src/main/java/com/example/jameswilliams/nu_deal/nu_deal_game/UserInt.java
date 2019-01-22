package com.example.jameswilliams.nu_deal.nu_deal_game;

import java.util.ArrayList;

public interface UserInt {

    //Displays a message to all players
    void displayMessage(String message);

    //Displays a message to Player p
    void displayMessageToPlayer(Player p, String message);

    //Prompts Player p to select cards from the list of cards (list)
    //Returns a list of the cards chosen
    ArrayList<Card> promptCardSelection(Player p, ArrayList<Card> list);

    //Prompts Player p to select a player from a list of players (list)
    //Returns the selected player
    Player promptPlayerSelection(Player p, ArrayList<Player> list);

    //Prompts Player p to select a color from a list of colors (colors)
    String promptColorSelection(Player p, ArrayList<String> colors);


}
