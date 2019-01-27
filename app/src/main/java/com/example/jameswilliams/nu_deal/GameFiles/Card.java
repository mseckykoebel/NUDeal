package com.example.jameswilliams.nu_deal.GameFiles;

public abstract class Card
{

    //Internal Variables
    public String name;//"Boardwalk"
    public int value;//"4"
    public boolean bankable;//false
    public boolean banked;

    public Card()
    {

    }

    public String getName(){return name;}
    public int getValue(){return value;}
    public boolean isBankable() { return bankable; }
    public boolean isBanked(){return banked;}

    //Should return "success" if play was successful, error message otherwise
    public CardResponse playCard(GameState g, UserInterface u, Player p)
    {
        return new CardResponse(false, "Method not implemented for this card");
    }

    public boolean removeSelfFromPlayerHand(Player p)
    {
        return p.removeFromHand(this);
    }

}