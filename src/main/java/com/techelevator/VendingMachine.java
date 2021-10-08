package com.techelevator;

import javax.swing.text.NumberFormatter;
import java.io.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
<<<<<<< HEAD
    /*
    Hey for when you open this up tomorrow morning, there's a bug I caught which is that while the program
    outputs fine, the output to the log file looses correct double format after subtracting x.x5 from another
    number also ending in .x5. I tried a few things to fix it that didn't, and since you did the double formatting
    in this I wanted to defer to you on how to solve this. If you want to see what I mean, run the main application,
    feed in 20, then buy D1 2 or 3 times, end the program, then take a look at the log.
    I tried changing the getMachineBalance method to auto format everytime it was used but that didn't help
    so feel free to change it back
     */
=======
>>>>>>> b0a171f02f3d4aa0f7fb8220ce529b21ce92f5b9

    Map<String, Item> inventory = new LinkedHashMap<>();
    double machineBalance = 0;

    DateTimeFormatter timeFormatterForLog = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    String timeNowForLog = timeFormatterForLog.format(LocalDateTime.now());

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
            System.out.println("ERROR. Inventory file not found!");
        }
    }

    public void listInventory() {

        for (String itemCode : inventory.keySet()) {
            if (inventory.get(itemCode).getQuantity() == 0) {

                System.out.format("%-5s%-20s%-10s%n", itemCode, inventory.get(itemCode).getName(), "SOLD OUT");

            } else {

<<<<<<< HEAD
                System.out.format("%-5s%-20s%-10s%10.2f%n", itemCode, inventory.get(itemCode).getName(),
                        inventory.get(itemCode).getQuantity() + " remaining", inventory.get(itemCode).getPrice());
=======
                System.out.format("%-5s%-20s%-10s%10s%n", itemCode, inventory.get(itemCode).getName(),
                        inventory.get(itemCode).getQuantity() + " remaining", displayAsCurrency(inventory.get(itemCode).getPrice()));
>>>>>>> b0a171f02f3d4aa0f7fb8220ce529b21ce92f5b9

            }
        }
    }

    public void transaction(String itemCode) {
        Item purchasedItem = inventory.get(itemCode);
        double costOfPurchasedItem = purchasedItem.getPrice();
        String balanceBeforeTransaction = displayAsCurrency(getMachineBalance());
        String balanceAfterTransaction = displayAsCurrency(getMachineBalance() - costOfPurchasedItem);

        logWriter.println(timeNowForLog + " " + purchasedItem.getName() + " " + itemCode
                + " " + balanceBeforeTransaction + " " + balanceAfterTransaction);
        logWriter.flush();

        purchasedItem.decreaseQuantity();
        machineBalance -= costOfPurchasedItem;
    }

    public int[] getChange() {
        int centsLeft = (int) ((getMachineBalance() * 100) + 0.5);
        int[] changeArr = new int[]{0, 0, 0, 0, centsLeft};
        while (centsLeft > 0)
            if (centsLeft >= 25) {
                changeArr[0]++;
                centsLeft -= 25;
            } else if (centsLeft >= 10) {
                changeArr[1]++;
                centsLeft -= 10;
            } else if (centsLeft >= 5) {
                changeArr[2]++;
                centsLeft -= 5;
            } else if (centsLeft >= 1) {
                changeArr[3]++;
                centsLeft--;
            }

        logWriter.println(timeNowForLog + " GIVE CHANGE: " + displayAsCurrency(getMachineBalance()) + " $0.00");
        logWriter.flush();

        if (centsLeft == 0) {
            machineBalance = 0;
        }
        return changeArr;
    }

    public Map<String, Item> getInventory() {
        return inventory;
    }

    public void addMoney(double money) {
        machineBalance += money;
        logWriter.println(timeNowForLog + " FEED MONEY: " + displayAsCurrency(money) + " " + displayAsCurrency(getMachineBalance()));
        logWriter.flush();
    }

    public double getMachineBalance() {
        return machineBalance;
    }

    public double roundTheDouble(double numberToRound) {
        return (((int) (numberToRound * 100 + 0.5)) / 100d);
    }

    public String displayAsCurrency(double rawDoubleNeedsFormatting) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(rawDoubleNeedsFormatting);
    }
}
