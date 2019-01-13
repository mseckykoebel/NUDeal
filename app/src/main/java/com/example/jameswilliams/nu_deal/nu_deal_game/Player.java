package com.example.jameswilliams.nu_deal.nu_deal_game;

import java.util.ArrayList;

public class Player {

    private ArrayList<Card> hand;
    private ArrayList<Card> bank;
    private String name;
    //Effectively acts as the player's board
    private ArrayList<Set> sets;

    public Player(String n) {
        name = n;
        hand = new ArrayList<Card>();
        bank = new ArrayList<Card>();
        sets = new ArrayList<Set>();

    }

    /////////////////
    //Hand Routines//
    /////////////////

    //Adds a card to the player's hand
    public void addToHand(Card c) {
        hand.add(c);
    }

    //Removes a card from the player's hand at index n and returns it
    public Card removeFromHand(int n) {
        return hand.remove(n);
    }

    //Removes a card from the player's hand by looking up the Card object
    public boolean removeFromHand(Card c) {
        return hand.remove(c);
    }

    //Gets a card from the player's hand at index n
    public Card getFromHand(int n) {
        return hand.get(n);
    }

    //Returns the entire hand of the player
    public ArrayList<Card> getHand() {
        return hand;
    }

    //Gets the size of the player's hand
    public int getHandSize() {
        return hand.size();
    }

    /////////////////
    //Bank Routines//
    /////////////////

    //Adds a card to the player's bank
    public void addToBank(Card c) {
        bank.add(c);
    }

    //Removes and returns a card from the player's bank at index n
    public Card removeFromBank(int n) {
        return bank.remove(n);
    }

    //Removes a card from the player's bank by looking up the Card object
    public void removeFromBank(Card c) {
        bank.remove(c);
    }

    //Gets a card from the player's bank at index n
    public Card getFromBank(int n) {
        return bank.get(n);
    }

    //Gets the size of the player's bank
    public int getBankSize() {
        return bank.size();
    }

    //////////////////
    //Board Routines//
    //////////////////

    //Adds a card to the player's board
    public boolean addToBoard(PropertyCard c) {
        //If we already have an set with this color
        for (int i = 0; i < sets.size(); i++) {
            if(sets.get(i).getColor() == c.getSelectedColor())
            {
                //try adding the card
                return sets.get(i).add(c);
            }
        }
        //Otherwise we need to make a new set
        Set s = new Set();
        s.add(c);
        sets.add(s);
        return true;
    }

    //Removes a card from the player's board by looking up the object
    public boolean removeFromBoard(PropertyCard c) {
        //Find the set with that card
        Set t = getSet(c.getSelectedColor());
        //If we had no matching sets
        if(t == null){
            return false;
        }
        //try removing the card
        return t.remove(c);
    }


    //Gets the player's board size
    public int getBoardSize() {
        return getCardsFromSets().size();
    }

    //Removes all cards from the player
    public void reset() {
        hand.clear();
        bank.clear();
        sets.clear();
    }

    //Gets the name of the player
    public String getName() {
        return name;
    }

    //Gets the list of cards the player can use to pay with
    public ArrayList<Card> getPayableCardList() {
        ArrayList<Card> c = new ArrayList<Card>();
        c.addAll(bank);
        c.addAll(getCardsFromSets());
        return c;
    }

    //This takes the cards out of the player's board and bank to pay the ammount
    public ArrayList<Card> chargeMoney(int ammount, UserInterface u) {
        ArrayList<Card> money = new ArrayList<Card>();

        //Check if they have 0 cards
        if (bank.size() + sets.size() == 0) {
            u.displayMessage("You have no money so you don't have to pay!");
            return money;
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
                    removeFromBoard((PropertyCard) boardCards.get(i));
                }

                //Return them
                money.addAll(boardCards);
                money.addAll(bankCards);
                return money;

            //If they used everything they had
            } else if(boardCards.size() + bankCards.size() == getPayableCardList().size()){
                u.displayMessageToPlayer(this, "Payment successful");
                //Take cards from this player
                for (int i = 0; i < bankCards.size(); i++) {
                    removeFromBank(bankCards.get(i));
                }
                for (int i = 0; i < boardCards.size(); i++) {
                    removeFromBoard((PropertyCard) boardCards.get(i));
                }

                //Return them
                money.addAll(boardCards);
                money.addAll(bankCards);
                return money;
            }
            //Insufficient funds
            else {
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
                this.addToBoard((PropertyCard) cards.get(i));
            }
        }
    }

    //Calculates the rent for a given color
    public int calculateRent(String color) {
        int rent = 0;

        //Find the set of that color
        Set s = getSet(color);
        //Add the base price in for the color
        rent += PropertyCard.getRentValue(color, s.getNumPropertyCards());
        //If we have a house
        if(s.hasHouse()){
            rent += 3;
        }
        if(s.hasHotel()){
            rent += 4;
        }
        return rent;
    }

    private Set getSet(String c){
        for(int i = 0; i < sets.size(); i++){
            if(sets.get(i).getColor() == c){
                return sets.get(i);
            }
        }
        return null;
    }

    //Returns all property cards from sets which ARE NOT FULL
    public ArrayList<Card> getTradablePropertiesList(){
        //Go through all of the sets
        ArrayList<Card> list = new ArrayList<>();
        for(int i = 0; i < sets.size(); i++){
            if(!sets.get(i).isFullSet()) {
                list.addAll(sets.get(i).getPropertyCards());
            }
        }
        return list;
    }

    //Returns all of the cards in our sets
    public ArrayList<Card> getCardsFromSets(){
        ArrayList<Card> list = new ArrayList<>();
        for(int i = 0; i < sets.size(); i++){
            list.addAll(sets.get(i).getCards());
        }
        return list;
    }

    //Implements a just say no battle between this player and p
    public boolean willSayNo(GameState g, UserInterface u, Player p){
        //Todo
        return false;
    }

    //Returns a list of the property colors on the board
    public ArrayList<String> getPropertyColors(){
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < sets.size(); i++){
            list.add(sets.get(i).getColor());
        }
        return list;
    }

    public ArrayList<String> getFullSetColors(){
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < sets.size(); i++){
            if(sets.get(i).isFullSet()) {
                list.add(sets.get(i).getColor());
            }
        }
        return list;
    }

    public Set takeFullSet(String color){
        //Find the set
        for(int i = 0; i < sets.size(); i++){
            if(sets.get(i).isFullSet() && sets.get(i).getColor() == color){
                return sets.remove(i);
            }
        }
        //If we couldn't find it
        return null;
    }

    public void giveSet(Set s){
        sets.add(s);
    }
}
