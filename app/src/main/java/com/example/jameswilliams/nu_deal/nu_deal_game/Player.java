package com.example.jameswilliams.nu_deal.nu_deal_game;

import java.util.ArrayList;

public class Player
{
    private ArrayList<Card> hand;
    private ArrayList<Card> bank;
    private ArrayList<Card> board;
    private String name;

    public Player(String n)
    {
        name = n;
        hand = new ArrayList<Card>();
        bank = new ArrayList<Card>();
        board = new ArrayList<Card>();

    }

    public void addToHand(Card c)
    {
        hand.add(c);
    }
    public Card removeFromHand(int n)
    {
        return hand.remove(n);
    }
    public Card getFromHand(int n){return hand.get(n);}

    public void addToBank(Card c){bank.add(c);}
    public Card removeFromBank(int n){return bank.remove(n);}
    public Card getFromBank(int n){return bank.get(n);}

    public void addToBoard(Card c){board.add(c);}
    public Card removeFromBoard(int n){return board.remove(n);}
    public Card getFromBoard(int n){return board.get(n);}

    public int getHandSize(){return hand.size();}
    public int getBankSize(){return bank.size();}
    public int getBoardSize(){return board.size();}

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

    public String getAllCardString()
    {
        String cards = "\n====================\nHand:\n";
        //Generate the hand string
        for(int i = 0; i < hand.size(); i++)
        {
            Card currentCard = hand.get(i);
            String thisCard = Integer.toString(i) + ". " + currentCard.getName() + ", value = " + currentCard.getValue();
            cards += thisCard + "\n";
        }

        //Generate the board string
        cards += "Board:\n";

        for(int i = 0; i < board.size(); i++)
        {
            Card currentCard = board.get(i);
            String thisCard = Integer.toString(i+hand.size()) + ". " + currentCard.getName() + ", value = " + currentCard.getValue();
            cards += thisCard + "\n";
        }

        //Generate the bank string
        cards += "Bank:\n";
        for(int i = 0; i < bank.size(); i++)
        {
            Card currentCard = bank.get(i);
            String thisCard = Integer.toString(i+hand.size()+board.size()) + ". " + currentCard.getName() + ", value = " + currentCard.getValue();
            cards += thisCard + "\n";
        }
        return cards + "====================\n";
    }

    public String getPayableCardString()
    {
        //Generate the board string
        String cards = "Board:\n";

        for(int i = 0; i < board.size(); i++)
        {
            Card currentCard = board.get(i);
            String thisCard = Integer.toString(i) + ". " + currentCard.getName() + ", value = " + currentCard.getValue();
            cards += thisCard + "\n";
        }

        //Generate the bank string
        cards += "Bank:\n";
        for(int i = 0; i < bank.size(); i++)
        {
            Card currentCard = bank.get(i);
            String thisCard = Integer.toString(i+board.size()) + ". " + currentCard.getName() + ", value = " + currentCard.getValue();
            cards += thisCard + "\n";
        }
        return cards + "====================\n";
    }
}
