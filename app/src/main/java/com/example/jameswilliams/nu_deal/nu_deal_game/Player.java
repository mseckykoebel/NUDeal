package com.example.jameswilliams.nu_deal.nu_deal_game;

import java.util.ArrayList;

public class Player
{
    private ArrayList<Card> hand;
    private ArrayList<Card> bank;
    private String name;

    public Player(String n)
    {
        // the player object contains a name (will be the user name), and two arrays for the hand and the bank
        name = n;
        hand = new ArrayList<Card>();
        bank = new ArrayList<Card>();

    }

    public void addToHand(Card c)
    {
        hand.add(c);
    }

    public Card removeFromHand(int n)
    {
        return hand.remove(n);
    }

    public void addToBank(Card c){ bank.add(c);}

    public Card removeFromBank(int n){return bank.remove(n);}

    public int getHandSize(){return hand.size();}
    public int getBankSize(){return bank.size();}

    public Card getCard(int c)
    {
        return hand.get(c);
    }

    public void reset()
    {
        hand.clear();
        bank.clear();
    }

    public String getName(){return name;}
}
