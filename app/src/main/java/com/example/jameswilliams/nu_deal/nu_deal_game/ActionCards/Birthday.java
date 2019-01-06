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

    public CardResponse playCard(GameState g, UserInterface u, Player p) {
        CardResponse response = new CardResponse(true, "success");

        //Create a list of cards to be recieved by the birthday woman/man
        ArrayList<Card> cards = new ArrayList<Card>();

        //Get the list of players who need to pay
        ArrayList<Player> players = g.getPlayersExcept(p);

        //Loop through the players
        for(int i = 0; i < players.size(); i++)
        {
            cards.addAll(players.get(i).chargeMoney(2, u));
        }

        //Remove the birthday card from the player's hand
        p.removeFromHand(this);
        g.addToDiscardPile(this);

        //Add the cards to the player's bank or board
        p.giveCards(cards);

        return response;

    }
}
