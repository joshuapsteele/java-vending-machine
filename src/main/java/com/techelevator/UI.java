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

    }
    public void finishTransaction(){

    }
}
