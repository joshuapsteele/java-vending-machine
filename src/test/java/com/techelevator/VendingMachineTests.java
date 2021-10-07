package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class VendingMachineTests {
    @Test
    public void changeTest(){
        VendingMachine testVM = null;
        try {
            testVM = new VendingMachine();
        } catch (IOException e) {
            System.out.println("Error while trying to write the machine log. Please try again.");
        }
        testVM.addMoney(8.30);
        int[] testArr = testVM.change();
        Assert.assertEquals(33,testArr[0]);
        Assert.assertEquals(1,testArr[2]);

    }
    @Test
    public void transactionTest(){
        VendingMachine testVM = null;
        try {
            testVM = new VendingMachine();
        } catch (IOException e) {
            System.out.println("Error while trying to write the machine log. Please try again.");
        }
        testVM.addMoney(10.00);
        testVM.transaction("D3");
        testVM.transaction("D3");
        testVM.transaction("A2");
        testVM.transaction("C3");

        Assert.assertEquals(3, testVM.getInventory().get("D3").getQuantity());
        Assert.assertEquals(4, testVM.getInventory().get("A2").getQuantity());
        Assert.assertEquals(5.55, testVM.getMachineBalance(),3);
    }
}
