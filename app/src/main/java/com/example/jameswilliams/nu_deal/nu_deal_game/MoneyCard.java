package com.example.jameswilliams.nu_deal.nu_deal_game;

public class MoneyCard extends Card
{

    public MoneyCard(int v)
    {
        this.value = v;
        this.name = Integer.toString(v) + " million";
        this.bankable = true;
    }


}
