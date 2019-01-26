package com.example.jameswilliams.nu_deal.nu_deal_game.ActionCards;

import com.example.jameswilliams.nu_deal.nu_deal_game.Card;
import com.example.jameswilliams.nu_deal.nu_deal_game.CardResponse;
import com.example.jameswilliams.nu_deal.nu_deal_game.GameState;
import com.example.jameswilliams.nu_deal.nu_deal_game.Player;
import com.example.jameswilliams.nu_deal.nu_deal_game.PropertyCard;
import com.example.jameswilliams.nu_deal.nu_deal_game.UserInterface;

import java.util.ArrayList;

public class SlyDeal extends Card
{
    // object constructor
    public SlyDeal()
    {
        name = "SlyDeal";
        value = 3;
        bankable = true;
        banked = false;
    }

    @Override
    public CardResponse playCard(GameState g, UserInterface u, Player p) {
        //Similar to forced deal but without the trade

        //Prompt the player to select the target player
        u.displayMessageToPlayer(p, "Select the player you wish to target:");
        Player target = u.promptPlayerSelection(p, g.getPlayersExcept(p));

        //Select the card you wish to take
        u.displayMessageToPlayer(p, "Select the card you wish to take");
        ArrayList<Card> toGet = u.promptCardSelection(p, target.getTradablePropertiesList());

        //Tell the target player what is happening
        u.displayMessageToPlayer(target, p.getName() + " wants to take your " + toGet.get(0).getName());

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

        //Take the card from the target player
        target.removeFromBoard((PropertyCard) toGet.get(0));

        //Give the recieved card to the player
        p.addToBoard((PropertyCard) toGet.get(0));

        //return success
        return new CardResponse(true,"Card played successfully");

    }
}
