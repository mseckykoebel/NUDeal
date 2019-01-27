package com.example.jameswilliams.nu_deal.GameFiles.ActionCards;

// Import individually so I know where tf they all came from

import com.example.jameswilliams.nu_deal.GameFiles.Card;
import com.example.jameswilliams.nu_deal.GameFiles.CardResponse;
import com.example.jameswilliams.nu_deal.GameFiles.GameState;
import com.example.jameswilliams.nu_deal.GameFiles.Player;
import com.example.jameswilliams.nu_deal.GameFiles.UserInterface;

import java.util.ArrayList;

public class WildRentCard extends Card {
    // object constructor
    public WildRentCard() {
        this.name = "WildRentCard";
        this.value = 3;
        this.bankable = true;
        this.banked = false;
    }

    // gather the number of players, and pass the GameState and the UserInterface classes
    // access the logic there
    @Override
    public CardResponse playCard(GameState g, UserInterface u, Player p) {

        CardResponse response;

        //If the player has no board cards
        if (p.getBoardSize() == 0) {
            //The player cannot play this card
            response = new CardResponse(false, "Cannot play this card, no cards on board");
            return response;
        }

        // The card can be played
        response = new CardResponse(true, "Card has successfully been played!");

        //Get the color the player would like to charge rent on
        String color = u.promptColorSelection(p, p.getPropertyColors());

        //Get the target player
        u.displayMessage("To whom are you charging this rent?");
        Player target = u.promptPlayerSelection(p, g.getPlayersExcept(p));

        // Charge the targeted player, placeholder
        ArrayList<Card> cards = target.chargeMoney(p.calculateRent(color), u);

        //Give the cards to the player
        p.giveCards(cards);

        //remove self from players hand and discard
        this.removeSelfFromPlayerHand(p);
        g.addToDiscardPile(this);

        //Success
        return response;
    }
}