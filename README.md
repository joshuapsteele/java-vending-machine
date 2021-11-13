## Object-Oriented Programming Capstone - Vending Machine Software

As a way to practice what I've learned about Java, Unit Testing, Git, File I/O, 
and Command Line Interface programs, I've developed this basic vending machine.

In addition to meeting the basic requirements for this coding bootcamp project (see below), I 
also went above and beyond to complete the bonus challenge: implementing a cummulative 
Sales Report for the vending machine that keeps track of total sales. Enter "4" in the
Main Menu to generate a sales report. Upon exiting the program, it will show up in the 
sales report directory.

## Here's what the vending machine does

1. The vending machine dispenses beverages, candy, chips, and gum.
   - Each vending machine item has a Name and a Price.
2. A main menu displays when the software runs, presenting the following options:
    > ```
    > (1) Display Vending Machine Items
    > (2) Purchase
    > (3) Exit
    > ```
3. The vending machine inventory is stocked via a .csv input file when the vending machine
starts.
4. The vending machine is automatically restocked each time the application runs.
5. When the customer selects "(1) Display Vending Machine Items", they're presented
with a list of all items in the vending machine with its quantity remaining:
    - Each vending machine product has a slot identifier and a purchase price.
    - Each slot in the vending machine has enough room for 5 of that product.
    - Every product is initially stocked to the maximum amount.
    - A product that has run out indicates that it is SOLD OUT.
6. When the customer selects "(2) Purchase", they are guided through the purchasing
process menu:
    >```
    >(1) Feed Money
    >(2) Select Product
    >(3) Finish Transaction
    >
    > Current Money Provided: $2.00
    >```
7. The purchase process flow is as follows:
    1. Selecting "(1) Feed Money" allows the customer to repeatedly feed money into the
    machine in valid, whole dollar amounts—for example, $1, $2, $5, or $10.
        - The "Current Money Provided" indicates how much money the customer
        has fed into the machine.
    2. Selecting "(2) Select Product" allows the customer to select a product to
    purchase.
        - Shows the list of products available and allow the customer to enter
        a code to select an item.
        - If the product code does not exist, the customer is informed and returned
        to the Purchase menu.
        - If a product is sold out, the customer is informed and returned to the
        Purchase menu.
        - If a valid product is selected, it is dispensed to the customer.
        - Dispensing an item prints the item name, cost, and the money
        remaining. Dispensing also returns a message:
          - All chip items print "Crunch Crunch, Yum!"
          - All candy items print "Munch Munch, Yum!"
          - All drink items print "Glug Glug, Yum!"
          - All gum items print "Chew Chew, Yum!"
        - After the product is dispensed, the machine updates its balance
        accordingly and returns the customer to the Purchase menu.
    3. Selecting "(3) Finish Transaction" allows the customer to complete the
    transaction and receive any remaining change.
        - The customer's money is returned using nickels, dimes, and quarters
        (using the smallest amount of coins possible).
        - The machine's current balance is then updated to $0 remaining.
    4. After completing their purchase, the user is returned to the "Main" menu to
    continue using the vending machine.
8. All purchases are audited in a "Log.txt" file to prevent theft from the vending machine.
9. Unit tests (JUnit) ensure that the code is working correctly.
10. BONUS CHALLENGE - Cummulative Sales Report
    - Provide a "Hidden" menu option on the main menu ("4") that writes to a sales
    report that shows the total sales since the machine was started. The name of the
    file must include the date and time so each sales report is uniquely named.
    - An example of the output format is provided below.

## How I would improve this vending machine

If I were to spend more time improving this project, I would:

- Implement BigDecimal instead of doubles throughout.
- Change the Item Class to an Interface that is then implemented by Chip, Candy, Drink, Gum, etc.
- Store vending machine data in a databse, instead of in .txt files.
