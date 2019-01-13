package com.example.jameswilliams.nu_deal.nu_deal_game.ActionCards;

import com.example.jameswilliams.nu_deal.nu_deal_game.Card;
import com.example.jameswilliams.nu_deal.nu_deal_game.CardResponse;
import com.example.jameswilliams.nu_deal.nu_deal_game.GameState;
import com.example.jameswilliams.nu_deal.nu_deal_game.Player;
import com.example.jameswilliams.nu_deal.nu_deal_game.UserInterface;

public class PassGo extends Card
{
    // object constructor
    public PassGo()
    {
        this.name = "PassGo";
        this.value = 1;
        this.bankable = true;
        this.banked = false;
    }

    @Override
    public CardResponse playCard(GameState g, UserInterface u, Player p) {
        //Discard this card from the player's hand
        this.removeSelfFromPlayerHand(p);
        g.addToDiscardPile(this);

        //Give the player two cards
        g.dealPlayer(p,2);

        //This card is always successful
        return new CardResponse(true, "Card played successfully");
    }
}
