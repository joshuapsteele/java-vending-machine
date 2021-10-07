package com.techelevator;

import java.util.Scanner;

public class UI {
    Scanner input = new Scanner(System.in);
    VendingMachine UIvm;
    public UI(VendingMachine vm){
        this.UIvm = vm;
    }
    public int mainMenu(){
        int inputNum = 0;
        while(true) {
            System.out.println("Please select one of these options:");
            System.out.println("(1) Display vending machine items");
            System.out.println("(2) Purchase");
            System.out.println("(3) Exit");
            String userInput = input.nextLine();
            try {
                inputNum = Integer.parseInt(userInput);
            }catch(Exception e){
                System.out.println("Please give valid input");
            }
            if(inputNum > 0 && inputNum < 4){
                break;
            }
            System.out.println("Your input must be one of the listed options");
        }

        return inputNum;
    }
    public void purchase(){
        int inputNum2 = 0;
        double moneyToAdd = 0;
        while(inputNum2 != 3) {
            System.out.println("What would you like to do?");
            System.out.println("(1) Feed money");
            System.out.println("(2) Select product");
            System.out.println("(3) Finish transaction");
            System.out.println();
            System.out.println("Current money provided: $" + (((int)(UIvm.getMachineBalance() * 100 + 0.5)) / 100d));
            String purchaseInput = input.nextLine();
            try {
                /////////add error message , write tests for vending machine
                inputNum2 = Integer.parseInt(purchaseInput);
                if (inputNum2 == 1) {
                    System.out.println("How much money would you like to add?");
                    String moneyToAddStr;
                    moneyToAddStr = input.nextLine();
                    try {
                        moneyToAdd += Double.parseDouble(moneyToAddStr);
                        UIvm.addMoney(moneyToAdd);
                    }catch(Exception b){
                        System.out.println("Did not enter a valid number, try again");
                    }
                }
                if(inputNum2 == 2){
                    UIvm.listInventory();
                    System.out.println("Please enter the code for the item you'd like to purchase");
                    String desiredItem = input.nextLine();
                    if(!UIvm.getInventory().containsKey(desiredItem)){
                        System.out.println("Sorry, you did not enter a valid item code. Please try again");
                    }else if(UIvm.getInventory().get(desiredItem).getQuantity() == 0){
                        System.out.println("Sorry, that item is out of stock. Please try again");
                    }else{
                        if(UIvm.machineBalance < UIvm.getInventory().get(desiredItem).getPrice()){
                            System.out.println("Sorry, you haven't paid enough to purchase that item");
                        }else {
                            UIvm.transaction(desiredItem);
                            String itemType = UIvm.inventory.get(desiredItem).getType();

                            if (itemType.equals("Candy")) {
                                System.out.println("Munch Munch, Yum!");
                            }
                            if (itemType.equals("Gum")) {
                                System.out.println("Chew Chew, Yum!");
                            }
                            if (itemType.equals("Chips")) {
                                System.out.println("Crunch Crunch, Yum!");
                            }
                            if (itemType.equals("Drink")) {
                                System.out.println("Glug Glug, Yum!");
                            }
                        }
                    }
                }
                if(inputNum2 == 3){
                    UIvm.change();
                }
            }catch(Exception e){
                System.out.println("Did not enter a valid number, try again");
            }

        }
    }

}
