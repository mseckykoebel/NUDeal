package com.example.jameswilliams.nu_deal.nu_deal_game;

import android.widget.Toast;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

// All messages are generated using a 'Toast.'

public class GameUserInterface implements UserInt {
    @Override
    // this message will display at the top of the screen ... it will not ask for anything
    // this will be directing the game
    public void displayMessage(String message) {

    }

    @Override
    // display some message to the player ... can be anything under the sun
    public void displayMessageToPlayer(Player p, String message) {

    }

    @Override
    // asks the player to choose some card (private)
    public ArrayList<Card> promptCardSelection(Player p, ArrayList<Card> list) {
        return null;
    }

    @Override
    // ask the player to choose some card
    public Player promptPlayerSelection(Player p, ArrayList<Player> list) {
        return null;
    }

    @Override
    // make the player choose the color
    public String promptColorSelection(Player p, ArrayList<String> colors) {
        return null;
    }


}