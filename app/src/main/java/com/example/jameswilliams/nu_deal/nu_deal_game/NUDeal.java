package com.example.jameswilliams.nu_deal.nu_deal_game;

import java.util.*;
import java.io.*;

//Main game class for NUDeal
//Handles game state as well as the hands

//Main game object
public class NUDeal {


    GameState g;
    UserInterface u;

    public NUDeal(UserInterface us) {
        g = new GameState();
        u = us;
    }

    public void runGame() {
        boolean playAgain = true;

        //Display the welcome message
        u.displayMessage("Welcome to NU Deal!");

        int numPlayers = Integer.parseInt(u.getInput("How many players would you like to add?"));

        //For each player, get their name
        for (int i = 0; i < numPlayers; i++) {
            String playerName = u.getInput("What will the name of player " + i + 1 + "be?");
            g.addPlayer(new Player(playerName));
        }

        //Tell them we're starting the game
        u.displayMessage("Starting game...");
        while (playAgain) {
            g.initGame();

            //Go to main game loop
            playAgain = mainGameLoop();
            if (playAgain) {
                u.displayMessage("Restarting game");
            } else {
                u.displayMessage("Exiting game");
            }
        }
    }

    private boolean mainGameLoop() {
        while (true) {

            //Say who'se turn it is
            u.displayMessage(g.getPlayers().get(g.whoseTurn()).getName()+"'s turn");

            //Deal the player two cards
            g.dealPlayer(g.whoseTurn(), 2);

            //Play up to 3 cards
            int plays = 0;
            while (plays < 3) {
                //Display cards to player
                u.displayMessage("Please select a card to play or say 'end turn' ");
                String choice = u.getInput(g.getPlayers().get(g.whoseTurn()).getAllCardString());

                //If they want to end their turn
                if (choice == "end turn") {
                    break;
                } else {
                    //Try to get a number from the strng
                    try {
                        int cardChoice = Integer.parseInt(choice);
                        //If the card choice is out of the range of their hand
                        if (cardChoice >= g.getPlayers().get(g.whoseTurn()).getHandSize() - 1) {
                            u.displayMessage("Invalid card choice, out of range of hand!");
                            continue;
                        }
                        //Play the card
                        CardResponse response = g.playCard(g.whoseTurn(), cardChoice, u);
                        //If the card was played successfully
                        if (response.success) {
                            u.displayMessage("Card played!");
                            plays++;
                            continue;
                        } else {
                            u.displayMessage("Unable to play card: " + response.message);
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        u.displayMessage("Invalid number format, try '0'");
                        continue;
                    }
                }
            }

            //If the player needs to discard cards
            if(g.getPlayers().get(g.whoseTurn()).getHandSize() > 7)
            {
                //TODO
            }


            //Check to see if anyone won on that turn
            if (g.winDetected() != null) {
                break;
            } else {
                //Advance the turn
                g.nextTurn();
            }

        }

        //If we're here, someone has won the game
        u.displayMessage(g.winDetected().getName() + " has won the game!");
        String choice = u.getInput("Play again? yes or no");

        if(choice == "yes")
        {
            return true;
        }
        return false;
    }



}
