package com.techelevator;

import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {
    private static Scanner in;
    //private PrintWriter out;

    public Menu() {
        this.in = new Scanner(System.in);
    }


    public static int runStartMenu() {
        System.out.println(" ");
        System.out.println("Welcome to the Vendomatic 800! ☂️");
        System.out.println(" ");
        System.out.println("Enter 1 to view our delicious options!");
        System.out.println("Enter 2 to purchase.");
        System.out.println("Enter 3 to exit.");
        String userChoice = in.nextLine();
        int stringToInt = Integer.parseInt(userChoice);
        return stringToInt;
    }

    public static int runPurchaseMenu() {
        System.out.println("Enter 1 to feed money.");
        System.out.println("Enter 2 to make purchase.");
        System.out.println("Enter 3 to end transaction");
        String userChoice = in.nextLine();
        int stringToInt = Integer.parseInt(userChoice);
        return stringToInt;
    }

    public static int feedMoney(){ //can we simplify this?
        System.out.println("Please enter money in whole dollar amount. Example: 5.00, 10.00");
        String cashInputString = in.nextLine();
        Double cashInputDouble = Double.parseDouble(cashInputString);
        long cashInputLong = Math.round(cashInputDouble);
        int cashInput = (int) cashInputLong;
        return cashInput;
    }

    public static String runDispenseMenu() {
        System.out.println(" ");
        System.out.println("Please make a selection by entering a valid slot.  Example: A1");
        System.out.println("Enter 1 to return to Purchase Menu");
        String selectionEntered = in.nextLine();
        return selectionEntered.toUpperCase();

    }


}

