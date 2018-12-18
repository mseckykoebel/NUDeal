package com.example.jameswilliams.nu_deal.nu_deal_game.ActionCards;

import com.example.jameswilliams.nu_deal.nu_deal_game.Card;
import com.example.jameswilliams.nu_deal.nu_deal_game.CardResponse;
import com.example.jameswilliams.nu_deal.nu_deal_game.GameState;
import com.example.jameswilliams.nu_deal.nu_deal_game.UserInterface;

public class DoubleRent extends Card {

    // object constructor
    public DoubleRent()
    {
        this.name ="DoubleTheRent";
        this.value = 1;
        this.bankable = true;
        this.banked = false;
    }

    public CardResponse playCard(GameState g, UserInterface u, int playernum) {

        CardResponse response = new CardResponse(true, "Card has successfully been played!");

        // The card is applied to the played rent card, and is also applied to one of the rent cards

        return response;
    }
}
