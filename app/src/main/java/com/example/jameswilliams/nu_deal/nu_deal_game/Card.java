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

    //Should return "success" if play was successfull, error message otherwise
    public String playCard()
    {
        return "Method not implemented for this card";
    }

}