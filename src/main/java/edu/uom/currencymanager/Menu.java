package edu.uom.currencymanager;

import edu.uom.currencymanager.currencies.Currency;
import edu.uom.currencymanager.currencies.CurrencyDatabase;
import edu.uom.currencymanager.currencies.ExchangeRate;
import edu.uom.currencymanager.currencies.ReaderWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    CurrencyDatabase currencyDatabase;
    Scanner sc = new Scanner(System.in);
    String path = "target" + File.separator + "classes" + File.separator + "currencies.txt";
    ReaderWriter readerWriter = new ReaderWriter(path);
    List<Currency> currencies = new ArrayList<Currency>();

    public Menu() throws Exception {
        currencyDatabase = new CurrencyDatabase(readerWriter);
        currencyDatabase.initilizeList();
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

    public void checkExchangeRateMenu(){
        System.out.print("\nEnter source currency code (e.g. EUR): ");
        String src = sc.next().toUpperCase();
        System.out.print("\nEnter destination currency code (e.g. GBP): ");
        String dst = sc.next().toUpperCase();

        checkExchangeRate(src,dst);
    }
    public void checkExchangeRate(String src,String dst){

        try {
            ExchangeRate rate = getExchangeRate(src, dst);
            System.out.println(rate.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ExchangeRate getExchangeRate(String sourceCurrency, String destinationCurrency) throws Exception {
        return currencyDatabase.getExchangeRate(sourceCurrency, destinationCurrency);
    }

    public void getAllCurrencies (){
        List<Currency> currencies = currencyDatabase.getCurrencies();
        System.out.println("\nAvailable Currencies\n--------------------");
        for (Currency currency : currencies) {
            System.out.println(currency.toString());
        }
    }

    public void getExchangeRates() throws Exception {
        List<ExchangeRate> exchangeRates = getMajorCurrencyRates();
        System.out.println("\nMajor Currency Exchange Rates\n-----------------------------");
        for (ExchangeRate rate : exchangeRates) {
            System.out.println(rate.toString());
        }
    }

    public List<ExchangeRate> getMajorCurrencyRates() throws Exception {

        List<ExchangeRate> exchangeRates = new ArrayList<ExchangeRate>();

        List<Currency> currencies = currencyDatabase.getMajorCurrencies();

        for (Currency src : currencies) {
            for (Currency dst : currencies) {
                if (src != dst) {
                    exchangeRates.add(currencyDatabase.getExchangeRate(src.code, dst.code));
                }
            }
        }
        return exchangeRates;
    }
}