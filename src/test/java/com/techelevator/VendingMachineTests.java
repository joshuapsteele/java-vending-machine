package com.techelevator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/*
The VENDO-MATIC 800 handles almost all user input validation through defensive programming in the UI class.
These tests are meant to check the performance of the product inventory, machine balance, and change functions.
 */

public class VendingMachineTests {

    private VendingMachine testVM;

    @Before
    public void setUp() {
        try {
            testVM = new VendingMachine();
        } catch (IOException e) {
            System.out.println("Error while trying to write the machine log. Please try again.");
        }
    }

    @Test
    public void change_for_830_yields_33_Quarters_and_1_Nickel() {

        testVM.addMoney(8.30);
        int[] testArr = testVM.getChange();
        Assert.assertEquals(33, testArr[0]);
        Assert.assertEquals(1, testArr[2]);

    }

    @Test
    public void starting_with_1000_purchasing_D3_D3_A2_C3_yields_correct_inventories_and_machine_balance() {
        testVM.addMoney(10.00);
        testVM.transaction("D3");
        testVM.transaction("D3");
        testVM.transaction("A2");
        testVM.transaction("C3");

        Assert.assertEquals(3, testVM.getInventory().get("D3").getQuantity());
        Assert.assertEquals(4, testVM.getInventory().get("A2").getQuantity());
        Assert.assertEquals(5.55, testVM.getMachineBalance(), 0.01);
        testVM.getChange();
    }

    @Test
    public void out_of_stock_test(){
        testVM.addMoney(10.00);
        testVM.transaction("B2");
        testVM.transaction("B2");
        testVM.transaction("B2");
        testVM.transaction("B2");
        testVM.transaction("B2");
        Assert.assertEquals(0, testVM.getInventory().get("B2").getQuantity());
        testVM.getChange();
    }

    @Test
    public void purchasing_multiple_items_math_and_double_format_holds_up_test(){
        testVM.addMoney(20.00);
        testVM.transaction("A1");
        testVM.transaction("B3");
        testVM.transaction("C3");
        testVM.transaction("D3");
        testVM.transaction("B1");
        testVM.transaction("C2");
        testVM.transaction("A4");
        testVM.transaction("D3");
        Assert.assertEquals("$5.50",testVM.displayAsCurrency(testVM.getMachineBalance()));
        testVM.getChange();
    }

    @Test
    public void buying_the_whole_damn_machine_yields_correct_change(){
        for (int i = 0; i < 50; i++) {
            testVM.addMoney(20);
        }
        for (int j = 0; j < 5; j++) {
            for (int k = 1; k < 5; k++) {
                testVM.transaction("A" + k);
                testVM.transaction("B" + k);
                testVM.transaction("C" + k);
                testVM.transaction("D" + k);
            }
        }
        Assert.assertEquals("$867.50",testVM.displayAsCurrency(testVM.getMachineBalance()));
        testVM.getChange();
    }
}
