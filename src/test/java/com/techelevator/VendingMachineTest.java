package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineTest {

    @Test

    public void returns_correct_snack_with_slotID() {
        //Arrange
        VendingMachine obj = new VendingMachine();
        double balance = 3.00;
        int menuOneChoice = 2;
        int menuTwoChoice = 2;
        String selectionChoice = "A1";

        //Act
        obj.dispenseProduct();


        //Assert
        Assert.assertEquals("Enjoy your Potato Crisps", selectionChoice);


    }
}
