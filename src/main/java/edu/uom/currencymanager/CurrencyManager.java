package edu.uom.currencymanager;

<<<<<<< Updated upstream
import edu.uom.currencymanager.currencies.Currency;
import edu.uom.currencymanager.currencies.CurrencyDatabase;
import edu.uom.currencymanager.currencies.CurrencyDatabaseUpdated;
import edu.uom.currencymanager.currencies.ExchangeRate;
=======
import edu.uom.currencymanager.currencies.*;
>>>>>>> Stashed changes

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CurrencyManager {

    public static void main(String[] args) throws Exception {
        String path = "target" + File.separator + "classes" + File.separator + "test.txt";
        ReaderWriter readerWriter = new ReaderWriter(path);
        List<Currency> currencies = readerWriter.read();

<<<<<<< Updated upstream
//        CurrencyDatabase currencyDatabase = new CurrencyDatabase();

        String path = "src/main/resources/test.txt";
        List<Currency> currencies = new ArrayList<Currency>();

        CurrencyDatabaseUpdated co = new CurrencyDatabaseUpdated(path,currencies);

        System.out.println(co.addCurrency(new Currency("hdi","wqdd",true)));


//        Scanner sc = new Scanner(System.in);
//
//        boolean exit = false;
//
=======
        for(Currency currency : currencies){
            System.out.println(currency.code);
        }
        CurrencyManager manager = new CurrencyManager();
        CurrencyDatabaseUpdated co = new CurrencyDatabaseUpdated();
        Scanner sc = new Scanner(System.in);


//        boolean exit = false;

>>>>>>> Stashed changes
//        while (!exit) {
//            System.out.println("\nMain Menu\n---------\n");
//
//            System.out.println("1. List currencies");
//            System.out.println("2. List exchange rates between major currencies");
//            System.out.println("3. Check exchange rate");
//            System.out.println("4. Add currency");
//            System.out.println("5. Delete currency");
//            System.out.println("0. Quit");
//
//            System.out.print("\nEnter your choice: ");
//
//            int choice = sc.nextInt();
<<<<<<< Updated upstream
//            String code,name, major,result = null;
=======
>>>>>>> Stashed changes
//
//            switch (choice) {
//                case 0:
//                    exit = true;
//                    break;
//                case 1:
<<<<<<< Updated upstream
//                    List<Currency> currencies = currencyDatabase.getCurrencies();
//                    System.out.println("\nAvailable Currencies\n--------------------");
//                    for (Currency currency : currencies) {
=======
//                    List<Currency> currencies = manager.currencyDatabase.getCurrencies();
//                    System.out.println("\nAvailable Currencies\n--------------------");
//                    for (Currency currency : co.getCurrencyList()) {
>>>>>>> Stashed changes
//                        System.out.println(currency.toString());
//                    }
//                    break;
//                case 2:
<<<<<<< Updated upstream
//                    List<ExchangeRate> exchangeRates = currencyDatabase.getMajorCurrencyRates();
=======
//                    List<ExchangeRate> exchangeRates = manager.getMajorCurrencyRates();
>>>>>>> Stashed changes
//                    System.out.println("\nMajor Currency Exchange Rates\n-----------------------------");
//                    for (ExchangeRate rate : exchangeRates) {
//                        System.out.println(rate.toString());
//                    }
//                    break;
//                case 3:
//                    System.out.print("\nEnter source currency code (e.g. EUR): ");
//                    String src = sc.next().toUpperCase();
//                    System.out.print("\nEnter destination currency code (e.g. GBP): ");
//                    String dst = sc.next().toUpperCase();
//                    try {
<<<<<<< Updated upstream
//                        ExchangeRate rate = currencyDatabase.getExchangeRate(src, dst);
=======
//                        ExchangeRate rate = manager.getExchangeRate(src, dst);
>>>>>>> Stashed changes
//                        System.out.println(rate.toString());
//                    } catch (Exception e) {
//                        System.out.println(e.getMessage());
//                    }
<<<<<<< Updated upstream
//                    break;
//                case 4:
//                    do {
//                        System.out.print("\nEnter the currency code: ");
//                        code = sc.next().toUpperCase();
//                        System.out.print("Enter currency name: ");
//                        name = sc.next();
//                        name += sc.nextLine();
//
//                        major = "\n";
//                        while (!(major.equalsIgnoreCase("y") || major.equalsIgnoreCase("n"))) {
//                            System.out.println("Is this a major currency? [y/n]");
//                            major = sc.next();
//                        }
//
//                        try {
//                            result = currencyDatabase.addCurrency(code, name, major.equalsIgnoreCase("y"));
//                            System.out.println(result);
//
//                        } catch (Exception e) {
//                            System.err.println(e.getMessage());
//                        }
//                    }while(!result.equals("Currency added"));
//                    break;
//                case 5:
//                    do{
//                    System.out.print("\nEnter the currency code: ");
//                    code = sc.next().toUpperCase();
//                    try {
//                        result = currencyDatabase.deleteCurrency(code);
//                        System.out.println(result);
//                    } catch (Exception e) {
//                        System.err.println(e.getMessage());
//                    }
//                    }while(result.contains("Currency does not exist"));
=======
//                case 4:
//                    System.out.print("\nEnter the currency code: ");
//                    String code = sc.next().toUpperCase();
//                    System.out.print("Enter currency name: ");
//                    String name = sc.next();
//                    name += sc.nextLine();
//
//                    String major = "\n";
//                    while (!(major.equalsIgnoreCase("y") || major.equalsIgnoreCase("n"))) {
//                        System.out.println("Is this a major currency? [y/n]");
//                        major = sc.next();
//                    }
//
//                    try {
//                        manager.addCurrency(code, name, major.equalsIgnoreCase("y"));
//                    } catch (Exception e) {
//                        System.err.println(e.getMessage());
//                    }
//                    break;
//                case 5:
//                    System.out.print("\nEnter the currency code: ");
//                    code = sc.next().toUpperCase();
//                    try {
//                        manager.deleteCurrencyWithCode(code);
//                    } catch (Exception e) {
//                        System.err.println(e.getMessage());
//                    }
>>>>>>> Stashed changes
//                    break;
//
//            }
//
//            Thread.sleep(1000);
//        }
    }

<<<<<<< Updated upstream
=======
//    public List<ExchangeRate> getMajorCurrencyRates() throws Exception {
//
//        List<ExchangeRate> exchangeRates = new ArrayList<ExchangeRate>();
//
//        List<Currency> currencies = currencyDatabase.getMajorCurrencies();
//
//        for (Currency src : currencies) {
//            for (Currency dst : currencies) {
//                if (src != dst) {
//                    exchangeRates.add(currencyDatabase.getExchangeRate(src.code, dst.code));
//                }
//            }
//        }
//        return exchangeRates;
//    }
//
//    public ExchangeRate getExchangeRate(String sourceCurrency, String destinationCurrency) throws Exception {
//        return currencyDatabase.getExchangeRate(sourceCurrency, destinationCurrency);
//    }
//
//    public void addCurrency(String code, String name, boolean major) throws Exception {
//
//        //Check format of code
//        if (code.length() != 3) {
//            throw new Exception("A currency code should have 3 characters.");
//        }
//
//        //Check minimum length of name
//        if (name.length() < 4) {
//            throw new Exception("A currency's name should be at least 4 characters long.");
//        }
//
//        //Check if currency already exists
//        if (currencyDatabase.currencyExists(code)) {
//            throw new Exception("The currency " + code + " already exists.");
//        }
//
//        //Add currency to database
//        currencyDatabase.addCurrency(new Currency(code,name,major));
//
//    }
//
//    public void deleteCurrencyWithCode(String code) throws Exception {
//
//        if (!currencyDatabase.currencyExists(code)) {
//            throw new Exception("Currency does not exist: " + code);
//        }
//
//        currencyDatabase.deleteCurrency(code);
//
//    }
>>>>>>> Stashed changes

}
