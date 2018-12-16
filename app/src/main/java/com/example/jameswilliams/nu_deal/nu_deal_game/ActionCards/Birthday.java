package com.example.jameswilliams.nu_deal.nu_deal_game.ActionCards;

import com.example.jameswilliams.nu_deal.nu_deal_game.*;

import java.util.ArrayList;


public class Birthday extends Card {
    public Birthday() {
        this.name = "Birthday";
        this.bankable = true;
        this.value = 2;
    }

    public CardResponse playCard(GameState g, UserInterface u, int playernum) {
        CardResponse response = new CardResponse(true, "success");

        //Create a list of cards to be recieved by the birthday boy/girl
        ArrayList<Card> cards = new ArrayList<Card>();

        //Get the list of players who need to pay
        ArrayList<OKPair> players = g.getPlayerPairs(playernum);

        //Loop through the players
        for(int i = 0; i < players.size(); i++)
        {
            cards.addAll(((Player)players.get(i).getObject()).chargeMoney(2, u));
        }

        //Remove the birthday card from the player's hand
        g.getPlayers().get(playernum).removeFromHand(this);

        //Add the cards to the player's bank or board
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

        return response;

    }
}
