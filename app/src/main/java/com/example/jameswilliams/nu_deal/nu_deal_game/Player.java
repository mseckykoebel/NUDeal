package com.example.jameswilliams.nu_deal.nu_deal_game;

import java.util.ArrayList;

public class Player
{
    public ArrayList<Card> hand;
    public int number;
    public String name;

    public Player(String n, int num)
    {
        number = num;
        name = n;
    }

    public void addToHand(Card c)
    {
        hand.add(c);
    }

    public Card removeFromHand(int n)
    {
        return hand.remove(n);
    }
}
