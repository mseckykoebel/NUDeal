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

    public String getName(){return name;}
    public int getValue(){return value;}
    public boolean isBankable() { return bankable; }

    //Should return "success" if play was successful, error message otherwise
    public CardResponse playCard(GameState g, UserInterface u, int playernum)
    {
        return new CardResponse(false, "Method not implemented for this card");
    }

    public void removeSelfFromPlayerHand(GameState g, int playernum)
    {
        //Figure out which card the player is playing
        int cardIndex = 0;
        for(int i = 0; i < g.getPlayers().get(playernum).getHandSize(); i++)
        {
            if(g.getPlayers().get(playernum).getFromHand(i).getName() == this.name)
            {
                cardIndex = i;
                break;
            }
        }
        g.getPlayers().get(playernum).removeFromHand(cardIndex);
    }

}