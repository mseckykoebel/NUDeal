package com.example.jameswilliams.nu_deal.nu_deal_game.ActionCards;

// Import individually so I know where tf they all came from
import com.example.jameswilliams.nu_deal.nu_deal_game.Card;
import com.example.jameswilliams.nu_deal.nu_deal_game.CardResponse;
import com.example.jameswilliams.nu_deal.nu_deal_game.GameState;
import com.example.jameswilliams.nu_deal.nu_deal_game.Player;
import com.example.jameswilliams.nu_deal.nu_deal_game.UserInterface;

import java.util.ArrayList;

public class WildRentCard extends Card
{
    // object constructor
    public WildRentCard()
    {
        this.name = "WildRentCard";
        this.value = 3;
        this.bankable = true;
        this.banked = false;
    }

    // gather the number of players, and pass the GameState and the UserInterface classes
    // access the logic there
    public CardResponse playCard(GameState g, UserInterface u, int playernum ) {

        CardResponse response;
        //Get the player playing the card
        Player p = g.getPlayers().get(playernum);

        //If the player has no board cards
        if(p.getBoardSize() == 0)
        {
            //The player cannot play this card
            response = new CardResponse(false, "Cannot play this card, no cards on board");
            return response;
        }

        // The card can be played
        response = new CardResponse(true, "Card has successfully been played!");

        //Get the color the player would like to charge rent on
        String color = u.promptColorSelection(p);

        // Ge the input from the user
        u.displayMessage("To whom are you charging this rent?");
        Player target = u.promptPlayerSelection(p, g.getPlayersExcept(playernum));

        // From the amounts that can be charged, choose the amount you want to charge rent
        // Basically the player will be prompted with money amounts, and they will choose the amount
        // they want to charge
        u.displayMessage("Select the amount you want to charge...");

        // Alert the player that they have been charged rent
        u.displayMessageToPlayer(target, "You have been charged rent!");

        // Charge the targeted player, placeholder
        ArrayList<Card> cards = target.chargeMoney(2, u);

        // Remove the card from the players hand and add to the discard pile
        g.getPlayers().get(playernum).removeFromBank(this);
        g.addToDiscardPile(this);

    return response;

    }
}