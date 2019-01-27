package com.example.jameswilliams.nu_deal.GameFiles;

//Main game class for NUDeal
//Handles game state as well as the hands

//Main game object
public class NUDeal {

    GameState g;
    UserInterface u;

    public NUDeal(UserInterface us) {
        g = new GameState();
        u = us;
    }

    public NUDeal(GameState gs, UserInterface us){
        g = gs;
        u = us;
    }

    public void runGame() {
        boolean playAgain = true;

        //Display the welcome message
        u.displayMessage("Welcome to NU Deal!");

        int numPlayers = u.getInt(null,"How many players will there be?");

        //For each player, get their name
        for (int i = 0; i < numPlayers; i++) {
            String playerName = u.getLine(null, "What will the name of player " + i + 1 + "be?");
            g.addPlayer(new Player(playerName));
        }

        //Tell them we're starting the game
        u.displayMessage("Starting game...");
        while (playAgain) {
            g.initGame();

            //Go to main game loop
            playAgain = mainGameLoop();
            if (playAgain) {
                u.displayMessage("Restarting game");
            } else {
                u.displayMessage("Exiting game");
            }
        }
    }

    public boolean mainGameLoop() {

        while (executeTurn());
        //If we're here, someone has won the game
        u.displayMessage(g.winDetected().getName() + " has won the game!");
        String choice = u.getLine(null, "Play again? yes or no");

        if(choice == "yes")
        {
            return true;
        }
        return false;
    }

    //Returns true if a win was detected
    public boolean executeTurn(){
        //Get the current player
        Player currentPlayer = g.whoseTurn();

        //Say who'se turn it is
        u.displayMessage(currentPlayer.getName()+"'s turn");

        //Deal the player two cards
        g.dealPlayer(currentPlayer, 2);

        //Play up to 3 cards
        int plays = 0;
        while (plays < 3) {
            //Display cards to player
            u.displayMessage("Please select a card to play or say 'end turn' ");
            Card choice = u.promptCardSelection(currentPlayer, currentPlayer.getHand()).get(0);

            //If they want to end their turn
            if (choice == null) {
                break;
            } else {
                //Play the card
                CardResponse rep = choice.playCard(g, u, currentPlayer);
                if(rep.success){
                    plays++;
                }
                else{
                    u.displayMessageToPlayer(currentPlayer, "Failed to play card: " + rep.message);
                }
            }
        }

        //If the player needs to discard cards
        if(currentPlayer.getHandSize() > 7)
        {
            //TODO
        }


        //Check to see if anyone won on that turn
        if (g.winDetected() != null) {
            return true;
        } else {
            //Advance the turn
            g.nextTurn();
            return false;
        }

    }

}
