package edu.uom.currencymanager;

import edu.uom.currencymanager.currencies.Currency;
import edu.uom.currencymanager.currencies.CurrencyDatabase;

import java.util.Scanner;

public class Menu {

    CurrencyDatabase currencyDatabase;
    Scanner sc = new Scanner(System.in);

    public Menu() throws Exception {
        currencyDatabase = new CurrencyDatabase();

    }

    public void setCurrencyDatabase(CurrencyDatabase currencyDatabase) {
        this.currencyDatabase = currencyDatabase;
    }

    public void menu() throws Exception {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nMain Menu\n---------\n");

            System.out.println("1. List currencies");
            System.out.println("2. List exchange rates between major currencies");
            System.out.println("3. Check exchange rate");
            System.out.println("4. Add currency");
            System.out.println("5. Delete currency");
            System.out.println("0. Quit");

            System.out.print("\nEnter your choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    exit = true;
                    break;

                case 1:
                    System.out.println("Dispaying all Currencies");
                    getAllCurrencies();
                    break;

                case 2:
                    System.out.println("Displaying exchange Rates");
                    getExchangeRates();
                    break;

                case 3:
                    System.out.println("Check Exchange Rate");
                    checkExchangeRateMenu();
                    break;
                case 4:
                    System.out.println("Add Currency");
                    addCurrency_menu();
                    break;

                case 5:
                    System.out.println("Deleting Currency");
                    deleteCurrency_menu();
                    break;
            }
        }
    }

    public void deleteCurrency_menu(){
        System.out.print("\nEnter the currency code: ");
        String code = sc.next().toUpperCase();
        try {
            deleteCurrency(code);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteCurrency(String code) throws Exception {
        if (!currencyDatabase.currencyExists(code)) {
            throw new Exception("Currency does not exist: " + code);
        }

        currencyDatabase.deleteCurrency(code);
    }

    public void addCurrency_menu(){
        System.out.print("\nEnter the currency code: ");
        String code = sc.next().toUpperCase();
        System.out.print("Enter currency name: ");
        String name = sc.next();
        name += sc.nextLine();

        String major = "\n";
        while (!(major.equalsIgnoreCase("y") || major.equalsIgnoreCase("n"))) {
            System.out.println("Is this a major currency? [y/n]");
            major = sc.next();
        }

        try {
            addCurrency(code, name, major.equalsIgnoreCase("y"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public void addCurrency(String code, String name, boolean major) throws Exception {

        //Check format of code
        if (code.length() != 3) {
            throw new Exception("A currency code should have 3 characters.");
        }

        //Check minimum length of name
        if (name.length() < 4) {
            throw new Exception("A currency's name should be at least 4 characters long.");
        }

        //Check if currency already exists
        if (currencyDatabase.currencyExists(code)) {
            throw new Exception("The currency " + code + " already exists.");
        }
        //Add currency to database
        currencyDatabase.addCurrency(new Currency(code,name,major));

    }
}