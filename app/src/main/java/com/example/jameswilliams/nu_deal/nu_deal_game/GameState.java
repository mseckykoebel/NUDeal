package com.example.jameswilliams.nu_deal.nu_deal_game;

import java.util.ArrayList;

public class GameState
{
    public Deck card_deck;
    public ArrayList<Player> players;
    public boolean direction;//True is low to high, false is high to low
    public int whos_turn;


    

    public void reset(){}

}
