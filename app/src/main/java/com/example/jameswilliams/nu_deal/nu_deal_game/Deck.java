package com.example.jameswilliams.nu_deal.nu_deal_game;

import java.io.*;
import java.util.*;


public class Deck
{
    private ArrayList<Card> draw_pile;
    private ArrayList<Card> discard_pile;
    private int expected_size;

    //Constructor for an empty deck
    public Deck(int size)
    {
        draw_pile = new ArrayList<Card>();
        discard_pile = new ArrayList<Card>();
        expected_size = size;
    }

    public Deck(ArrayList<Card> c, int size)
    {
        draw_pile = (ArrayList<Card>)c.clone();
        discard_pile = new ArrayList<Card>();
        expected_size = size;
    }

    public Card drawCard()
    {
        return draw_pile.remove(0);
    }

    public void discardCard(Card c)
    {
        discard_pile.add(c);
    }

    //Returns all cards to draw pile and shuffles deck
    //Returns true if all cards were present, false otherwise
    public boolean reset()
    {
        //If we don't have all of the cards
        if(draw_pile.size() + discard_pile.size() != expected_size)
        {
            //Fail
            return false;
        }

        //Move all of the cards to the draw pile
        draw_pile.addAll(discard_pile);

        //Empty the discard pile
        discard_pile.clear();

        //Shuffle the deck
        Collections.shuffle(draw_pile);

        //Success
        return true;
    }




}