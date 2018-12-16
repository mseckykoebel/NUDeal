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
                    Player target = (Player) OKPair.findKey(players, pchoice);
                    //Charge the player
                    ArrayList<Card> cards = target.chargeMoney(5, u);
                    //Add the cards to the player of debt collector
                    for (int i = 0; i < cards.size(); i++) {
                        //If it's a money card
                        if (cards.get(i).isBanked()) {
                            //Add it to the player's bank
                            g.getPlayers().get(playernum).addToBank(cards.get(i));
                        } else {
                            //Add it to their board
                            g.getPlayers().get(playernum).addToBoard(cards.get(i));
                        }
                    }
                    //Break out of the loop
                    break;
                }

            } catch (NumberFormatException e) {
                u.displayMessage("Invalid number format, try again.");
                continue;
            }
        }


        return response;
    }

}
