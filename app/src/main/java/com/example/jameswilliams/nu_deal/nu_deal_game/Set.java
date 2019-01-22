package com.example.jameswilliams.nu_deal.nu_deal_game;

import android.util.Property;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Set {

    //This class is intended to keep track of what cards are in which property including
    //houses, hotels, wildcards, etc

    private ArrayList<PropertyCard> cards;
    private boolean fullSet;
    private String color;

    public Set() {
        cards = new ArrayList<PropertyCard>();
        fullSet = false;
    }


    public boolean isFullSet(){return fullSet;}

    //Adds the card to the set
    //Returns true on success, false if set was full

    public boolean add(PropertyCard c) {

        //If the set is empty
        if (cards.size() == 0 && c.isProperty()) {
            //Set the color
            color = c.getSelectedColor();
            //JUst add it to the set
            cards.add(c);
            checkFullSet();
            return true;
        }
        //If the set is full
        if (fullSet) {
            //and we're trying to add a property
            if (c.isProperty()) {
                return false;
            }
            //If we already have a house and we're trying to add a house
            if (c.isHouse() && hasHouse()) {
                return false;
            }
            //If we're trying to add a hotel and we don't have a house
            if (c.isHotel() && !hasHouse()) {
                return false;
            }
            //If we're trying to add a hotel and we have a hotel
            if (c.isHotel() && hasHotel()) {
                return false;
            }
            //Otherwise add the card
            cards.add(c);
            return true;
        }
        //Otherwise check if the card is the right color
        if (c.getSelectedColor() != color) {
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
    public int getSize() {
        return cards.size();
    }


    private void checkFullSet() {
        switch (color) {
            case "Green":
            case "Yellow":
            case "SkyBlue":
            case "DarkOrchid":
            case "Orange":
            case "Red":
                fullSet = getNumPropertyCards() == 3 ? true : false;
                return;
            case "SaddleBrown":
            case "Blue":
            case "Utility":
                fullSet = getNumPropertyCards() == 2 ? true : false;
                return;
            case "Railroad":
                fullSet = getNumPropertyCards() == 4 ? true : false;
                return;
        }
    }

    public int getNumPropertyCards() {
        int count = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).isProperty()) {
                count++;
            }
        }
        return count;
    }

    public ArrayList<PropertyCard> getPropertyCards(){
        ArrayList<PropertyCard> c = new ArrayList<>();
        for(int i = 0; i < cards.size(); i++){
            if(cards.get(i).isProperty()){
                c.add(cards.get(i));
            }
        }
        return c;
    }

    public String getColor() {
        return color;
    }

    public boolean hasCard(PropertyCard c){
        return cards.contains(c);
    }

    public boolean remove(PropertyCard c){
        if(!hasCard(c)){
            return false;
        }

        //If we're trying to remove a house and have a hotel
        if(c.isHouse() && this.hasHotel()){
            return false;
        }

        //If we're trying to remove property and have a house
        if(!c.isHouse() && this.hasHouse()){
            return false;
        }

        //Otherwise safe to remove
        cards.remove(c);
        checkFullSet();
        return true;
    }

    public ArrayList<PropertyCard> getCards(){return cards;}
}
