package com.example.jameswilliams.nu_deal.GameFiles.ActionCards;

import com.example.jameswilliams.nu_deal.GameFiles.Card;
import com.example.jameswilliams.nu_deal.GameFiles.CardResponse;
import com.example.jameswilliams.nu_deal.GameFiles.GameState;
import com.example.jameswilliams.nu_deal.GameFiles.Player;
import com.example.jameswilliams.nu_deal.GameFiles.UserInterface;

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
