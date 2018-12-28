package com.example.jameswilliams.nu_deal;

import android.util.Property;

import com.example.jameswilliams.nu_deal.nu_deal_game.*;


import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void game_init_works()
    {
        GameState g = new GameState();
        //Generate a test player
        g.addPlayer(new Player("James"));
        g.addPlayer(new Player("Mason"));
        g.addPlayer(new Player("Sam"));

        g.initGame();

        //Make sure we have 108 cards
        assertEquals(108, g.getAllCardSize());

        //Make sure we have 45 properties including hotels and houses
        //20 money cards, and 43 action cards
        ArrayList<Card> cards = g.getAllCards();
        int moneyCards = 0;
        int propertyCards = 0;
        int actionCards = 0;

        //Count all of the property and money cards
        for(int i = 0; i < cards.size(); i++)
        {
            if(cards.get(i).getClass() == MoneyCard.class)
            {
                moneyCards++;
            }
            else if (cards.get(i).getClass() == PropertyCard.class)
            {
                propertyCards++;
            }
            else { actionCards++;}
        }

        assertEquals(20, moneyCards);
        assertEquals(45, propertyCards);
        assertEquals(43, actionCards);

        //Make sure each player has 4 cards
        ArrayList<Player> players = g.getPlayers();

        for(int i = 0; i < players.size(); i++)
        {
            assertEquals(5, players.get(i).getHandSize());
        }

        //Make sure the draw pile size is correct
        assertEquals(108-(g.getPlayers().size()*5), g.getDrawPileSize());
    }

    @Test
    public void player_dealing_works()
    {
        GameState g = new GameState();
        //Generate a test player
        g.addPlayer(new Player("James"));
        g.addPlayer(new Player("Mason"));
        g.addPlayer(new Player("Sam"));

        g.initGame();

        //Try dealing all players two cards
        for(int i = 0; i < g.getPlayers().size(); i++)
        {
            g.dealPlayer(g.getPlayers().get(i), 2);
            assertEquals(7, g.getPlayers().get(i).getHandSize());
        }

        //Try dealing all players five cards
        for(int i = 0; i < g.getPlayers().size(); i++)
        {
            g.dealPlayer(g.getPlayers().get(i), 5);
            assertEquals(12, g.getPlayers().get(i).getHandSize());
        }
    }

    @Test
    public void can_split_new_line()
    {
        String test = "this\nis\na\ntest";

        String[] ls = test.split("\n");

        assertEquals(4, ls.length);
        assertEquals("this", ls[0]);
        assertEquals("is", ls[1]);
        assertEquals("a", ls[2]);
        assertEquals("test", ls[3]);
    }

    @Test
    public void rent_table_works()
    {
        //Red
        assertEquals(2, PropertyCard.getRentValue("Red", 1));
        assertEquals(3, PropertyCard.getRentValue("Red", 2));
        assertEquals(6, PropertyCard.getRentValue("Red", 3));

        //Yellow
        assertEquals(2, PropertyCard.getRentValue("Yellow", 1));
        assertEquals(4, PropertyCard.getRentValue("Yellow", 2));
        assertEquals(6, PropertyCard.getRentValue("Yellow", 3));

        //Blue
        assertEquals(3, PropertyCard.getRentValue("Blue", 1));
        assertEquals(8, PropertyCard.getRentValue("Blue", 2));

        //Green
        assertEquals(2, PropertyCard.getRentValue("Green", 1));
        assertEquals(4, PropertyCard.getRentValue("Green", 2));
        assertEquals(7, PropertyCard.getRentValue("Green", 3));

        //Purple
        assertEquals(1, PropertyCard.getRentValue("DarkOrchid", 1));
        assertEquals(2, PropertyCard.getRentValue("DarkOrchid", 2));
        assertEquals(4, PropertyCard.getRentValue("DarkOrchid", 3));

        //Orange
        assertEquals(1, PropertyCard.getRentValue("Orange", 1));
        assertEquals(3, PropertyCard.getRentValue("Orange", 2));
        assertEquals(5, PropertyCard.getRentValue("Orange", 3));

        //Brown
        assertEquals(1, PropertyCard.getRentValue("SaddleBrown", 1));
        assertEquals(2, PropertyCard.getRentValue("SaddleBrown", 2));

        //Sky Blue
        assertEquals(1, PropertyCard.getRentValue("SkyBlue", 1));
        assertEquals(2, PropertyCard.getRentValue("SkyBlue", 2));
        assertEquals(3, PropertyCard.getRentValue("SkyBlue", 3));

        //Railroad
        assertEquals(1, PropertyCard.getRentValue("Railroad", 1));
        assertEquals(2, PropertyCard.getRentValue("Railroad", 2));
        assertEquals(3, PropertyCard.getRentValue("Railroad", 3));
        assertEquals(4, PropertyCard.getRentValue("Railroad", 4));

        //Utility
        assertEquals(1, PropertyCard.getRentValue("Utility", 1));
        assertEquals(2, PropertyCard.getRentValue("Utility", 2));


    }

    @Test
    public void house_hotel_set_check_works()
    {
        //Create a set
        Set set = new Set();

        //Create some cards
        PropertyCard card0 = new PropertyCard("BoardWalk", "Blue",4 );
        PropertyCard card1 = new PropertyCard("ParkPlace", "Blue",4 );

        //Make sure the set isnt full
        assertEquals(false, set.isFullSet());
        //Add a card
        assertEquals(true, set.add(card0));
        //Make sure the set isnt full
        assertEquals(false, set.isFullSet());

        //Try adding a house
        PropertyCard house = new PropertyCard("House",3);
        assertEquals(false, set.add(house));

        //Add a card
        assertEquals(true, set.add(card1));

        //Shouldn't let us add past a full set
        assertEquals(false, set.add(card0));

        //Make sure the set is full
        assertEquals(true, set.isFullSet());

        //Try adding a hotel before a house
        PropertyCard hotel = new  PropertyCard("Hotel",4);
        assertEquals(false, set.add(hotel));

        //Add the house and the hotel
        assertEquals(true, set.add(house));
        assertEquals(true, set.add(hotel));



    }

    @Test
    public void set_card_add_and_remove_work(){}


}