package com.techelevator;

import java.io.IOException;

// Vending Machine Command Line Interface application
public class VendingMachineCLI {

    public static void main(String[] args) {

        VendingMachine newVendingMachine;

        try {
            newVendingMachine = new VendingMachine();
        } catch (IOException e) {
            System.out.println("Error while trying to write the machine log. Please try loading the Vendo-Matic 800 again.");
            return;
        }

        System.out.println("*******************************");
        System.out.println("Welcome to the VENDO-MATIC 800!");
        System.out.println("*******************************");

        UI userInterface = new UI(newVendingMachine);

        int userInputMainMenu = 0;
        while (userInputMainMenu != 3) {
            userInputMainMenu = userInterface.mainMenu();
            if (userInputMainMenu == 1) {
                newVendingMachine.listInventory();
            }
            if (userInputMainMenu == 2) {
                userInterface.purchaseMenu();
            }
        }
        System.out.println("Thanks for using the VENDO-MATIC 800. Have a wonderful day!");
    }
}
