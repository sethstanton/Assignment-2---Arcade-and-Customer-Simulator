package com.company;

//Here is the customer class, this simulates how a customers balance is affected while using the arcade.
//An enum is used along with other fields to help create this class.
//methods addFunds and chargeAccount are created here to alter and modify the balance as the files are read in.
public class Customer {

    private String AccountID;
    private String CustomerName;
    private int AccountBalance;
    private int Age;

    public enum PersonalDiscount {STAFF, STUDENT, NODISCOUNT}

    private String PersonalDiscount;


    private String StudentBalance;

    //constructor sets default balance to 0

    public Customer(String AccountID, String CustomerName, int Age,
                    String personalDiscount) throws InvalidCustomerException {
        this.CustomerName = CustomerName;
        this.Age = Age;
        this.PersonalDiscount = (PersonalDiscount.valueOf(personalDiscount));
        this.AccountBalance = 0;
        this.AccountID = AccountID;


        if (AccountID.length() == 6) {
            System.out.println("I'm a valid account id");
        } else {
            throw new InvalidCustomerException(AccountID + " AccountID not available");

        }

    }

    // constructor for specified balance


    public Customer(String AccountID, String CustomerName, int AccountBalance,
                    int Age, String PersonalDiscount) throws InvalidCustomerException, InsufficientBalanceException {
        this.Age = Age;
        this.PersonalDiscount = PersonalDiscount;
        this.StudentBalance = getPersonalDiscount();
        this.CustomerName = CustomerName;
        this.AccountID = AccountID;
        this.AccountBalance = AccountBalance;


        if (AccountID.length() == 6) {
            System.out.println("I'm a valid account id wooo!");
        } else {
            throw new InvalidCustomerException(AccountID + " AccountID not available");
        }
        if (AccountBalance >= 0) {
            System.out.println("I'm a valid balance wooo!");
            this.AccountBalance = AccountBalance;
        } else {
            if (StudentBalance.equals("STUDENT") && (AccountBalance < 0 && AccountBalance >= -500)) {

            }
            throw new InsufficientBalanceException(AccountBalance + " Account balance is negative");
        }
    }

    //this is a new customer constructor to help read in the general public
    public Customer(String AccountID, String CustomerName, int AccountBalance,
                    int Age) throws InvalidCustomerException, InsufficientBalanceException {
        this.Age = Age;
        this.AccountBalance = AccountBalance;
        this.AccountID = AccountID;
        this.CustomerName = CustomerName;
        if (AccountID.length() == 6) {
            System.out.println("I'm a valid account id wooo!");
        } else {
            throw new InvalidCustomerException(AccountID + " AccountID not available");
        }
        if (AccountBalance >= 0) {
            System.out.println("I'm a valid balance wooo!");
            this.AccountBalance = AccountBalance;
        } else {
            if (StudentBalance.equals("STUDENT") && (AccountBalance < 0 && AccountBalance >= -500)) {

            }
            throw new InsufficientBalanceException(AccountBalance + " Account balance is negative");
        }
    }

    public String getAccountID() {
        return AccountID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public int getAccountBalance() {
        return AccountBalance;
    }

    public int getAge() {
        return Age;
    }

    public String getPersonalDiscount() {
        return String.valueOf(PersonalDiscount);
    }


    public int addFunds(int amount) {
        if (amount >= 1) {
            if (StudentBalance.equals("STUDENT") && (AccountBalance < 0 && AccountBalance >= -500)) {
                System.out.println("meep");

                return (AccountBalance + amount);
            } else if (StudentBalance.equals("STAFF")) {
                return (AccountBalance + amount);

            } else {
                System.out.println(" No Balance Added ");
            }
        }

        return AccountBalance;
    }

    public int chargeAccount(ArcadeGame test, boolean peak) throws InsufficientBalanceException, AgeLimitException {
        //Variable to define game price
        int GamePrice = test.calculatePrice(peak);
        String discount = getPersonalDiscount();

        // if balance is good
        if (AccountBalance > test.getGamePrice() || (StudentBalance.equals("STUDENT")
                && AccountBalance > -500 && (-500 - AccountBalance) >= test.GamePrice)) {
            if (test instanceof VirtualRealityGame || test instanceof ActiveGame) {

                int AgeReq = ((ActiveGame) test).AgeReq;

                // if virtual
                if (test.getGameID().startsWith("V")) {

                    // Age requirement Check
                    if (AgeReq < Age) {

                        if (discount.equals("STAFF")) {
                            // 10% staff discount
                            GamePrice = (90 * GamePrice) / 100;
                            this.AccountBalance = this.AccountBalance - GamePrice;
                            return GamePrice;

                        } else if (discount.equals("STUDENT")) {
                            // 5% student discount
                            GamePrice = (95 * GamePrice) / 100;
                            this.AccountBalance = this.AccountBalance - GamePrice;
                            return GamePrice;
                        }
                    } else
                        throw new AgeLimitException(Age + " You're too young for this game!");
                }

                // if activegame
                else

                    // Age Requirement check
                    if (AgeReq < Age) {

                        if (discount.equals("STAFF")) {
                            // 10% staff discount
                            GamePrice = (90 * GamePrice) / 100;
                            this.AccountBalance = this.AccountBalance - GamePrice;
                            return GamePrice;
                        } else if (discount.equals("STUDENT")) {
                            // 5% student discount
                            GamePrice = (95 * GamePrice) / 100;
                            this.AccountBalance = this.AccountBalance - GamePrice;
                            return GamePrice;
                        }
                    }
            }
            // if cabinet
            else if (test.getGameID().startsWith("C")) {

                if (discount.equals("STAFF")) {
                    // 10% staff discount
                    GamePrice = (90 * GamePrice) / 100;
                    this.AccountBalance = this.AccountBalance - GamePrice;
                    return GamePrice;
                } else if (discount.equals("STUDENT")) {
                    // 5% student discount
                    GamePrice = (95 * GamePrice) / 100;
                    this.AccountBalance = this.AccountBalance - GamePrice;
                    return GamePrice;
                }
            }


            // else off-peak times
            else
                throw new AgeLimitException(Age + " You're too young for this game!");
        } else
            throw new InsufficientBalanceException(AccountBalance + " Insufficient Balance");
        return 0; //a quick fix for now
    }


    public String toString() {
        return "Personal Account ID " + this.AccountID + ", Customer Name " + this.CustomerName +
                ", Account Balance " + this.AccountBalance + ", Customer's Age " +
                this.Age + " Personal Discount " + PersonalDiscount;
    }

    public static void main(String[] args) throws InsufficientBalanceException, InvalidCustomerException {
        Customer adam = new Customer("123456", "Adam",
                100, 21, "STAFF");
        System.out.println(adam);
        adam.addFunds(500);
        System.out.println(adam);
    }

}
