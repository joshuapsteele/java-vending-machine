package com.techelevator;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
    Map<String, Item> inventory = new LinkedHashMap<>();
    double machineBalance = 0;

    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    String timeNow = timeFormatter.format(LocalDateTime.now());

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
        System.out.println(LocalDateTime.now().toString());

        for (String itemCode : inventory.keySet()) {
            if (inventory.get(itemCode).getQuantity() == 0) {

                System.out.format("%-5s%-20s%-10s%n", itemCode, inventory.get(itemCode).getName(), "SOLD OUT");

            } else {

                System.out.format("%-5s%-20s%-10s%10.2f%n", itemCode, inventory.get(itemCode).getName(), inventory.get(itemCode).getQuantity() + " remaining", inventory.get(itemCode).getPrice());

            }
        }
    }

    public void transaction(String key) {
        inventory.get(key).decreaseQuantity();
        logWriter.println(timeNow + " " + inventory.get(key).getName() + " " + inventory.get(key).getCode() + " $" + getMachineBalance() + " $" + (getMachineBalance() - inventory.get(key).getPrice()));
        logWriter.flush();
        machineBalance -= inventory.get(key).getPrice();
    }

    public int[] change() {
        int centsLeft = (int) ((getMachineBalance() * 100) + 0.5);
        int[] changeArr = new int[]{0, 0, 0, 0,centsLeft};
//        int numberOfQuarters = 0;
//        int numberOfDimes = 0;
//        int numberOfNickels = 0;
//        int numberOfPennies = 0;
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

//        System.out.println("Here is your change.");
//        System.out.println("$ " + (int) ((getMachineBalance() * 100) + 0.5) / 100d); //Rounds the double before displaying.
//        System.out.println(numberOfQuarters + " Quarters");
//        System.out.println(numberOfDimes + " Dimes");
//        System.out.println(numberOfNickels + " Nickels");
//        System.out.println(numberOfPennies + " Pennies");

        logWriter.println(timeNow + " GIVE CHANGE: $" + getMachineBalance() + " $0.00");
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
        logWriter.println(timeNow + " FEED MONEY: $" + money + " $" + getMachineBalance());
        logWriter.flush();
    }

    public double getMachineBalance() {
        return machineBalance;
    }
}
