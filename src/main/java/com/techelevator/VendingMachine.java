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
    private List<Product> productList = new ArrayList<>();
    private double balance;

    public VendingMachine() {
        this.balance = 0;
    }

    public static void main(String[] args) {
        VendingMachine test = new VendingMachine();
        test.stock();
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

    public void menuOneChoice() {
        Menu menuOne = new Menu();
        int menuOneChoice = menuOne.runStartMenu();
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
        } else if (menuOneChoice > 3 || menuOneChoice < 1){
            System.out.println("Sorry! Not a valid option!");
            menuOneChoice();
        }
    }

    public void menuTwoChoice() {
        System.out.println(" ");
        System.out.printf("Your current balance is: %.2f ", balance);
        System.out.println();
        int menuTwoChoice = Menu.runPurchaseMenu();
        if (menuTwoChoice == 1) {
            addToBalance();
        } else if (menuTwoChoice == 2) {
            dispenseProduct();
        } else if (menuTwoChoice == 3){
            endTransaction();
        } else if (menuTwoChoice > 3 || menuTwoChoice < 1){
            System.out.println("Sorry! Not a valid option!");
            menuTwoChoice();
        }

    }


    public void addToBalance() {
        int cashToAdd = Menu.feedMoney();
        balance = balance + cashToAdd;
        System.out.printf("Current Money Provided: %.2f", balance);
        try (FileWriter fileWriter = new FileWriter("Log.txt", true);
             PrintWriter writer = new PrintWriter(fileWriter)) {
            writer.println(LocalDateTime.now() + " FEED MONEY $" + cashToAdd + " $" + balance);
        } catch (IOException e) {
            System.out.println("No such file");
        }
        menuTwoChoice();
    }

    public void dispenseProduct() {
        int i = 0;
        for (Product productInList : productList) {
            System.out.println(productList.get(i).getProduct());
            i++;
        }
        String selectionChoice = Menu.runDispenseMenu();
        double startingBalance = balance;
        int x = 0;
        if (selectionChoice.equals("1")){
            menuTwoChoice();
        }
        for (Product productInList : productList) {
            x++;
            if (x == productList.size() && !selectionChoice.equals(productInList.getSlotID())) {
                System.out.println("Sorry! Not a valid option! Try again");
                dispenseProduct();
            } else if (selectionChoice.equals(productInList.getSlotID())) {
                if (productInList.getPrice() > balance) {
                    System.out.println("Sorry! Not enough money! Try again");
                    dispenseProduct();
                } else if (productInList.getQuantity() > 0) {
                    System.out.println("Enjoy your " + productInList.getName());
                    productInList.setQuantity(productInList.getQuantity() - 1);
                    balance = (balance - productInList.getPrice());
                    if (productInList.getType().equals("Chip")){
                        System.out.println("Crunch Crunch, Yum!");
                    } else if (productInList.getType().equals("Candy")){
                        System.out.println("Munch Munch, Yum!");
                    } else if (productInList.getType().equals("Drink")){
                        System.out.println("Glug Glug, Yum!");
                    } else if (productInList.getType().equals("Gum")){
                        System.out.println("Chew Chew, Yum!");
                    }
                    try (FileWriter fileWriter = new FileWriter("Log.txt", true);
                         PrintWriter writer = new PrintWriter(fileWriter)) {
                        writer.println(LocalDateTime.now() + " " + productInList.getName() + " $" + startingBalance + " $" + balance);
                    } catch (IOException e) {
                        System.out.println("No such file");
                    } menuTwoChoice();
                } else if (productInList.getQuantity() == 0) {
                    System.out.println("Sorry! Sold Out!");
                    dispenseProduct();
                }
            }
        }


    }

    public void endTransaction(){
        changeDispense();
        System.out.println("Please retrieve change");
        menuOneChoice();
    }

    public void changeDispense() {
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
        System.out.println("Your change is " + quarters + " quarters, " + dimes + " dimes, " + nickels + " nickels, and " + pennies + " pennies");
        System.out.println();
        balance = 0;
        try (FileWriter fileWriter = new FileWriter("Log.txt", true);
             PrintWriter writer = new PrintWriter(fileWriter)) {
            writer.println(LocalDateTime.now() + " GIVE CHANGE $" + startingChange + " $" + balance);
        } catch (IOException e) {
            System.out.println("No such file");
        }
    }

    public void exitVendingMachine(){
        System.out.println("Goodbye! Have a wonderful day!");
        System.exit(1);
    }

    public void returnToPurchaseMenu(){

    }
}

