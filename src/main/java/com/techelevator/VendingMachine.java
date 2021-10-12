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

    private Map<String, Item> inventory = new LinkedHashMap<>();
    private double machineBalance = 0;

    DateTimeFormatter timeFormatterForLog = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    String timeNowForLog = timeFormatterForLog.format(LocalDateTime.now());

    File log = new File("Log.txt");
    FileWriter fileWriter = new FileWriter(log, true);
    PrintWriter logWriter = new PrintWriter(fileWriter);

    public VendingMachine() throws IOException {
        setInventory(); // Fully stocks the vending machine from the .csv file each time a vending machine is created.
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

                System.out.format("%-5s%-20s%-10s%10s%n", itemCode, inventory.get(itemCode).getName(),
                        inventory.get(itemCode).getQuantity() + " remaining",
                        displayAsCurrency(inventory.get(itemCode).getPrice()));
            }
        }
    }

    public void transaction(String itemCode) {
        Item purchasedItem = inventory.get(itemCode);
        double costOfPurchasedItem = purchasedItem.getPrice();
        String balanceBeforeTransaction = displayAsCurrency(getMachineBalance());
        String balanceAfterTransaction =
                displayAsCurrency(getMachineBalance() - costOfPurchasedItem);

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
        logWriter.println(timeNowForLog + " FEED MONEY: " + displayAsCurrency(money) + " "
                + displayAsCurrency(getMachineBalance()));
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
