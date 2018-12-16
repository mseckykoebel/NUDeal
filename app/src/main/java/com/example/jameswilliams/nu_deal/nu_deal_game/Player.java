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

    public ArrayList<OKPair>  getPayableCardOKList()
    {
        ArrayList<OKPair> cards = new ArrayList<OKPair>();

        //Generate the board
        for(int i = 0; i < board.size(); i++)
        {
            cards.add(new OKPair(board.get(i), i));
        }

        //Generate the bank
        for(int i = 0; i < bank.size(); i++)
        {
            cards.add(new OKPair(bank.get(i), i+board.size()));
        }
        return cards;
    }

    //This takes the cards out of the player's board and bank
    public ArrayList<Card> chargeMoney(int ammount, UserInterface u)
    {
        ArrayList<Card> money = new ArrayList<Card>();

        //Check if they have 0 cards
        if(bank.size() + board.size() == 0)
        {
            u.displayMessage("You have no money so you don't have to pay!");
            return money;
        }

        //Check if they have 1 card
        if(bank.size() + board.size() == 1)
        {
            //If it's in the bank
            if(bank.size() == 1)
            {
                //Remove it from the player's bank
                money.add(this.removeFromBank(0));
                u.displayMessage("You only have " + money.get(0).getName() + ", so you must pay with it.");
                return money;
            }else{
                //Must be on the board
                money.add(this.removeFromBoard(0));
                u.displayMessage("You only have " + money.get(0).getName() + ", so you must pay with it.");
                return money;
            }


        }

        //Get list of payable cards
        ArrayList<OKPair> cards = this.getPayableCardOKList();
        while(true) {
            //List the cards they can pay with
            u.displayMessage("You must pay " + ammount + " million\nHow would you like to pay?");
            u.displayMessage("To select multiple cards, say: 1 2 3....");

            String input = u.getInput(OKPair.OKListToString(cards, "Payable cards:"));

            //Try converting the input to a number
            try {
                int cardChoices[] = u.stringToIntArray(input);
                //Get the cards
                ArrayList<Card> boardCards = new ArrayList<Card>();
                ArrayList<Card> bankCards = new ArrayList<Card>();

                for (int j = 0; j < cardChoices.length; j++) {
                    //If the card is on the board
                    if (cardChoices[j] < this.getBoardSize()) {
                        //Add that card to the board cards list
                        boardCards.add(this.getFromBoard(cardChoices[j]));
                    } else {
                        //Add that card to the bank cards list
                        bankCards.add(this.getFromBank(cardChoices[j] - this.getBoardSize()));
                    }
                }

                //Make sure the value of the cards adds up
                int value = 0;
                for (int j = 0; j < bankCards.size(); j++) {
                    value += bankCards.get(j).getValue();
                }

                for (int j = 0; j < boardCards.size(); j++) {
                    value += boardCards.get(j).getValue();
                }

                //If it was enough
                if (value >= 2) {
                    u.displayMessage("Payment successful");
                    //Take cards from this player
                    //TODO
                    //Return them
                    money.addAll(boardCards);
                    money.addAll(bankCards);
                    return money;
                } else {
                    u.displayMessage("Value of cards is insufficient: " + value + " million, try again.");
                    continue;
                }
            }catch(NumberFormatException e)
            {
                u.displayMessage("Invalid number format, try again.");
                continue;
            }
        }
    }
}
