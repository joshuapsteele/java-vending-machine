package com.techelevator;

import java.io.FileNotFoundException;
import java.io.IOException;

// Vending Machine Command Line Interface application
public class VendingMachineCLI {

    public static void main(String[] args) {

        VendingMachine newVendingMachine;
        UI userInterface;
        VendingMachineSalesReport salesReport;

        try {
            newVendingMachine = new VendingMachine();
        } catch (IOException e) {
            System.out.println("Error while trying to write the machine log. " +
                    "Please try loading the Vendo-Matic 800 again.");
            return;
        }

        System.out.println("*******************************");
        System.out.println("Welcome to the VENDO-MATIC 800!");
        System.out.println("*******************************");

        userInterface = new UI(newVendingMachine);

        int userInputMainMenu = 0;
        while (userInputMainMenu != 3) {
            userInputMainMenu = userInterface.mainMenu();
            if (userInputMainMenu == 1) {
                newVendingMachine.listInventory();
            }
            if (userInputMainMenu == 2) {
                userInterface.purchaseMenu();
            }
            if (userInputMainMenu == 4) {
                salesReport = new VendingMachineSalesReport(newVendingMachine);
                try {
                    salesReport.generateSalesReport();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Thanks for using the VENDO-MATIC 800. Have a wonderful day!");
    }
}
