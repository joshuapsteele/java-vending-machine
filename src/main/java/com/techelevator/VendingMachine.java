package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
    Map<String,Item> inventory = new LinkedHashMap<>();
    double machineBalance = 0;

    public void setInventory(){
        String path = "vendingmachine.csv";
        File inventoryFile = new File(path);

        try (Scanner inventoryScanner = new Scanner(inventoryFile)){
            while(inventoryScanner.hasNextLine()){
                String[] itemInfo = inventoryScanner.nextLine().split("\\|");
                Item currentItem = new Item(itemInfo[0], itemInfo[1], Double.parseDouble(itemInfo[2]), itemInfo[3]);
                inventory.put(itemInfo[0], currentItem);
            }
        }catch(FileNotFoundException e){
            System.out.println("Did not reference the correct file");
        }
    }
    public void listInventory(){
        for(String s : inventory.keySet()){
            System.out.println(s + " " + inventory.get(s).getName() + ". There are " + inventory.get(s).getQuantity() + " left, and they cost: " + inventory.get(s).getPrice());
        }
    }
    public void transaction(String key){

        //update quantity
        //update machine balance
    }
    public double change(){
        //calculating the change in coins based on remaining balance
        return 0;
    }
    public Map<String, Item> getInventory() {
        return inventory;
    }
    public void addMoney(double money){
        machineBalance += money;
    }
}
