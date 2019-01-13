package com.example.jameswilliams.nu_deal.nu_deal_game;

public class MoneyCard extends Card
{

    public MoneyCard(int v)
    {
        this.value = v;
        this.name = Integer.toString(v) + " million";
        this.bankable = true;
        this.banked = false;
    }

    public CardResponse playCard(GameState g, UserInterface u, Player p)
    {
        CardResponse response = new CardResponse(true, "success");

        //Remove myself from the player's hand
        this.removeSelfFromPlayerHand(p);

        //Just add this card to the player's board
        p.addToBank(this);

        return response;
    }

}
