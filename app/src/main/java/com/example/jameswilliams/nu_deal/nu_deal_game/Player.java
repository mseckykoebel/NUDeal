package com.example.jameswilliams.nu_deal.nu_deal_game;

import java.util.ArrayList;

public class Player {

    private ArrayList<Card> hand;
    private ArrayList<Card> bank;
    private ArrayList<Card> board;
    private String name;

    public Player(String n) {
        name = n;
        hand = new ArrayList<Card>();
        bank = new ArrayList<Card>();
        board = new ArrayList<Card>();

    }

    /////////////////
    //Hand Routines//
    /////////////////

    //Adds a card to the player's hand
    public void addToHand(Card c) { hand.add(c); }
    //Removes a card from the player's hand at index n and returns it
    public Card removeFromHand(int n) { return hand.remove(n); }
    //Removes a card from the player's hand by looking up the Card object
    public void removeFromHand(Card c) { hand.remove(c); }
    //Gets a card from the player's hand at index n
    public Card getFromHand(int n) { return hand.get(n); }
    //Returns the entire hand of the player
    public ArrayList<Card> getHand() { return hand; }
    //Gets the size of the player's hand
    public int getHandSize() { return hand.size(); }

    /////////////////
    //Bank Routines//
    /////////////////

    //Adds a card to the player's bank
    public void addToBank(Card c) { bank.add(c); }
    //Removes and returns a card from the player's bank at index n
    public Card removeFromBank(int n) { return bank.remove(n); }
    //Removes a card from the player's bank by looking up the Card object
    public void removeFromBank(Card c) { bank.remove(c); }
    //Gets a card from the player's bank at index n
    public Card getFromBank(int n) { return bank.get(n); }
    //Gets the size of the player's bank
    public int getBankSize() { return bank.size(); }

    //////////////////
    //Board Routines//
    //////////////////

    //Adds a card to the player's board
    public void addToBoard(Card c) { board.add(c); }
    //Removes a card from the player's board at index n
    public Card removeFromBoard(int n) { return board.remove(n); }
    //Removes a card from the player's board by looking up the object
    public void removeFromBoard(Card c) { board.remove(c); }
    //Gets a card from the player's board at position n
    public Card getFromBoard(int n) { return board.get(n); }
    //Gets the player's board size
    public int getBoardSize() { return board.size(); }

    //Removes all cards from the player
    public void reset() {
        hand.clear();
        bank.clear();
        board.clear();
    }

    //Gets the name of the player
    public String getName() { return name; }

    //Gets the list of cards the player can use to pay with
    public ArrayList<Card> getPayableCardList() {
        ArrayList<Card> c = new ArrayList<Card>();
        c.addAll(bank);
        c.addAll(board);
        return c;
    }

    //This takes the cards out of the player's board and bank to pay the ammount
    public ArrayList<Card> chargeMoney(int ammount, UserInterface u) {
        ArrayList<Card> money = new ArrayList<Card>();

        //Check if they have 0 cards
        if (bank.size() + board.size() == 0) {
            u.displayMessage("You have no money so you don't have to pay!");
            return money;
        }

        //Check if they have 1 card
        if (bank.size() + board.size() == 1) {
            //If it's in the bank
            if (bank.size() == 1) {
                //Remove it from the player's bank
                money.add(this.removeFromBank(0));
                u.displayMessage("You only have " + money.get(0).getName() + ", so you must pay with it.");
                return money;
            } else {
                //Must be on the board
                money.add(this.removeFromBoard(0));
                u.displayMessage("You only have " + money.get(0).getName() + ", so you must pay with it.");
                return money;
            }


        }

        //Get list of payable cards
        ArrayList<Card> cards = getPayableCardList();

        //Selection loop
        while (true) {
            //List the cards they can pay with
            u.displayMessage("You must pay " + ammount + " million\nHow would you like to pay?");
            u.displayMessage("To select multiple cards, say: 1 2 3....");


            ArrayList<Card> choices = u.promptCardSelection(this, cards);
            //Get the cards
            ArrayList<Card> boardCards = new ArrayList<Card>();
            ArrayList<Card> bankCards = new ArrayList<Card>();

            for (int j = 0; j < choices.size(); j++) {
                //Look up the card in the list
                Card cardChoice = choices.get(j);
                //If it's money
                if (cardChoice.getClass() == MoneyCard.class) {
                    bankCards.add(cardChoice);
                } else {
                    //Must be a board card
                    boardCards.add(cardChoice);
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
                for (int i = 0; i < bankCards.size(); i++) {
                    removeFromBank(bankCards.get(i));
                }
                for (int i = 0; i < boardCards.size(); i++) {
                    removeFromBoard(boardCards.get(i));
                }

                //Return them
                money.addAll(boardCards);
                money.addAll(bankCards);
                return money;
            } else {
                u.displayMessage("Value of cards is insufficient: " + value + " million, try again.");
                continue;
            }
        }
    }

    //Gives this player cards in list, distributes them to bank or board depending on if banked
    public void giveCards(ArrayList<Card> cards) {
        //Add the cards to the player of debt collector
        for (int i = 0; i < cards.size(); i++) {
            //If it's a money card
            if (cards.get(i).isBanked()) {
                //Add it to the player's bank
                this.addToBank(cards.get(i));
            } else {
                //Add it to their board
                this.addToBoard(cards.get(i));
            }
        }
    }

    //Calculates the rent for a given color
    public int calculateRent(String color)
    {
        int rent = 0;
        //TODO

        return rent;
    }
}
