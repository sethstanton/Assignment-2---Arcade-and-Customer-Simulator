package com.company;

import java.util.*;

//Here is the arcade class that simulates what an arcade is and uses previous parts of every other class.
//This class has fields to help create the base for an arcade and pulls other things in to help fill it out.
//The methods to add a game/customer are used to build the arcade, and then we are able to retrieve them with
//the get method for each. Hashmaps were used to store these as when researching I found them to be easier to retrieve
// things from when rated against other methods.We are also able to process the transaction for each customer.
//We then have a few methods that were made to help please the owners of the arcade to most likely
//help them devise ways to improve the arcade.
public class Arcade {


    private String ArcadeName;
    private int ArcadeRevenue;
    private Customer customer;


    public Arcade(String ArcadeName) {
        this.ArcadeName = ArcadeName;
    }

    protected Map<String, Customer> CollectionOfCustomers = new HashMap<String, Customer>();
    protected Map<String, ArcadeGame> CollectionOfGames = new HashMap<String, ArcadeGame>();

    public String getArcadeName() {
        return ArcadeName;
    }

    public int getArcadeRevenue() {
        return ArcadeRevenue;
    }

    @Override
    public String toString() {
        return "ArcadeName " + ArcadeName + ", Arcade Revenue " + ArcadeRevenue +
                ", Collection Of Games " + CollectionOfGames + ", Collection Of Customers " + CollectionOfCustomers;
    }

    public void addCustomer(Customer c) throws InvalidCustomerException, InsufficientBalanceException {
        CollectionOfCustomers.put(c.getAccountID(), new Customer(c.getAccountID(),
                c.getCustomerName(), c.getAccountBalance(),
                c.getAge(), c.getPersonalDiscount()));

    }

    public void addGame(ArcadeGame d) throws InvalidGameIDException {
        if (d instanceof VirtualRealityGame || d instanceof CabinetGame || d instanceof ActiveGame) {
            if (d.getGameID().startsWith("V")) {
                CollectionOfGames.put(d.getGameID(), new VirtualRealityGame(d.getGameID(), d.getName(), d.getGamePrice(),
                        ((VirtualRealityGame) d).AgeReq, ((VirtualRealityGame) d).getControllerType()));

            } else if (d.getGameID().startsWith("C")) {
                CollectionOfGames.put(d.getGameID(), new CabinetGame(d.getGameID(), d.getName(), d.GamePrice,
                        ((CabinetGame) d).getTicketPrize()));
            } else {
                CollectionOfGames.put(d.getGameID(), new ActiveGame(d.getGameID(), d.getName(), d.getGamePrice(),
                        ((ActiveGame) d).getAgeReq()));
            }
        }
    }

    public Customer getCustomer(String AccountID) throws InvalidCustomerException {

        if (CollectionOfCustomers.containsKey(AccountID) == false) {
            throw new InvalidCustomerException(AccountID + " AccountID not available");

        } else {
            System.out.println(CollectionOfCustomers.get(AccountID));
        }
        return CollectionOfCustomers.get(AccountID);
    }

    public ArcadeGame getArcadeGame(String gameID) throws InvalidGameIDException {

        if (CollectionOfGames.containsKey(gameID) == false) {
            throw new InvalidGameIDException(gameID + " Game not available");
        } else
            System.out.println(CollectionOfGames.get(gameID));


        return CollectionOfGames.get(gameID);
    }

    public boolean processTransaction(String customerID, String gameID, boolean peak) throws AgeLimitException, InsufficientBalanceException {

        if (CollectionOfCustomers.containsKey(customerID)) {
            Customer currentCustomer = CollectionOfCustomers.get(customerID);
            int accountBalance = currentCustomer.getAccountBalance();


            if (CollectionOfGames.containsKey(gameID)) {
                ArcadeGame arcadeInformation = CollectionOfGames.get(gameID);


                int arcadePrice = currentCustomer.chargeAccount(arcadeInformation, peak);
                accountBalance = --arcadePrice;
                ArcadeRevenue = ++arcadePrice;

                System.out.println("Newly Calculated Revenue " + ArcadeRevenue);
                System.out.println("Newly Calcualted Account Balance " + accountBalance);
                System.out.println("Transaction Successful - Please Proceed");

                return true;
            }
        } else {
            System.out.println("Transaction Failed - Please Try Again");

        }
        return false;
    }

    // This method stores account balance value, then stores all of a customers values,
    // before getting the value of the entry chosen.
    // It then checks if customers account balance is > then current richest number if so, then this
    // customers account balance is the new richest value. store all of this richest customer details.
    // This is then checked that the "richest customer" isnt a null value avoiding a null pointer exception
    public void findRichestCustomer() {
        int richestCustomer = 0;
        Customer customer = null;

        for (Map.Entry<String, Customer> entry : CollectionOfCustomers.entrySet()) { // a forEach loop
            Customer list = entry.getValue();
            if (list.getAccountBalance() > richestCustomer) {
                richestCustomer = (int) list.getAccountBalance();
                customer = list;
            }
        }
        assert customer != null;
        System.out.println("The wealthiest customer is " + customer.getCustomerName() + " who's details are: " + customer);
    }

    public void getMedianGamePrice() {
        double median = 0;
        List<Integer> gamePrices = new ArrayList<>();

        for (Map.Entry<String, ArcadeGame> entry : CollectionOfGames.entrySet()) {
            int gameprice = entry.getValue().GamePrice;
            gamePrices.add(gameprice);
        }
        Collections.sort(gamePrices);
        if (gamePrices.size() % 2 == 0) {
            median = gamePrices.get((gamePrices.size() - 1) / 2);
        } else {
            median = (gamePrices.get((gamePrices.size() - 1) / 2) + gamePrices.get(gamePrices.size() / 2)) / 2;
        }
        System.out.println("The median price for all arcade games is: " + median + " pence");
    }

    public void countArcadeGames() {
        int[] gamesArray = new int[3];
        for (Map.Entry<String, ArcadeGame> entry : CollectionOfGames.entrySet()) {
            if (entry.getValue().GameID.startsWith("C")) {
                gamesArray[0]++;
            } else if (entry.getValue().GameID.startsWith("A")) {
                gamesArray[1]++;
            } else {
                gamesArray[2]++;
            }
        }

        System.out.println(Arrays.toString(gamesArray));
    }

    public void printCorporateJargon() {
        System.out.println("GreedyJayInc. and ArcadeCorp do not take responsibility for any accidents " +
                "or fits of rage that occur on the premises");
    }

    public static void main(String[] args) throws InvalidGameIDException, InsufficientBalanceException,
            InvalidCustomerException, AgeLimitException {
        ActiveGame test = new ActiveGame("Ae11lloooo", "ABCDEF1234", 100, 10);
        CabinetGame test2 = new CabinetGame("Cellpppppp", "ABCDEF1235", 200, true);
        ActiveGame test3 = new ActiveGame("Aelopppppp", "ABCDEF1236", 400, 10);


        Customer a = new Customer("abcdef", "John",
                500, 34, "STAFF");
        System.out.println();
        Customer a2 = new Customer("abcdeg",
                "Johnie", 600, 19, "STUDENT");
        System.out.println();
        Arcade b = new Arcade("Bob");
        System.out.println(b);

        b.addCustomer(a);
        b.addCustomer(a2);
        b.getCustomer("abcdef");

        b.addGame(test);

        b.addGame(test2);

        b.addGame(test3);
        System.out.println();
        b.processTransaction("abcdef", "Ae11lloooo", true);
        System.out.println();
        b.findRichestCustomer();
        System.out.println();
        b.getMedianGamePrice();
        System.out.println();
        b.countArcadeGames();


    }

}
