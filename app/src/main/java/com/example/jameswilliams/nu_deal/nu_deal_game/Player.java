package com.example.jameswilliams.nu_deal.nu_deal_game;

import java.util.ArrayList;

public class Player
{
    private ArrayList<Card> hand;
    private ArrayList<Card> bank;
    public int number;
    public String name;

    public Player(String n, int num)
    {
        number = num;
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

    public void addToBank(Card c){bank.add(c);}

    public Card removeFromBank(int n){return bank.remove(n);}

    public int getHandSize(){return hand.size();}
}
