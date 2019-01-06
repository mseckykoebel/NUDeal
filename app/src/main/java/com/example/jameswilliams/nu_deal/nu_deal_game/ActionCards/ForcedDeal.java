package com.example.jameswilliams.nu_deal.nu_deal_game.ActionCards;

import com.example.jameswilliams.nu_deal.nu_deal_game.Card;
import com.example.jameswilliams.nu_deal.nu_deal_game.CardResponse;
import com.example.jameswilliams.nu_deal.nu_deal_game.GameState;
import com.example.jameswilliams.nu_deal.nu_deal_game.Player;
import com.example.jameswilliams.nu_deal.nu_deal_game.UserInterface;

import java.lang.reflect.Array;
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

        if(p.willSayNo(u))
        {
            //Discard this from the player's hand
            //return success
        }

        //Discard this from player's hand
        //Get target card from player and give to this player



    }
}
