package com.example.jameswilliams.nu_deal.nu_deal_game.ActionCards;

import com.example.jameswilliams.nu_deal.nu_deal_game.*;

import java.util.ArrayList;


public class DebtCollector extends Card {
    public DebtCollector() {
        this.name = "DebtCollector";
        this.value = 3;
        this.bankable = true;
        this.banked = false;
    }

    public CardResponse playCard(GameState g, UserInterface u, int playernum) {
        CardResponse response = new CardResponse(true, "success");

        //Figure out who we're playing the debt collector on
        //Get the user input
        u.displayMessage("Who would you like to debt collect?");
        Player target = u.promptPlayerSelection(g.getPlayers().get(playernum), g.getPlayersExcept(playernum));

        //Prompt that player for debt collection
        u.displayMessageToPlayer(g.getPlayers().get(playernum), "You have been debt collected!");
        //Charge the player
        ArrayList<Card> cards = target.chargeMoney(5, u);
        //Add the cards to the player of debt collector
        g.getPlayers().get(playernum).giveCards(cards);

        //Remove this from the player's hand
        g.getPlayers().get(playernum).removeFromHand(this);
        g.addToDiscardPile(this);


        return response;
    }

}
