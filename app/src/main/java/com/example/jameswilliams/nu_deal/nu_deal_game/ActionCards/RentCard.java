package com.example.jameswilliams.nu_deal.nu_deal_game.ActionCards;

import com.example.jameswilliams.nu_deal.nu_deal_game.Card;
import com.example.jameswilliams.nu_deal.nu_deal_game.CardResponse;
import com.example.jameswilliams.nu_deal.nu_deal_game.GameState;
import com.example.jameswilliams.nu_deal.nu_deal_game.Player;
import com.example.jameswilliams.nu_deal.nu_deal_game.UserInterface;
import com.example.jameswilliams.nu_deal.nu_deal_game.Utils;

import java.util.ArrayList;

public class RentCard extends Card
{
    private String color1;
    private String color2;

    // object constructor
    public RentCard(String c1, String c2)
    {
        color1 = c1;
        color2 = c2;
        this.value = 1;
        this.name = "RendCard";
        this.bankable = true;
    }


    @Override
    public CardResponse playCard(GameState g, UserInterface u, Player p) {

        //Check if the player has either of the colors
        ArrayList<String> colors = p.getPropertyColors();
        String selectedColor;
        //If the player has both
        if(Utils.containsString(colors, color1) && Utils.containsString(colors, color2)){
            //Prompt color selection
            ArrayList<String> selection = new ArrayList<>();
            selection.add(color1);
            selection.add(color2);
            selectedColor = u.promptColorSelection(p,selection);
        }

        //If the player has just 1
        else if(Utils.containsString(colors, color1) || Utils.containsString(colors, color2)){
            //Figure out which color the player has
            selectedColor = Utils.containsString(colors, color1) ? color1 : color2;
        }

        //if the player has neither
        else{
            //Can't play the card
            return new CardResponse(false, "Player has neither " + color1 + " nor " + color2 + " properties.");
        }

        //Calculate the rent on the selected color
        int rentValue = p.calculateRent(selectedColor);

        //Create a list of cards to be recieved by the birthday woman/man
        ArrayList<Card> cards = new ArrayList<Card>();

        //Get the list of players who need to pay
        ArrayList<Player> players = g.getPlayersExcept(p);

        //Loop through the players
        for(int i = 0; i < players.size(); i++)
        {
            cards.addAll(players.get(i).chargeMoney(rentValue, u));
        }

        //Give the recieved cards to the player
        p.giveCards(cards);

        //Remove this card from the players hand
        this.removeSelfFromPlayerHand(p);
        g.addToDiscardPile(this);
        //Return success
        return new CardResponse(true, "Card played successfully");
    }
}
