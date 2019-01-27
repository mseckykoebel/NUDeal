package com.example.jameswilliams.nu_deal.GameFiles.ActionCards;

import com.example.jameswilliams.nu_deal.GameFiles.Card;
import com.example.jameswilliams.nu_deal.GameFiles.CardResponse;
import com.example.jameswilliams.nu_deal.GameFiles.GameState;
import com.example.jameswilliams.nu_deal.GameFiles.Player;
import com.example.jameswilliams.nu_deal.GameFiles.PropertyCard;
import com.example.jameswilliams.nu_deal.GameFiles.UserInterface;

import java.util.ArrayList;

public class ForcedDeal extends Card
{
    // object constructor
    public ForcedDeal()
    {
        this.bankable = true;
        this.banked = false;
        this.value = 3;
        this.name = "ForcedDeal";
    }

    @Override
    public CardResponse playCard(GameState g, UserInterface u, Player p) {

        //If the player has no cards to trade
        if(p.getBoardSize() == 0){
            return new CardResponse(false, "Player has no cards to trade");
        }

        //Prompt the player to select one of their cards to trade
        u.displayMessageToPlayer(p, "Select one of your cards to trade:");
        ArrayList<Card> tradable = p.getTradablePropertiesList();
        ArrayList<Card> toGive = u.promptCardSelection(p,tradable);

        //Prompt the player to select the target player
        u.displayMessageToPlayer(p, "Select the player you wish to target:");
        Player target = u.promptPlayerSelection(p, g.getPlayersExcept(p));

        //Select the card you wish to take
        u.displayMessageToPlayer(p, "Select the card you wish to take");
        ArrayList<Card> toGet = u.promptCardSelection(p, target.getTradablePropertiesList());

        //Tell the target player what is happening
        u.displayMessageToPlayer(target, p.getName() + " wants to take your " + toGet.get(0).getName() + " and give you " + toGive.get(0).getName());

        //If the target wants to play a just say no
        if(target.willSayNo(g, u, p))
        {
            //Discard this from the player's hand
            this.removeSelfFromPlayerHand(p);
            g.addToDiscardPile(this);
            //return success
            return new CardResponse(true, "card played successfully");
        }

        //Discard this from player's hand
        this.removeSelfFromPlayerHand(p);
        g.addToDiscardPile(this);

        //Remove the trading cards from the players boards
        target.removeFromBoard((PropertyCard) toGet.get(0));
        p.removeFromBoard((PropertyCard) toGive.get(0));

        //Give the cards to the opposite players
        target.addToBoard((PropertyCard) toGive.get(0));
        p.addToBoard((PropertyCard) toGet.get(0));

        //Return success
        return new CardResponse(true, "card played successfully");


    }
}
