package com.example.jameswilliams.nu_deal.nu_deal_game.ActionCards;

import com.example.jameswilliams.nu_deal.nu_deal_game.*;

import java.util.ArrayList;


public class Birthday extends Card {
    public Birthday() {
        this.name = "Birthday";
        this.bankable = true;
        this.value = 2;
    }

    public CardResponse playCard(GameState g, UserInterface u, int playernum) {
        CardResponse response = new CardResponse(true, "success");

        //Make each player pay at least 2 mil to player who played the birthday
        for (int i = 0; i < g.getPlayers().size(); i++) {
            //If we're not on the player who played the birthday
            if (i != playernum) {
                //Get the player object
                Player currentPlayer = g.getPlayers().get(i);
                //If the player has nothing on the board or only one card
                if (currentPlayer.getBankSize() + currentPlayer.getBoardSize() == 1) {
                    //If it's on the board
                    if (currentPlayer.getBankSize() == 1) {
                        //Get the card
                        Card payment = currentPlayer.removeFromBank(0);
                        u.displayMessage("You have only one card on the board: " + payment.getName() + ", you must pay with it.");
                        //Add it to the birthday player's bank
                        g.getPlayers().get(playernum).addToBank(payment);

                    } else {
                        //Must be on the board
                        //Get the card
                        Card payment = currentPlayer.removeFromBoard(0);
                        u.displayMessage("You have only one card on the board: " + payment.getName() + ", you must pay with it.");
                        //Add it to the birthday player's properties
                        g.getPlayers().get(playernum).addToBoard(payment);

                    }
                } else if (currentPlayer.getBankSize() + currentPlayer.getBoardSize() == 0) {
                    u.displayMessage(currentPlayer.getName() + " has no cards, so they don't have to pay");
                    continue;
                } else {
                    //Card selection loop
                    while (true) {
                        //Tell them they need to pay
                        u.displayMessage("You must pay " + g.getPlayers().get(playernum).getName() + " 2 million\nHow would you like to pay?");
                        u.displayMessage("To select multiple cards, say: 1 2 3....");
                        //List the cards they can pay with
                        String input = u.getInput(currentPlayer.getPayableCardString());

                        //Try converting the input to a number
                        try {
                            int cards[] = u.stringToIntArray(input);
                            //Get the cards
                            ArrayList<Card> boardCards = new ArrayList<Card>();
                            ArrayList<Card> bankCards = new ArrayList<Card>();

                            for (int j = 0; j < cards.length; j++) {
                                //If the card is on the board
                                if (cards[j] < currentPlayer.getBoardSize()) {
                                    //Add that card to the board cards list
                                    boardCards.add(currentPlayer.getFromBoard(cards[j]));
                                } else {
                                    //Add that card to the bank cards list
                                    bankCards.add(currentPlayer.getFromBank(cards[j] - currentPlayer.getBoardSize()));
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
                                //Take cards from currentPlayer and add then to birthday player
                                for (int j = 0; j < cards.length; j++){
                                    //If the card is on the board
                                    if (cards[j] < currentPlayer.getBoardSize()) {
                                        //Add that card to the board cards list
                                        g.getPlayers().get(playernum).addToBoard(currentPlayer.getFromBoard(cards[j]));
                                    } else {
                                        //Add that card to the bank cards list
                                        g.getPlayers().get(playernum).addToBank(currentPlayer.getFromBoard(cards[j] - currentPlayer.getBoardSize()));
                                    }
                                }
                            } else {
                                u.displayMessage("Value of cards is insufficient: " + value + " million, try again.");
                                continue;
                            }


                        } catch (NumberFormatException e) {
                            u.displayMessage("Invalid number format, try again");
                        }

                    }
                }
            }
        }

        //Remove the birthday card from the player's hand

        return response;

    }
}
