package com.example.jameswilliams.nu_deal;

import com.example.jameswilliams.nu_deal.nu_deal_game.ActionCards.DealBreaker;
import com.example.jameswilliams.nu_deal.nu_deal_game.ActionCards.ForcedDeal;
import com.example.jameswilliams.nu_deal.nu_deal_game.ActionCards.SlyDeal;
import com.example.jameswilliams.nu_deal.nu_deal_game.Card;
import com.example.jameswilliams.nu_deal.nu_deal_game.DummyUserInterface;
import com.example.jameswilliams.nu_deal.nu_deal_game.GameState;
import com.example.jameswilliams.nu_deal.nu_deal_game.MoneyCard;
import com.example.jameswilliams.nu_deal.nu_deal_game.NUDeal;
import com.example.jameswilliams.nu_deal.nu_deal_game.Player;
import com.example.jameswilliams.nu_deal.nu_deal_game.PropertyCard;
import com.example.jameswilliams.nu_deal.nu_deal_game.Set;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GamePlayTests {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void deal_breaker_test(){
        //Make a new game state
        GameState g = new GameState();

        //Make two players
        Player player0 = new Player("James");
        Player player1 = new Player("Petros");

        //Give player0 a full set
        player0.addToHand(new PropertyCard( "BalticAvenue", "SaddleBrown", 1));
        player0.addToHand(new PropertyCard("MediterraneanAvenue","SaddleBrown",1));

        //Give player1 a deal breaker
        player1.addToHand(new DealBreaker());

        //add some cards to the game state
        for(int i = 0; i < 20; i++){
            Card c = new MoneyCard(1);
            g.addToAllCards(c);
            g.addToDrawPile(c);
        }

        ArrayList<String> gameOutput = new ArrayList<>();
        ArrayList<Integer> cardChoices = new ArrayList<>();
        ArrayList<Integer> playerChoices = new ArrayList<>();
        ArrayList<String> colorChoices = new ArrayList<>();

        //Add the choices

        //Play the two properties
        cardChoices.add(0);
        cardChoices.add(0);

        //Pass the turn
        cardChoices.add(5);

        //Play the dealbreaker
        cardChoices.add(0);

        //Select the first player
        playerChoices.add(0);

        //Select the brown color
        colorChoices.add("SaddleBrown");

        //Prep the user interface
        DummyUserInterface u = new DummyUserInterface(gameOutput, cardChoices, playerChoices, colorChoices);

        //Start the game
        NUDeal game = new NUDeal(g, u);

        //Run two turns
        game.executeTurn();
        game.executeTurn();

        //Make sure that player1 has 1 full set
        assertEquals(1, player1.getFullSetColors().size());

        //Make sure the set color is brown
        assertEquals("SaddleBrown", player1.getFullSetColors().get(0));

        //Make sure player0 has nothing
        assertEquals(0, player0.getBoardSize());
        assertEquals(0, player0.getFullSetColors().size());

    }

    @Test
    public void forced_deal_test()
    {
        //Make a new game state
        GameState g = new GameState();

        //Make two players
        Player player0 = new Player("James");
        Player player1 = new Player("Petros");

        //Give player0 a card
        player0.addToHand(new PropertyCard( "BalticAvenue", "SaddleBrown", 1));

        //Give player1 a card and a forced deal
        player1.addToHand(new PropertyCard("MediterraneanAvenue","SaddleBrown",1));
        player1.addToHand(new ForcedDeal());

        //add some cards to the game state
        for(int i = 0; i < 20; i++){
            Card c = new MoneyCard(1);
            g.addToAllCards(c);
            g.addToDrawPile(c);
        }

        ArrayList<String> gameOutput = new ArrayList<>();
        ArrayList<Integer> cardChoices = new ArrayList<>();
        ArrayList<Integer> playerChoices = new ArrayList<>();
        ArrayList<String> colorChoices = new ArrayList<>();

        //Add the choices

        //Play the property
        cardChoices.add(0);

        //Pass the turn
        cardChoices.add(5);

        //Play the property
        cardChoices.add(0);

        //Play the forced deal
        cardChoices.add(0);

        //Select the first player
        playerChoices.add(0);

        //Select the brown property
        cardChoices.add(0);

        //Prep the user interface
        DummyUserInterface u = new DummyUserInterface(gameOutput, cardChoices, playerChoices, colorChoices);

        //Start the game
        NUDeal game = new NUDeal(g, u);

        //Run two turns
        game.executeTurn();
        game.executeTurn();

        //Make sure that player1 has 1 property
        assertEquals(1, player1.getBoardSize());
        assertEquals("BalticAvenue", player1.getCardsFromSets().get(0).getName());

        //Same for player0
        assertEquals(1, player0.getBoardSize());
        assertEquals("MediterraneanAvenue", player0.getCardsFromSets().get(0).getName());

    }

    @Test
    public void sly_deal_test()
    {
        //Make a new game state
        GameState g = new GameState();

        //Make two players
        Player player0 = new Player("James");
        Player player1 = new Player("Petros");

        //Give player0 a card
        player0.addToHand(new PropertyCard( "BalticAvenue", "SaddleBrown", 1));

        //Give player1 a sly seal
        player1.addToHand(new SlyDeal());

        //add some cards to the game state
        for(int i = 0; i < 20; i++){
            Card c = new MoneyCard(1);
            g.addToAllCards(c);
            g.addToDrawPile(c);
        }

        ArrayList<String> gameOutput = new ArrayList<>();
        ArrayList<Integer> cardChoices = new ArrayList<>();
        ArrayList<Integer> playerChoices = new ArrayList<>();
        ArrayList<String> colorChoices = new ArrayList<>();

        //Add the choices

        //Play the property
        cardChoices.add(0);

        //Pass the turn
        cardChoices.add(5);

        //Play the forced deal
        cardChoices.add(0);

        //Select the first player
        playerChoices.add(0);

        //Select the brown property
        cardChoices.add(0);

        //Prep the user interface
        DummyUserInterface u = new DummyUserInterface(gameOutput, cardChoices, playerChoices, colorChoices);

        //Start the game
        NUDeal game = new NUDeal(g, u);

        //Run two turns
        game.executeTurn();
        game.executeTurn();

        //Make sure that player1 has 1 property
        assertEquals(1, player1.getBoardSize());
        assertEquals("BalticAvenue", player1.getCardsFromSets().get(0).getName());

        //Make sure the sly deal made it to the discard pile
        assertEquals(1, g.getDiscardPile().get(0).getClass() == SlyDeal.class);

        //Same for player0
        assertEquals(0, player0.getBoardSize());

    }





}