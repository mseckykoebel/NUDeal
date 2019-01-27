package com.example.jameswilliams.nu_deal.GameFiles.ActionCards;

import com.example.jameswilliams.nu_deal.GameFiles.Card;
import com.example.jameswilliams.nu_deal.GameFiles.CardResponse;
import com.example.jameswilliams.nu_deal.GameFiles.GameState;
import com.example.jameswilliams.nu_deal.GameFiles.Player;
import com.example.jameswilliams.nu_deal.GameFiles.UserInterface;

public class JustSayNo extends Card
{
    // object constructor
    public JustSayNo()
    {
        this.name = "JustSayNo";
        this.value = 3;
        this.bankable = true;
        this.banked = false;
    }

    @Override
    public CardResponse playCard(GameState g, UserInterface u, Player p) {
        //This card cannot be played directly, it must be played through the associated function in Player.java
        return new CardResponse(false, "Cannot play a Just Say No directly.");
    }
}
