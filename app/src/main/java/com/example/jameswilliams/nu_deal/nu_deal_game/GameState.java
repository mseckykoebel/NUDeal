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
        allCards.add(new PropertyCard("IndianaAvenue", "Red", 3));
        allCards.add(new PropertyCard("IllinoisAvenue","Red",3));

        //Yellow Properties
        allCards.add(new PropertyCard("AtlanticAvenue","Yellow",3));
        allCards.add(new PropertyCard("VentnorAvenue","Yellow",3));
        allCards.add(new PropertyCard("MarvinGardens","Yellow",3));

        //Light Blue Properties
        allCards.add(new PropertyCard("ConnecticutAvenue","SkyBlue",1));
        allCards.add(new PropertyCard("VermontAvenue","SkyBlue",1));
        allCards.add(new PropertyCard("OrientalAvenue","SkyBlue",1));

        //Brown Properties
        allCards.add(new PropertyCard( "BalticAvenue", "SaddleBrown", 1));
        allCards.add(new PropertyCard("MediterraneanAvenue","SaddleBrown",1));

        //Orange Properties
        allCards.add(new PropertyCard("StJamesPlace","Orange", 2));
        allCards.add(new PropertyCard("NewYorkAvenue","Orange",2));
        allCards.add(new PropertyCard("TennesseeAvenue","Orange",2));

        //Purple Properties (these are DarkOrchid !!!!!!)
        allCards.add(new PropertyCard("StCharlesPlace","DarkOrchid",2));
        allCards.add(new PropertyCard("VirginiaAvenue","DarkOrchid",2));
        allCards.add(new PropertyCard("StatesAvenue","DarkOrchid", 2));

        // Utilities
        // Color group is grey
        allCards.add(new PropertyCard( "ElectricCompany", "Grey", 2));
        allCards.add(new PropertyCard( "WaterWorks", "Grey", 2));

        //Railroads
        allCards.add(new PropertyCard("ShortLine", "Black",2 ));
        allCards.add(new PropertyCard("ReadingRailroad", "Black",2));
        allCards.add(new PropertyCard("B&ORailroad","Black",2));
        allCards.add(new PropertyCard("PennsylvaniaRailroad","Black",2));

        //Two Type Wild Cards
        allCards.add(new PropertyCard("RedYellow1","Red","Yellow",3));
        allCards.add(new PropertyCard("RedYellow2","Red","Yellow",3));

        allCards.add(new PropertyCard("RailroadSkyBlue","Black","SkyBlue",4));
        allCards.add(new PropertyCard("RailRoadGrey","Black","Grey",4));

        allCards.add(new PropertyCard("OrangeDarkOrchid1","Orange","DarkOrchid",2));
        allCards.add(new PropertyCard("OrangeDarkOrchid2","Orange","DarkOrchid",2));

        allCards.add(new PropertyCard("BlueGreen","Blue","Green",4));

        allCards.add(new PropertyCard("SkyBlueSaddleBrown","SkyBlue","SaddleBrown",1));

        //Wild Cards
        allCards.add(new PropertyCard("WildCard1","",0));
        allCards.add(new PropertyCard("WildCard2","",0));

        //Houses
        allCards.add(new PropertyCard("House1",3));
        allCards.add(new PropertyCard("House2",3));
        allCards.add(new PropertyCard("House3",3));

        //Hotels
        allCards.add(new PropertyCard("Hotel1",4));
        allCards.add(new PropertyCard("Hotel2",4));

    }

    void addActionCards(){}
}
