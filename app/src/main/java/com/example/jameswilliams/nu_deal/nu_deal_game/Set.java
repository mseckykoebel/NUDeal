package com.example.jameswilliams.nu_deal.nu_deal_game;

import java.util.ArrayList;

public class Set {

    //This class is intended to keep track of what cards are in which property including
    //houses, hotels, wildcards, etc

    private ArrayList<Card> cards;
    private boolean fullSet;
    private String color;

    public Set() {
        cards = new ArrayList<Card>();
        fullSet = false;
    }

    //Removes the card from the set
    public void removeFromSet(Card c) {
        cards.remove(c);
        checkFullSet();
    }

    //Adds the card to the set
    //Returns true on success, false if set was full

    public boolean addToSet(PropertyCard c) {
        //If the set is empty
        if(cards.size() == 0 && c.isProperty())
        {
            //Set the color
            color = c.getSelectedColor();
            //JUst add it to the set
            cards.add(c);
            checkFullSet();
            return true;
        }
        //If the set is full
        if(fullSet){
            //and we're trying to add a property
            if(c.isProperty()){
                return false;
            }
            //If we already have a house and we're trying to add a house
            if(c.isHouse() && hasHouse()){
                return false;
            }
            //If we're trying to add a hotel and we don't have a house
            if(c.isHotel() && !hasHouse()){
                return false;
            }
            //If we're trying to add a hotel and we have a hotel
            if(c.isHotel() && hasHotel()){
                return false;
            }
            //Otherwise add the card
            cards.add(c);
            return true;
        }
        //Otherwise check if the card is the right color
        if(c.getSelectedColor() != color){
            return false;
        }

        //Its the right color, so add it to the set
        cards.add(c);
        checkFullSet();
        return true;

    }
    //Checks if the set has a house
    public boolean hasHouse() {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getName() == "House") {
                return true;
            }
        }
        return false;
    }

    //Checks if the set has a hotel
    public boolean hasHotel() {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getName() == "Hotel") {
                return true;
            }
        }
        return false;
    }

    //Returns the size of the set
    public int getSize(){return cards.size();}

    public void checkFullSet(){
        //TODO
    }
}
