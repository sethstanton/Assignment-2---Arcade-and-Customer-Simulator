package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
//Here is a Simulation of the Arcade in motion, here we can be seen reading in the files that give life to the
//arcade. Here we should have the four methods made previously in the arcade class.


public class Simulation {


    File gamesFile = new File("games.txt");
    File customerFile = new File("customers.txt");
    File transactionFile = new File("transactions.txt");

    private ArrayList<ArcadeGame> arcadegame;


    public  Arcade initialiseArcade(String ArcadeName, File gamesFile, File customerFile) {
        Arcade NorwichArcade = new Arcade(ArcadeName);

        //Made an instance of the game classes here, so I could use it to add games to the arcade
        VirtualRealityGame testVirt = null;
        CabinetGame testCab = null;
        ActiveGame testAct = null;
        Customer testCust = null;
        try { //this might cause a break out of the loop
            String one;
            BufferedReader games = new BufferedReader(new FileReader(gamesFile));
            while ((one = games.readLine()) != null) {
                String[] Games = one.split("@");

                if (Games[0].startsWith("V")) {   // for Virtual reality games
                    testVirt = new VirtualRealityGame(Games[0].trim(), Games[1].trim(), Integer.parseInt(Games[3].trim())
                            , Integer.parseInt(Games[4].trim()), Games[5].trim());
                    NorwichArcade.addGame(testVirt);
                    testVirt = null;
                } else if (Games[0].startsWith("C")) {     //for Cabinet Games
                    testCab = new CabinetGame(Games[0].trim(), Games[1].trim(), Integer.parseInt(Games[3].trim())
                            , Boolean.parseBoolean(Games[4].trim()));
                    NorwichArcade.addGame(testCab);
                    testCab = null;

                } else if (Games[0].startsWith("A")) {     //for Active Games
                    testAct = new ActiveGame(Games[0].trim(), Games[1].trim(), Integer.parseInt(Games[3].trim())
                            , Integer.parseInt(Games[4].trim()));
                    NorwichArcade.addGame(testAct);
                    testAct = null;
                }
                for (int i = 0; i < Games.length; i++) ;

            }
        } catch (IOException | InvalidGameIDException e) {
            e.printStackTrace();
        }
        try {
            String two;
            BufferedReader customers = new BufferedReader(new FileReader(customerFile));
            while ((two = customers.readLine()) != null) {
                String[] Customers = two.split("#");

                if (Character.isDigit(two.charAt(0)) || Character.isAlphabetic(two.charAt(0))) {

                    if (Customers.length == 4){
                        testCust = new Customer(Customers[0].trim(), Customers[1].trim(), Integer.parseInt(Customers[2].trim()),
                                Integer.parseInt(Customers[3].trim()));
                        NorwichArcade.addCustomer(testCust);
                        testCust = null;
                    }
                    else{
                    testCust = new Customer(Customers[0].trim(), Customers[1].trim(), Integer.parseInt(Customers[2].trim()),
                            Integer.parseInt(Customers[3].trim()), Customers[4].trim());
                    NorwichArcade.addCustomer(testCust);
                    testCust = null;}
                }

                for (int i = 0; i < Customers.length; i++) ;
                //    System.out.println(Customers[i]);
            }
        } catch (IOException | InvalidCustomerException | InsufficientBalanceException e) {
            e.printStackTrace();
        }

        return NorwichArcade;
    }

    public void simulatefun(Arcade arcade, File transactionFile) {
        Arcade NorwichArcade = new Arcade("NorwichArcade");
        initialiseArcade("Norwich Arcade", gamesFile, customerFile);
        Customer NewCustomer = null;
        ArcadeGame newArcadeGame = null;

        try {
            String three;
            BufferedReader transactions = new BufferedReader(new FileReader(transactionFile));
            while ((three = transactions.readLine()) != null) {
                String[] Transactions = three.split(",");

                if (Transactions[0].startsWith("PLAY")) {
                    NorwichArcade.CollectionOfGames.get(Transactions[2].trim());
                    NorwichArcade.CollectionOfCustomers.get(Transactions[1].trim());

                    NewCustomer.chargeAccount(newArcadeGame, Boolean.parseBoolean(Transactions[3].trim()));
                    System.out.println(Transactions);
                    NewCustomer = null;


                }

                if (Transactions[0].startsWith("ADD_FUNDS")) {

                    NorwichArcade.CollectionOfCustomers.get(Transactions[1].trim());
                    NewCustomer.addFunds(Integer.parseInt(Transactions[2].trim()));
                    System.out.println(Transactions);
                    NewCustomer = null;
                }

                if (Transactions[0].startsWith("NEW_CUSTOMER")) {
                    if (Transactions.length == 6) {
                        NewCustomer = new Customer(Transactions[1].trim(), Transactions[2].trim(),
                                Integer.parseInt(Transactions[4].trim()), Integer.parseInt(Transactions[5].trim()),
                                Transactions[3].trim());
                        NorwichArcade.addCustomer(NewCustomer);
                        System.out.println(Transactions);
                        NewCustomer = null;
                    } else {
                        NewCustomer = new Customer(Transactions[1].trim(), Transactions[2].trim(),
                                Integer.parseInt(Transactions[3].trim()), Integer.parseInt(Transactions[4].trim()));
                        NorwichArcade.addCustomer(NewCustomer);
                        System.out.println();
                        NewCustomer = null;
                    }
                }

                for (int i = 0; i < Transactions.length; i++) ;

            }
        } catch (IOException | InvalidCustomerException | InsufficientBalanceException | AgeLimitException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws InvalidGameIDException, InvalidCustomerException,
            InsufficientBalanceException, AgeLimitException, IOException {
        File gamesFile = new File("games.txt");
        File customerFile = new File("customers.txt");
        File transactionFile = new File("transactions.txt");
        Simulation ArcadeSim = new Simulation();

        Arcade A = ArcadeSim.initialiseArcade("NorwichArcade ",gamesFile,customerFile);
        ArcadeSim.simulatefun(A,transactionFile);






    }

}
