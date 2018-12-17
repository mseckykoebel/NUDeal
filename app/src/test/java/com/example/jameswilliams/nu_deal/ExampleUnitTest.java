package com.example.jameswilliams.nu_deal;

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

        //Make sure we have 106 cards
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
            g.dealPlayer(i, 2);
            assertEquals(7, g.getPlayers().get(i).getHandSize());
        }

        //Try dealing all players five cards
        for(int i = 0; i < g.getPlayers().size(); i++)
        {
            g.dealPlayer(i, 5);
            assertEquals(12, g.getPlayers().get(i).getHandSize());
        }
    }


}