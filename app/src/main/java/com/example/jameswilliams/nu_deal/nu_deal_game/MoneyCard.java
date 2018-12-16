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

    public CardResponse playCard(GameState g, UserInterface u, int playernum)
    {
        CardResponse response = new CardResponse(true, "success");

        //Remove myself from the player's hand
        this.removeSelfFromPlayerHand(g, playernum);

        //Just add this card to the player's board
        g.getPlayers().get(playernum).addToBoard(this);

        return response;
    }

}
