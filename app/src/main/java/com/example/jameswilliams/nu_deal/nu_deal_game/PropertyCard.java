package com.example.jameswilliams.nu_deal.nu_deal_game;

public class PropertyCard extends Card
{
    private String color1;
    private String color2;

    public PropertyCard(String n, String c1, int v)
    {
        this.name = n;
        this.color1 = c1;
        this.color2 = "";
        this.value = v;
        this.bankable = false;
        this.banked = true;
    }

    // Property WildCards taking on two color values, and can be banked
    public PropertyCard(String n, String c1, String c2, int v)
    {
        this.name = n;
        this.color1 = c1;
        this.color2 = c2;
        this.value = v;
        this.bankable = true;
        this.banked = true;

    }

    public String getColor1(){return color1;}
    public String getColor2(){return color2;}

    // Houses and Hotels that Can be banked, and qualify as property cards
    // More logic dealing with these will be done later
    public PropertyCard(String n, int v)
    {
        this.name = n;
        this.value = v;
        this.bankable = true;
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
