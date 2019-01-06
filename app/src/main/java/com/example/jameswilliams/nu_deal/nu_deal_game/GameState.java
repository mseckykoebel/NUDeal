package com.example.jameswilliams.nu_deal.nu_deal_game;
import com.example.jameswilliams.nu_deal.nu_deal_game.ActionCards.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class GameState
{
    // Defining the players, the cards, the drawPile and the discardPile as arrays
    private ArrayList<Player> players;
    private ArrayList<Card> allCards;
    private ArrayList<Card> drawPile;
    private ArrayList<Card> discardPile;
    private boolean direction; //True is low to high, false is high to low
    private int whose_turn; // ?????

    // Defining primary global variables, unchanged
    private final int num1Mil = 6;
    private final int num2Mil = 5;
    private final int num3Mil = 3;
    private final int num4Mil = 3;
    private final int num5Mil = 2;
    private final int num10Mil = 1;

    private final int numDealBreakers = 2;
    private final int numSlyDeals = 3;
    private final int numForcedDeals = 4;
    private final int numGlobalRents = 2;
    private final int numWildRents = 3;
    private final int numDebtCollectors = 3;
    private final int numBirthdays = 3;
    private final int numDoubleRents = 2;
    private final int numPassGos = 10;
    private final int numJustSayNos = 3;

    
    public GameState()
    {
        // init
        allCards = new ArrayList<Card>();
        drawPile = new ArrayList<Card>();
        discardPile = new ArrayList<Card>();
        players = new ArrayList<Player>();
        direction = false;

        //Player 0 will start
        whose_turn = 0;

    }


    public void addPlayer(Player p)
    {
        players.add(p);
    }

    //Returns true if game was successfully initialized, false otherwise
    //This will also reset the game state
    public boolean initGame()
    {
        //If we don't have enough players
        if(players.size() < 2)
        {
            System.out.println("Error, game state must have more than two players before being initialized");
            return false;
        }

        //Clear all of the player hands
        for(int i = 0; i < players.size(); i++)
        {
            players.get(i).reset();
        }

        //Add all cards allCards
        initCards();

        //Add all cards to deck and shuffle
        for(int i = 0; i < allCards.size(); i++)
        {
            drawPile.add(allCards.get(i));
        }
        Collections.shuffle(drawPile);

        //Deal cards to players
        dealPlayersIn();

        return true;
    }

    public int getAllCardSize()
    {
        return allCards.size();
    }

    public ArrayList<Card> getAllCards()
    {
        return allCards;
    }

    public int whoseTurn()
    {
        return whose_turn;
    }

    public void addToDiscardPile(Card c){discardPile.add(c);}

    public void nextTurn()
    {
        if(whose_turn == players.size() - 1)
        {
            whose_turn = 0;
        }else {
            whose_turn++;
        }

        //If draw pile is smaller than 10
        if(drawPile.size() < 10)
        {
            Collections.shuffle(discardPile);
            drawPile.addAll(discardPile);
            discardPile.clear();
        }
    }

    public Player winDetected()
    {
        ///TODO
        return null;
    }

    //////////////////
    //Player Dealing//
    //////////////////

    private void dealPlayersIn()
    {
        //Deal each player 5 cards
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < players.size(); j++)
            {
                players.get(j).addToHand(drawPile.remove(0));
            }
        }
    }

    //Deals player p n cards
    public void dealPlayer(Player p, int n)
    {
        for(int i = 0; i < n; i++)
        {
            p.addToHand(drawPile.remove(0));
        }
    }

    public int getDrawPileSize()
    {
        return drawPile.size();
    }

    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    //Returns list of players excluding pnum
    public ArrayList<Player> getPlayersExcept(Player p)
    {
        ArrayList<Player> ret = new ArrayList<Player>();
        for(int i = 0; i < players.size(); i++)
        {
            if(p != players.get(i))
            {
                ret.add(players.get(i));
            }
        }
        return ret;
    }

    //Gets the takable property cards from everyone except the specified player
    public ArrayList<Card> getTakableCards(Player p)
    {
        ArrayList<Card> list = new ArrayList<>();
        for(int i = 0; i < players.size(); i++){
            if(players.get(i) != p){
                list.addAll(players.get(i).getTradablePropertiesList());
            }
        }
        return list;
    }


    //Initializes the allCards array
    public void initCards()
    {
        allCards.clear();
        drawPile.clear();
        discardPile.clear();

        // Calls our non-returnable action functions
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
        for(int i = 0; i < num10Mil; i++) {
            allCards.add(new MoneyCard(10));
        }

    }


// Defining all of the property cards, which are initialized in the latter logic
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
        allCards.add(new PropertyCard( "ElectricCompany", "Utility", 2));
        allCards.add(new PropertyCard( "WaterWorks", "Utility", 2));

        //Railroads
        allCards.add(new PropertyCard("ShortLine", "Railroad",2 ));
        allCards.add(new PropertyCard("ReadingRailroad", "Railroad",2));
        allCards.add(new PropertyCard("B&ORailroad","Railroad",2));
        allCards.add(new PropertyCard("PennsylvaniaRailroad","Railroad",2));

        //Two Type Wild Cards
        allCards.add(new PropertyCard("RedYellow1","Red","Yellow",3));
        allCards.add(new PropertyCard("RedYellow2","Red","Yellow",3));

        allCards.add(new PropertyCard("RailroadSkyBlue","Railroad","SkyBlue",4));
        allCards.add(new PropertyCard("RailRoadUtility","Railroad","Utility",4));
        allCards.add(new PropertyCard("RailroadGreen", "Railroad", "Green", 4));

        allCards.add(new PropertyCard("OrangeDarkOrchid1","Orange","DarkOrchid",2));
        allCards.add(new PropertyCard("OrangeDarkOrchid2","Orange","DarkOrchid",2));

        allCards.add(new PropertyCard("BlueGreen","Blue","Green",4));

        allCards.add(new PropertyCard("SkyBlueSaddleBrown","SkyBlue","SaddleBrown",1));

        //Wild Cards
        allCards.add(new PropertyCard("WildCard","",0));
        allCards.add(new PropertyCard("WildCard","",0));

        //Houses
        allCards.add(new PropertyCard("House",3));
        allCards.add(new PropertyCard("House",3));
        allCards.add(new PropertyCard("House",3));

        //Hotels
        allCards.add(new PropertyCard("Hotel",4));
        allCards.add(new PropertyCard("Hotel",4));
        allCards.add(new PropertyCard("Hotel",4));


    }

    void addActionCards()
    {
        //Two Deal Breakers
        for(int i = 0; i < numDealBreakers; i++)
        {
            allCards.add(new DealBreaker());
        }

        //Three sly deals
        for(int i = 0; i < numSlyDeals; i++)
        {
            allCards.add(new SlyDeal());
        }

        //Three Forced Deals
        for(int i = 0; i < numForcedDeals; i++)
        {
            allCards.add(new ForcedDeal());
        }

        //Global Rent Cards
        for(int i = 0; i < numGlobalRents; i++)
        {
            //Blue Green Rents
            allCards.add(new RentCard("Blue", "Green"));
            //Red Yellow Rents
            allCards.add(new RentCard("Red", "Yellow"));
            //SkyBlue SaddleBrown Rents
            allCards.add(new RentCard("SkyBlue", "SaddleBrown"));
            //Orange DarkOrchid Rents
            allCards.add(new RentCard("Orange", "DarkOrchid"));
            //Railroad/Utility Rents
            allCards.add(new RentCard("Railroad", "Utility"));
        }

        //Wild Rent Cards
        for(int i = 0; i < numWildRents; i++)
        {
            allCards.add(new WildRentCard());
        }

        //Three Debt Collectors
        for(int i = 0; i < numDebtCollectors; i++)
        {
            allCards.add(new DebtCollector());
        }

        //Three Birthdays
        for(int i = 0; i < numBirthdays; i++)
        {
            allCards.add(new Birthday());
        }

        //Two Double Rents
        for(int i = 0; i < numDoubleRents; i++)
        {
            allCards.add(new DoubleRent());
        }

        //Ten Pass Gos
        for(int i = 0; i < numPassGos; i++)
        {
            allCards.add(new PassGo());
        }

        //Three Just Say No's
        for(int i = 0; i < numJustSayNos; i++)
        {
            allCards.add(new JustSayNo());
        }


    }
}
