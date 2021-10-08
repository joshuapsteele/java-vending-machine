package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

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
    public void change_for_830_yields_33_Quarters_and_1_Nickel(){

        testVM.addMoney(8.30);
        int[] testArr = testVM.getChange();
        Assert.assertEquals(33,testArr[0]);
        Assert.assertEquals(1,testArr[2]);

    }

    @Test
    public void starting_with_1000_purchasing_D3_D3_A2_C3_yields_correct_inventories_and_machine_balance(){
        testVM.addMoney(10.00);
        testVM.transaction("D3");
        testVM.transaction("D3");
        testVM.transaction("A2");
        testVM.transaction("C3");

        Assert.assertEquals(3, testVM.getInventory().get("D3").getQuantity());
        Assert.assertEquals(4, testVM.getInventory().get("A2").getQuantity());
        Assert.assertEquals(5.55, testVM.getMachineBalance(),0.01);
    }
}
