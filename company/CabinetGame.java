package com.company;
//Here is the CabinetGame class that helps define what a cabinet game is in the arcade.
//This has a new field which helps decide if a prize is given or not and the constructor is extended from ArcadeGame.
//The calculatePrice method is defined to help fit the needs of the arcade as described in the brief.

public class CabinetGame extends ArcadeGame {
    private boolean TicketPrize;


    public CabinetGame(String GameID, String GameName, int GamePrice, boolean TicketPrize) throws InvalidGameIDException {
        super(GameID, GameName, GamePrice);
        this.TicketPrize = TicketPrize;
    }


    public boolean getTicketPrize() {
        return this.TicketPrize;
    }


    @Override
    public int calculatePrice(boolean peak) {
        int currentGamePrice = this.GamePrice;

        // if peak times
        if (peak) {
            return this.GamePrice;

        } else {
            //Does give rewards
            if (TicketPrize) {
                currentGamePrice = (80 * currentGamePrice) / 100;
                System.out.println("test1");
                return currentGamePrice;

            } else {
                //Does not give rewards
                currentGamePrice = (50 * currentGamePrice) / 100;
                System.out.println("test2");
                return currentGamePrice;

            }
        }


    }

    //test harness below
    public String toString() {
        return super.toString() + " Prize Given " + TicketPrize;
    }

    public static void main(String[] args) throws InvalidGameIDException {
        //This shows a valid id and what will print out a full cabinent game.
        CabinetGame test = new CabinetGame("1234567890", "Apple", 300, true);

        System.out.println(test);


        // these test the calculate price function to show how the different scenarios give different results
        CabinetGame paul = new CabinetGame("1234567890", "paul", 1000, true);
        System.out.println(paul.calculatePrice(false));
        System.out.println(paul.calculatePrice(true));
        CabinetGame paula = new CabinetGame("1234567891", "paula", 1000, false);
        System.out.println(paula.calculatePrice(true));
        System.out.println(paula.calculatePrice(false));
    }


}
