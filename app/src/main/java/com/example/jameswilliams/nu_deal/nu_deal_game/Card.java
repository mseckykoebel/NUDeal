package com.example.jameswilliams.nu_deal.nu_deal_game;

public abstract class Card
{

    //Internal Variables
    public String name;//"Boardwalk"
    public int value;//"4"
    public boolean bankable;//false

    public Card()
    {

    }

    //Should return "success" if play was successful, error message otherwise
    public String playCard(GameState g, int playernum, int targetnum)
    {
        return "Method not implemented for this card";
    }

}