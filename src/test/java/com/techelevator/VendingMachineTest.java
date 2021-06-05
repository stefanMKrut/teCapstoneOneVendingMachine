package com.techelevator;

import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.techelevator.Product;

public class VendingMachineTest {
    VendingMachine obj = new VendingMachine();
    List <Product> listForTests = new ArrayList();


    @Test
    public void productList_has_same_number_of_lines_as_file() {
        File inputFile = new File("vendingmachine.csv");
        int i = 0;
        try (Scanner scanner = new Scanner(inputFile);) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        int actualList = obj.getProductList().size();
        Assert.assertEquals(i, actualList);
    }

    @Test
    public void dispense_five_quarters_and_two_dimes_for_1_dollar_45_cents(){
        obj.setBalance(1.45);
        String actualResult = obj.changeDispense();
        Assert.assertEquals("Your change is 5 quarters, 2 dimes, 0 nickels, and 0 pennies", actualResult);
    }

    @Test
    public void try_to_buy_sold_out_snack(){
        obj.setBalance(10);
        listForTests = obj.getProductList();
        listForTests.get(0).setQuantity(0);
        String actualResult = obj.dispenseProduct("A1");
        Assert.assertEquals("Sorry! Sold Out!", actualResult);

    }

    @Test
    public void add_balance_given_negative_number(){
        obj.setBalance(0);
        obj.addToBalance(-15);
        double actualAnswer = obj.getBalance();
        Assert.assertEquals(0.00, actualAnswer, 0);
    }



}
