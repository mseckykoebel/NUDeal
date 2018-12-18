package com.example.jameswilliams.nu_deal.nu_deal_game.ActionCards;

import com.example.jameswilliams.nu_deal.nu_deal_game.*;

import java.util.ArrayList;

public class Birthday extends Card {

    // object constructor
    public Birthday() {
        this.name = "Birthday";
        this.bankable = true;
        this.value = 2;
        this.banked = false;
    }

    public CardResponse playCard(GameState g, UserInterface u, int playernum) {
        CardResponse response = new CardResponse(true, "success");

        //Create a list of cards to be recieved by the birthday boy/girl
        ArrayList<Card> cards = new ArrayList<Card>();

        //Get the list of players who need to pay
        ArrayList<Player> players = g.getPlayersExcept(playernum);

        //Loop through the players
        for(int i = 0; i < players.size(); i++)
        {
            cards.addAll(players.get(i).chargeMoney(2, u));
        }

        //Remove the birthday card from the player's hand
        g.getPlayers().get(playernum).removeFromHand(this);
        g.addToDiscardPile(this);

        //Add the cards to the player's bank or board
        g.getPlayers().get(playernum).giveCards(cards);

        return response;

    }
}
