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

        // The card has been played
        CardResponse response = new CardResponse(true, "Card has successfully been played!");

        // Ge the input from the user
        u.displayMessage("To whom are you charging this rent?");
        Player target = u.promptPlayerSelection(g.getPlayers().get(playernum), g.getPlayersExcept(playernum));

        // From the amounts that can be charged, choose the amount you want to charge rent
        // Basically the player will be prompted with money amounts, and they will choose the amount
        // they want to charge
        u.displayMessage("Slect the amount you want to charge...");

        // Alert the player that they have been charged rent
        u.displayMessageToPlayer(g.getPlayers().get(playernum), "You have been charged rent!");

        // Charge the targeted player, placeholder
        ArrayList<Card> cards = target.chargeMoney(2, u);

        // Remove the card from the players hand and add to the discard pile
        g.getPlayers().get(playernum).removeFromBank(this);
        g.addToDiscardPile(this);

    return response;

    }
}