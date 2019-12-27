package edu.uom.currencymanager;

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