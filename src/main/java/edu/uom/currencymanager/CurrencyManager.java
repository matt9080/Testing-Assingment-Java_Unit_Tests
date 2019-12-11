package edu.uom.currencymanager;

import edu.uom.currencymanager.currencies.Currency;
import edu.uom.currencymanager.currencies.CurrencyDatabase;
import edu.uom.currencymanager.currencies.CurrencyDatabaseUpdated;
import edu.uom.currencymanager.currencies.ExchangeRate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CurrencyManager {

    public static void main(String[] args) throws Exception {

//        CurrencyDatabase currencyDatabase = new CurrencyDatabase();

        String path = "src/main/resources/test.txt";
        List<Currency> currencies = new ArrayList<Currency>();

        CurrencyDatabaseUpdated co = new CurrencyDatabaseUpdated(path,currencies);

        System.out.println(co.addCurrency(new Currency("hdi","wqdd",true)));


//        Scanner sc = new Scanner(System.in);
//
//        boolean exit = false;
//
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
//            String code,name, major,result = null;
//
//            switch (choice) {
//                case 0:
//                    exit = true;
//                    break;
//                case 1:
//                    List<Currency> currencies = currencyDatabase.getCurrencies();
//                    System.out.println("\nAvailable Currencies\n--------------------");
//                    for (Currency currency : currencies) {
//                        System.out.println(currency.toString());
//                    }
//                    break;
//                case 2:
//                    List<ExchangeRate> exchangeRates = currencyDatabase.getMajorCurrencyRates();
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
//                        ExchangeRate rate = currencyDatabase.getExchangeRate(src, dst);
//                        System.out.println(rate.toString());
//                    } catch (Exception e) {
//                        System.out.println(e.getMessage());
//                    }
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
//                    break;
//
//            }
//
//            Thread.sleep(1000);
//        }
    }


}
