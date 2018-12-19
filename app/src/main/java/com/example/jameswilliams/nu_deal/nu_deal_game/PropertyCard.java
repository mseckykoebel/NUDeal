package com.example.jameswilliams.nu_deal.nu_deal_game;

public class PropertyCard extends Card {
    private String color1;
    private String color2;
    private boolean isPartOfFullSet;

    public PropertyCard(String n, String c1, int v) {
        this.name = n;
        this.color1 = c1;
        this.color2 = "";
        this.value = v;
        this.bankable = false;
        this.banked = true;
        this.banked = false;
        this.isPartOfFullSet = false;
    }

    // Property WildCards taking on two color values, and can be banked
    public PropertyCard(String n, String c1, String c2, int v) {
        this.name = n;
        this.color1 = c1;
        this.color2 = c2;
        this.value = v;
        this.bankable = true;
        this.banked = true;
        this.banked = false;
        this.isPartOfFullSet = false;
    }

    public String getColor1() {
        return color1;
    }

    public String getColor2() {
        return color2;
    }

    // Houses and Hotels that Can be banked, and qualify as property cards
    // More logic dealing with these will be done later
    public PropertyCard(String n, int v) {
        this.name = n;
        this.value = v;
        this.bankable = true;
        this.color1 = "";
        this.color2 = "";
        this.banked = false;
        this.isPartOfFullSet = false;
    }

    public CardResponse playCard(GameState g, UserInterface u, int playernum) {
        CardResponse response = new CardResponse(true, "success");

        //Remove myself from the player's hand
        this.removeSelfFromPlayerHand(g, playernum);

        //Just add this card to the player's board
        g.getPlayers().get(playernum).addToBoard(this);

        return response;
    }


    //Returns the rent value for the given number of a certain property
    public static int getRentValue(String color, int number) {
        int rank = 0;
        switch (color) {
            case "Blue":
                rank = 1;//3 and 8
                break;
            case "Green":
                rank = 2;//2 4 and 7
                break;
            case "Red":
                rank = 3;//2 3 and 6
                break;
            case "Yellow":
                rank = 4;//2 4 and 6
                break;
            case "SkyBlue":
                rank = 5;//1 2 and 3
                break;
            case "SaddleBrown":
                rank = 5;//1 and 2
                break;
            case "DarkOrchid"://1 2 and 4
                rank = 6;
                break;
            case "Orange":
                rank = 7;
                break;
            case "Railroad":
                rank = 5;//1 2 3 and 4
                break;
            case "Utility":
                rank = 5;//1 and 2
                break;
            default:
                return 0;

        }

        switch (rank) {
            case 1:
                if (number == 1) {
                    return 3;
                }
                return 8;
            case 2:
                if (number == 1) {
                    return 2;
                } else if (number == 2) {
                    return 4;
                } else {
                    return 7;
                }
            case 3:
                if(number == 1)
                {
                    return 2;
                }else if(number == 2){
                    return 3;
                }else{
                    return 6;
                }
            case 4:
                return number >= 3 ? 6 : number*3;
            case 5:
                if(color == "Railroad")
                {
                    return number >= 4 ? 4 : number;
                }else if(color == "Utility" || color == "SaddleBrown"){
                    return number >= 2 ? 2 : 1;
                }else{
                    return number >= 3 ? 3 : number;
                }
            case 6:
                break;
            case 7:
                break;
            default:
                return 0;

        }

    }
}
