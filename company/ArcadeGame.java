package com.company;

//Here is the Base Class for the Arcade, this is used to help construct the other types of games that are used to
//make the Arcade. We have some key fields and a constructor each been extended into other classes.
//There is accessors, a toString and the abstract method calculatePrice which is used later on.
public abstract class ArcadeGame {

    protected String GameName;
    protected String GameID;
    protected int GamePrice;

    //constructor

    public ArcadeGame(String GameID, String GameName, int GamePrice) throws InvalidGameIDException {

        this.GameName = GameName;
        this.GameID = GameID;
        this.GamePrice = GamePrice;

        if (GameID.length() == 10) {
            System.out.println("I'm a valid id ");
        } else {
            System.out.println("test error");
            throw new InvalidGameIDException(GameID + " Game not available");
        }
    }

    public String getName() {
        return this.GameName;
    }

    public String getGameID() {
        return this.GameID;
    }

    public int getGamePrice() {
        return this.GamePrice;
    }


    public String toString() {
        return "Game Name: " + this.GameName + ", Serial Number: " + this.GameID +
                ", Game Price: " + this.GamePrice + " Pence";
    }


    public abstract int calculatePrice(boolean peak);
}
