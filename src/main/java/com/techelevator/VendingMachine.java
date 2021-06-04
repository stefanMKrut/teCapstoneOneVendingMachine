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
    private static int balance = 0;



    public static void main(String[] args) {
        menuOneChoice();

    }

    public static List<Product> stock(){
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
        List<Product> productList = new ArrayList<Product>();
        for (String line : lineList){
            String[] splitLines = line.split("\\|");
            Product individualProduct = new Product(splitLines[0], splitLines[1], splitLines[2], splitLines[3]);
            productList.add(individualProduct);
        }
        return productList;
    }

   /* public String testDispense(){
        List<Product> inventoryList = new ArrayList<>();
        inventoryList = stock();
        for (Product product : inventoryList){
            if (product.getSlotID().equals("A1")){
                product.setQuantity(product.getQuantity() - 1);
                // balance = balance - product.getPrice;
                return product.getName();
            }
        } return "Entry not valid";
    } */

    public static void printInventory(){
        List<Product> inventoryList = new ArrayList<>();
        inventoryList = stock();
        for (Product product : inventoryList){
            System.out.println(product.getProduct());
        }
    }
    public static void menuOneChoice(){
        Menu menuOne = new Menu();
        int menuOneChoice = menuOne.runStartMenu();
        if (menuOneChoice == 1){
            printInventory();
        } else if (menuOneChoice == 2){
            menuTwoChoice();
        } else if (menuOneChoice == 3){
            System.out.println("Goodbye!!");
            System.exit(1);
        }
    }

    public static void menuTwoChoice(){
        int menuTwoChoice = Menu.runPurchaseMenu();
        if (menuTwoChoice == 1){
            addToBalance();
        }
    }

    public static int getBalance(){
        return balance;
    }

    public static void addToBalance() {
        int cashToAdd = Menu.feedMoney();
        balance = getBalance() + cashToAdd;
        System.out.println("Your current balance is " + getBalance());
        try (FileWriter fileWriter = new FileWriter("Log.txt", true);
             PrintWriter writer = new PrintWriter(fileWriter)) {
            writer.println(LocalDateTime.now() + " FEED MONEY " + cashToAdd + " " + getBalance() );
        } catch (IOException e) {
            System.out.println("No such file");
        }

    }
    }

