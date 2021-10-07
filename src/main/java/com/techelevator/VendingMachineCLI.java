package com.techelevator;

import java.io.IOException;

// Vending Machine Command Line Interface application
public class VendingMachineCLI {

	public static void main(String[] args) {
		VendingMachine vm;

		try {
			vm = new VendingMachine();
		} catch (IOException e) {
			System.out.println("Error while trying to write the machine log. Please try again.");
			return;
		}

		UI ui = new UI(vm);

		int userInputNum1 = 0;
		while(userInputNum1 != 3) {
			userInputNum1 = ui.mainMenu();
			if (userInputNum1 == 1) {
				vm.listInventory();
			}
			if (userInputNum1 == 2){
				ui.purchase();
			}
		}
	}
}
