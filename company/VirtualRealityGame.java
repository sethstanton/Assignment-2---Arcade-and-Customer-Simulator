package com.company;

//Here is the VirtualRealityGame class that helps define what an virtual reality game is in the arcade.
//This is a subclass of ActiveGame which ages on to the age verification of its parent class with a
//Controller type field that allows for different kinds of VR games.
//The calculatePrice method is defined to help fit the needs of the arcade as described in the brief.
public class VirtualRealityGame extends ActiveGame {

    private String ControllerType;

    public enum ControllerType {headsetOnly, headsetAndController, fullBodyTracking}


    public VirtualRealityGame(String GameID, String GameName, int GamePrice, int AgeReq,
                              String ControllerType) throws InvalidGameIDException {
        super(GameID, GameName, GamePrice, AgeReq);

        this.ControllerType = String.valueOf(ControllerType);

    }

    public String getControllerType() {
        return this.ControllerType;
    }

    public int calculatePrice(boolean peak) {
        int currentGamePrice = this.GamePrice;


        if (peak) {
            return this.GamePrice;
        } else {
            //headset only
            if (ControllerType.equals("headsetOnly")) {
                currentGamePrice = (90 * currentGamePrice) / 100;
                System.out.println("trial2");
                return currentGamePrice;

            } else if (ControllerType.equals("headsetAndController")) {
                //Does give rewards
                currentGamePrice = (95 * currentGamePrice) / 100;
                System.out.println("trial3");
                return currentGamePrice;
            }
            //Fullbody tracking
            else {
                return GamePrice;
            }

        }

    }

    public String toString() {
        return super.toString() + " Controller Requirements " + ControllerType;
    }

    public static void main(String[] args) throws InvalidGameIDException {
        //This shows a valid id and what will print out a full activegame.
        VirtualRealityGame delta = new VirtualRealityGame("1234567893", "delta",
                1000, 12, "headsetOnly");


        System.out.println(delta);
        //this tests the calculate price function to show how the different scenarios give different results
        System.out.println(delta.calculatePrice(true));
        System.out.println(delta.calculatePrice(false));

        VirtualRealityGame delta1 = new VirtualRealityGame("1234567893", "delta1", 1000, 12, "headsetAndController");
        System.out.println(delta1.calculatePrice(true));
        System.out.println(delta1.calculatePrice(false));

        VirtualRealityGame delta2 = new VirtualRealityGame("1234567893", "delta2", 1000, 12, "fullBodyTracking");
        System.out.println(delta2.calculatePrice(true));
        System.out.println(delta2.calculatePrice(false));

    }
}
