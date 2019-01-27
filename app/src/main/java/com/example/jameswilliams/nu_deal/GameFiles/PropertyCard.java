package com.example.jameswilliams.nu_deal.GameFiles;

public class PropertyCard extends Card {
    private String color1;
    private String color2;
    private String selectedColor;//Only for wild cards
    private boolean isTwoColor;
    private boolean isWild;

    //Normal property card
    public PropertyCard(String n, String c1, int v) {
        this.name = n;
        this.color1 = c1;
        this.selectedColor = c1;
        this.color2 = "";
        this.value = v;
        this.bankable = false;
        this.banked = true;
        this.banked = false;
        isWild = (n == "WildCard");
        isTwoColor = false;

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
        this.selectedColor = c1;//Default to c1, prompt when played
        this.isTwoColor = true;
        this.isWild = false;
    }

    public String getColor1() {
        return color1;
    }

    public String getColor2() {
        return color2;
    }

    public boolean isTwoColor(){return isTwoColor;}
    public boolean isWild(){return isWild;}

    //House and hotel constructor
    // Houses and Hotels that Can be banked, and qualify as property cards
    // More logic dealing with these will be done later
    public PropertyCard(String n, int v) {
        this.name = n;
        this.value = v;
        this.bankable = true;
        this.color1 = "";
        this.color2 = "";
        this.banked = false;
        this.selectedColor = "";
    }

    public String getSelectedColor(){
        return selectedColor;
    }

    //True if not hotel or house
    public boolean isProperty()
    {
        return name != "House" && name != "Hotel";
    }

    public boolean isHouse(){
        return name == "House";
    }

    public boolean isHotel(){
        return name == "Hotel";
    }

    public CardResponse playCard(GameState g, UserInterface u, Player p) {
        CardResponse response = new CardResponse(true, "success");

        //Remove myself from the player's hand
        this.removeSelfFromPlayerHand(p);


        //If the card is a wild card
        if(this.color1 !=  "" && this.color2 != ""){
            //If it's a two color


            //If it's a wild card

            //ToDo
        }

        //Just add this card to the player's board
        p.addToBoard(this);

        return response;
    }


    //Returns the rent value for the given number of a certain property
    public static int getRentValue(String color, int number) {
        if(number == 0 || color == "") {
            return 0;
        }
        int rank;
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
            case "Orange"://1 3 and 5
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
                return number >= 3 ? 6 : number*2;
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
                if(number <= 2){
                    return number;
                }else{
                    return 4;
                }
            case 7:
                if(number == 1){
                    return 1;
                }else if(number == 2){
                    return 3;
                }else {
                    return 5;
                }
            default:
                return 0;

        }

    }
}
