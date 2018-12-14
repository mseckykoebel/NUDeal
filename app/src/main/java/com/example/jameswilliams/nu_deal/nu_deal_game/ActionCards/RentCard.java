package com.example.jameswilliams.nu_deal.nu_deal_game.ActionCards;

import com.example.jameswilliams.nu_deal.nu_deal_game.Card;

public class RentCard extends Card
{
    private String color1;
    private String color2;

    public RentCard(String c1, String c2)
    {
        color1 = c1;
        color2 = c2;
        this.value = 1;
        this.name = "RendCard";
        this.bankable = true;
    }
}
