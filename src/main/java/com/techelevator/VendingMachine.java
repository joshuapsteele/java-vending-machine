package com.techelevator;

import java.io.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
    Map<String, Item> inventory = new LinkedHashMap<>();
    double machineBalance = 0;

    // I'm thinking we need something like the following in here to be able to write to a log.txt file.

    File log = new File("Log.txt");
    FileWriter fileWriter = new FileWriter(log, true);
    PrintWriter logWriter = new PrintWriter(fileWriter);

    public VendingMachine() throws IOException {
        setInventory();
    }

    public void setInventory() {
        String path = "vendingmachine.csv";
        File inventoryFile = new File(path);

        try (Scanner inventoryScanner = new Scanner(inventoryFile)) {
            while (inventoryScanner.hasNextLine()) {

                String[] itemInfo = inventoryScanner.nextLine().split("\\|");
                String itemCode = itemInfo[0];
                String itemName = itemInfo[1];
                double itemPrice = Double.parseDouble(itemInfo[2]);
                String itemType = itemInfo[3];

                Item currentItem = new Item(itemCode, itemName, itemPrice, itemType);
                inventory.put(itemCode, currentItem);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error. File not found!");
        }
    }

    public void listInventory() {

        for (String itemCode : inventory.keySet()) {
            if (inventory.get(itemCode).getQuantity() == 0) {

                System.out.format("%-5s%-20s%-15s%n", itemCode, inventory.get(itemCode).getName(), "SOLD OUT");

            } else {

                System.out.format("%-5s%-20s%-15s%n", itemCode, inventory.get(itemCode).getName(), inventory.get(itemCode).getQuantity() + " remaining"); ///add price

            }
        }
    }

    public void transaction(String key) {
        //As long as the item is not sold out, updates quantity of the requested item
            inventory.get(key).decreaseQuantity();
        //As long as there's enough money to buy the item, updates the machine balance
            machineBalance -= inventory.get(key).getPrice();


        logWriter.println(LocalDateTime.now().toString() + " " + inventory.get(key).getName() + " " + inventory.get(key).getCode() + " " + machineBalance);

    }

    public void change() {
        int centsLeft = (int) (machineBalance * 100);
        int numberOfQuarters = 0;
        int numberOfDimes = 0;
        int numberOfNickels = 0;
        int numberOfPennies = 0;
        while (centsLeft > 0)
            if (centsLeft >= 25) {
                numberOfQuarters++;
                centsLeft -= 25;
            } else if (centsLeft >= 10) {
                numberOfDimes++;
                centsLeft -= 10;
            } else if (centsLeft >= 5) {
                numberOfNickels++;
                centsLeft -= 5;
            } else if (centsLeft >= 1) {
                numberOfPennies++;
                centsLeft--;
            }

        System.out.println("Here is your change.");
        System.out.println("$ " + machineBalance);
        System.out.println(numberOfQuarters + " Quarters");
        System.out.println(numberOfDimes + " Dimes");
        System.out.println(numberOfNickels + " Nickels");
        System.out.println(numberOfPennies + " Pennies");
        //look into formatting the double
        if (centsLeft == 0) {
            machineBalance = 0;
        }
    }

    public Map<String, Item> getInventory() {
        return inventory;
    }

    public void addMoney(double money) {
        machineBalance += money;
    }

    public double getMachineBalance() {
        return machineBalance;
    }
}
