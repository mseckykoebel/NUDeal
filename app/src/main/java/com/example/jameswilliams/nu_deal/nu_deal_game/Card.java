package com.example.jameswilliams.nu_deal.nu_deal_game;

public abstract class Card
{

    //Internal Variables
    private String name;//"Boardwalk"
    private int value;//"4"
    private boolean bankable;//false

    public Card()
    {

    }

    public String getName(){return name;}
    public int getValue(){return value;}
    public boolean isBankable() { return bankable; }

    //Should return "success" if play was successful, error message otherwise
    public CardResponse playCard(GameState g, UserInterface u, int playernum)
    {
        return new CardResponse(false, "Method not implemented for this card");
    }

}