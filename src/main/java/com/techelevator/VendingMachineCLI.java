package com.techelevator;

// Vending Machine Command Line Interface application
public class VendingMachineCLI {

	public static void main(String[] args) {
		VendingMachine vm = new VendingMachine();
		vm.setInventory();
		UI ui = new UI(vm);

		int userInputNum1 = 0;
		while(userInputNum1 != 3) {
			userInputNum1 = ui.mainMenu();
			if (userInputNum1 == 1) {
				vm.listInventory();
			}
		}
	}
}
