package com.techelevator;

import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {



    public static void main(String[] args) {


        File inputFile = new File("vendingmachine.csv");
        List<String> lineList = new ArrayList<>();




        try (Scanner fileScanner = new Scanner(inputFile)) {

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                lineList.add(line);



            }
            System.out.println(lineList);

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
        }

    }




}

