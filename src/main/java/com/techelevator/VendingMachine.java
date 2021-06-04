package com.techelevator;

import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {



    public static void main(String[] args) {
        List<Product> inventoryList = new ArrayList<>();
        inventoryList = stock();
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

    public Product testDispense(){
        return null;
    }




}

