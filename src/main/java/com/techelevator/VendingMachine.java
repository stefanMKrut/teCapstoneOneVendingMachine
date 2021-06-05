package com.techelevator;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techelevator.Menu;

import java.time.LocalDate;
import java.util.Date;

public class VendingMachine {
    private final List<Product> productList = new ArrayList<>();
    private double balance;

    public VendingMachine() {
        this.balance = 0;
        stock();
    }

    public static void main(String[] args) {
        VendingMachine test = new VendingMachine();
        test.menuOneChoice();

    }

    public void stock() {
        //this takes the file & turns each line into an array list
        File inputFile = new File("vendingmachine.csv");
        List<String> lineList = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(inputFile)) {

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                lineList.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
        }

        //this takes the arrayList & turns it into a list of Products

        for (String line : lineList) {
            String[] splitLines = line.split("\\|");
            Product individualProduct = new Product(splitLines[0], splitLines[1], splitLines[2], splitLines[3]);
            this.productList.add(individualProduct);
        }
    }

    public List getProductList() {
        return productList;
    }

    public void menuOneChoice() {
        Menu menuOne = new Menu();
        int menuOneChoice = Menu.runStartMenu();
        if (menuOneChoice == 1) {
            int i = 0;
            for (Product productInList : productList) {
                System.out.println(productList.get(i).getProduct());
                i++;
            }
            System.out.println();
            menuOneChoice();
        } else if (menuOneChoice == 2) {
            menuTwoChoice();
        } else if (menuOneChoice == 3) {
            System.out.println("Goodbye!!");
            System.exit(1);
        } else if (menuOneChoice > 3 || menuOneChoice < 1) {
            System.out.println("Sorry! Invalid Option!");
            menuOneChoice();
        }
    }

    public void menuTwoChoice() {
        System.out.println(" ");
        System.out.printf("Your current balance is: %.2f ", balance);
        System.out.println();
        int menuTwoChoice = Menu.runPurchaseMenu();
        if (menuTwoChoice == 1) {
            int cashToAdd = Menu.feedMoney();
            addToBalance(cashToAdd);
        } else if (menuTwoChoice == 2) {
            int i = 0;
            for (Product productInList : productList) {
                System.out.println(productList.get(i).getProduct());
                i++;
            }
            String selectionChoice = Menu.runDispenseMenu();
            dispenseProduct(selectionChoice);
        } else if (menuTwoChoice == 3) {
            endTransaction();
        } else if (menuTwoChoice > 3 || menuTwoChoice < 1) {
            System.out.println("Sorry! Not a valid option!");
            menuTwoChoice();
        }

    }

    public void setBalance(double balanceAmount) {
        balance = balanceAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void addToBalance(int cashToAdd) {
        if (cashToAdd < 1) {
            System.out.println("Please enter a valid whole dollar amount.");
        } else {
            balance = balance + cashToAdd;
            System.out.printf("Current Money Provided: %.2f", balance);
            try (FileWriter fileWriter = new FileWriter("Log.txt", true);
                 PrintWriter writer = new PrintWriter(fileWriter)) {
                writer.println(LocalDateTime.now() + " FEED MONEY $" + cashToAdd + " $" + balance);
            } catch (IOException e) {
                System.out.println("No such file");
            }

        }
        rerouteMenu(2);
    }

    public String dispenseProduct(String selectionChoice) {
        double startingBalance = balance;
        int x = 0;
        if (selectionChoice.equals("1")) {
            rerouteMenu(2);
        }
        for (Product productInList : productList) {
            x++;
            if (x == productList.size() && !selectionChoice.equals(productInList.getSlotID())) {
                String errorInvalidChoice = "Sorry! Not a valid option! Try again";
                System.out.println(errorInvalidChoice);
                rerouteMenu(2);
                return errorInvalidChoice;
            } else if (selectionChoice.equals(productInList.getSlotID())) {
                if (productInList.getPrice() > balance) {
                    String balanceTooLow = "Sorry! Not enough money! Try again";
                    System.out.println(balanceTooLow);
                    rerouteMenu(2);
                    return balanceTooLow;
                } else if (productInList.getQuantity() > 0) {
                    String saleMessage = "Enjoy your " + productInList.getName();
                    System.out.println(saleMessage);
                    productInList.setQuantity(productInList.getQuantity() - 1);
                    balance = (balance - productInList.getPrice());
                    if (productInList.getType().equals("Chip")) {
                        System.out.println("Crunch Crunch, Yum!");
                    } else if (productInList.getType().equals("Candy")) {
                        System.out.println("Munch Munch, Yum!");
                    } else if (productInList.getType().equals("Drink")) {
                        System.out.println("Glug Glug, Yum!");
                    } else if (productInList.getType().equals("Gum")) {
                        System.out.println("Chew Chew, Yum!");
                    }
                    try (FileWriter fileWriter = new FileWriter("Log.txt", true);
                         PrintWriter writer = new PrintWriter(fileWriter)) {
                        writer.println(LocalDateTime.now() + " " + productInList.getName() + " $" + startingBalance + " $" + balance);
                    } catch (IOException e) {
                        System.out.println("No such file");
                    }
                    rerouteMenu(2);
                    return saleMessage;
                } else if (productInList.getQuantity() == 0) {
                    String soldOut = "Sorry! Sold Out!";
                    System.out.println(soldOut);
                    rerouteMenu(2);
                    return soldOut;
                }
            }
        }
        String errorSomethingHasGoneWrong = "Something Has Gone Wrong. Exiting Program";
        return errorSomethingHasGoneWrong;


    }

    public void endTransaction() {
        changeDispense();
        System.out.println("Please retrieve change");
        menuOneChoice();
    }

    public String changeDispense() {
        Double startingChange = balance;
        Double changeDouble = balance * 100;
        long changeLong = Math.round(changeDouble);
        int changeInt = (int) changeLong;
        int quarters = changeInt / 25;
        changeInt = changeInt % 25;
        int dimes = 0;
        int nickels = 0;
        int pennies = 0;
        if (changeInt >= 10) {
            dimes = changeInt / 10;
            changeInt = changeInt % 10;
            if (changeInt >= 5) {
                nickels = changeInt / 5;
                changeInt = changeInt % 5;
                pennies = changeInt;
            }
        } else if (changeInt < 10 && changeInt >= 5) {
            nickels = changeInt / 5;
            changeInt = changeInt % 5;
            pennies = changeInt;
        } else {
            pennies = changeInt;
        }
        String changeDispensed = "Your change is " + quarters + " quarters, " + dimes + " dimes, " + nickels + " nickels, and " + pennies + " pennies";
        System.out.println(changeDispensed);
        System.out.println();
        balance = 0;
        try (FileWriter fileWriter = new FileWriter("Log.txt", true);
             PrintWriter writer = new PrintWriter(fileWriter)) {
            writer.println(LocalDateTime.now() + " GIVE CHANGE $" + startingChange + " $" + balance);
        } catch (IOException e) {
            System.out.println("No such file");
        }
        return changeDispensed;
    }

    public void exitVendingMachine() {
        System.out.println("Goodbye! Have a wonderful day!");
        System.exit(1);
    }

    public void returnToPurchaseMenu() {

    }

    public void rerouteMenu(int menuID) {
        if (menuID == 1) {
            menuOneChoice();
        } else if (menuID == 2) {
            menuTwoChoice();
        } else {
            System.out.println("Invalid Menu ID. Closing Program");
            endTransaction();
            exitVendingMachine();
        }
    }

}

