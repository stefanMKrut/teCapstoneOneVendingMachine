package com.techelevator;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.ls.LSOutput;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineTest {
    VendingMachine obj = new VendingMachine();

    @Test
    public void productList_has_same_number_of_lines_as_file() {
        File inputFile = new File("vendingmachine.csv");
        int i = 0;
        try (Scanner scanner = new Scanner(inputFile);) {
            while (scanner.hasNextLine()) {
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        int actualList = obj.getProductList().size();
        Assert.assertEquals(i, actualList);
    }

     */


}
