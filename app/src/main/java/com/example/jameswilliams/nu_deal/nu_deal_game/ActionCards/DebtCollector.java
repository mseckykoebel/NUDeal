package com.example.jameswilliams.nu_deal.nu_deal_game.ActionCards;

import com.example.jameswilliams.nu_deal.nu_deal_game.*;

import java.util.ArrayList;


public class DebtCollector extends Card {
    public DebtCollector() {
        this.name = "DebtCollector";
        this.value = 3;
        this.bankable = true;
    }

    public CardResponse playCard(GameState g, UserInterface u, int playernum) {
        CardResponse response = new CardResponse(true, "success");

        //Figure out who we're playing the debt collector on
        //Get the list of players
        ArrayList<OKPair> players = g.getPlayerPairs(playernum);
        //Get the user input
        while (true) {
            u.displayMessage("Who would you like to debt collect?");
            String input = u.getInput(OKPair.OKListToString(players, "Players:\n"));

            try {
                int pchoice = Integer.parseInt(input);
                //If the choice was invalid
                if (pchoice >= players.size()) {
                    u.displayMessage("Invalid player choice, try again.");
                    continue;
                } else {
                       //Prompt that player for debt collection
                        Player target = (Player)players.get(pchoice).getObject();
                        //If that player has no cards on the board
                }

            } catch (NumberFormatException e) {
                u.displayMessage("Invalid number format, try again.");
                continue;
            }
        }


        return response;
    }

}
