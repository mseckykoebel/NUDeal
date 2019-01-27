package com.example.jameswilliams.nu_deal.GameFiles.ActionCards;

import com.example.jameswilliams.nu_deal.GameFiles.Card;
import com.example.jameswilliams.nu_deal.GameFiles.CardResponse;
import com.example.jameswilliams.nu_deal.GameFiles.GameState;
import com.example.jameswilliams.nu_deal.GameFiles.Player;
import com.example.jameswilliams.nu_deal.GameFiles.Set;
import com.example.jameswilliams.nu_deal.GameFiles.UserInterface;

import java.util.ArrayList;

public class DealBreaker extends Card {

    // object constructor
    public DealBreaker()
    {
        this.name = "DealBreaker";
        this.bankable = true;
        this.banked = false;
        this.value = 5;
    }

    @Override
    public CardResponse playCard(GameState g, UserInterface u, Player p) {

        //Select the target player
        Player target = u.promptPlayerSelection(p, g.getPlayersExcept(p));

        //Get the full sets of the target
        ArrayList<String> sets = target.getFullSetColors();

        //If the target has no full sets
        if(sets.size() == 0){
            //Cannot play this card
            return new CardResponse(false, "Cannot play this card, targeted player has no full sets");
        }

        //Prompt color selection
        String selectedColor = u.promptColorSelection(p, sets);

        //Tell the targeted player what is happening
        u.displayMessageToPlayer(target, "You are being deal broken by " + p.getName() + ", they want to take your " + selectedColor +" set.");

        //If they're going to say no
        if(target.willSayNo(g, u, p)){
            //Discard this card from the player's hand
            this.removeSelfFromPlayerHand(p);
            g.addToDiscardPile(this);

            //Return success
            return new CardResponse(true, "Card played successfully.");
        }

        //Otherwise take the set from the target player
        Set toGet = target.takeFullSet(selectedColor);

        //Give the set to the player
        p.giveSet(toGet);

        //Discard this card from the player's hand
        this.removeSelfFromPlayerHand(p);
        g.addToDiscardPile(this);

        //Return success
        return new CardResponse(true, "Card played successfully.");
    }
}

