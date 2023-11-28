package com.company;
//Here is the Active Game class that helps define what an active game is in the arcade.
//This has a field that simulates age verification when trying to play certain games.
//The calculatePrice method is defined to help fit the needs of the arcade as described in the brief.

public class ActiveGame extends ArcadeGame {

    protected int AgeReq;


    public ActiveGame(String GameID, String GameName, int GamePrice, int AgeReq) throws InvalidGameIDException {
        super(GameID, GameName, GamePrice);
        this.AgeReq = AgeReq;


    }

    public int getAgeReq() {
        return this.AgeReq;
    }

    @Override
    public int calculatePrice(boolean peak) {
        int currentGamePrice = this.GamePrice;
        if (peak) {
            return GamePrice;
        } else {
            currentGamePrice = (80 * currentGamePrice) / 100;
            return currentGamePrice;
        }

    }

    public String toString() {
        return super.toString() + " Age Rating " + AgeReq;
    }


    //Below is the test harness for this class
    public static void main(String[] args) throws InvalidGameIDException {
        //This shows a valid id and what will print out a full activegame.
        ActiveGame charlie = new ActiveGame("1234567892", "charlie", 1000, 12);
        System.out.println(charlie);
        //this tests the calculate price function to show how the different scenarios give different results
        System.out.println(charlie.calculatePrice(true));
        System.out.println(charlie.calculatePrice(false));


    }

}
