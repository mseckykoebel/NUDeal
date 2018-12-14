package com.example.jameswilliams.nu_deal.nu_deal_game;

import java.util.ArrayList;

public class GameState
{
    public ArrayList<Player> players;
    public ArrayList<Card> allCards;
    public ArrayList<Card> drawPile;
    public ArrayList<Card> discardPile;
    public boolean direction;//True is low to high, false is high to low
    public int whos_turn;

    private final int num1Mil = 6;
    private final int num2Mil = 5;
    private final int num3Mil = 3;
    private final int num4Mil = 3;
    private final int num5Mil = 2;
    private final int num10Mil = 1;
    

    public void reset()
    {
        //Remove all of the players cards and put them into the deck.
    }

    //Initializes the allCards array
    public void initCards()
    {
        allCards.clear();

        addMoneyCards();
        addPropertyCards();
        addActionCards();

    }

    void addMoneyCards()
    {
        //6 1 Million Dollar Cards
        for(int i = 0; i < num1Mil; i++)
        {
            allCards.add(new MoneyCard(1));
        }

        //5 2 Million Dollar Cards
        for(int i = 0; i < num2Mil; i++)
        {
            allCards.add(new MoneyCard(2));
        }

        //3 3 Million Dollar Cards
        for(int i = 0; i < num3Mil; i++)
        {
            allCards.add(new MoneyCard(3));
        }

        //3 4 Million Dollar Cards
        for(int i = 0; i < num4Mil; i++)
        {
            allCards.add(new MoneyCard(4));
        }

        //2 5 Million Dollar Cards
        for(int i = 0; i < num5Mil; i++)
        {
            allCards.add(new MoneyCard(5));
        }

        //1 10 Million Dollar Card
        allCards.add(new MoneyCard(10));


    }

    void addPropertyCards()
    {
        //Blue Properties
        allCards.add(new PropertyCard("BoardWalk", "Blue",4 ));
        allCards.add(new PropertyCard("ParkPlace", "Blue",4 ));

        //Green Properties
        allCards.add(new PropertyCard("NorthCarolinaAvenue", "Green",4 ));
        allCards.add(new PropertyCard("PacificAvenue", "Green",4 ));
        allCards.add(new PropertyCard("PennsylvaniaAvenue", "Green",4 ));

        //Red Properties
        allCards.add(new PropertyCard("KentuckyAvenue", "Red",3 ));

        //Yellow Properties

        //Light Blue Properties

        //Brown Properties

        //Orange Properties

        //Purple Properties

        //Utilities

        //Railroads

        //Two Type Wild Cards

        //Wild Cards

        //Houses

        //Hotels


    }

    void addActionCards(){}
}
