package edu.uom.currencymanager;
import edu.uom.currencymanager.currencies.Currency;
import edu.uom.currencymanager.currencies.CurrencyDatabase;
import edu.uom.currencymanager.currencies.CurrencyDatabaseUpdated;
import edu.uom.currencymanager.currencies.ExchangeRate;
import edu.uom.currencymanager.currencies.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CurrencyManager {

    public static void main(String[] args) throws Exception {
        String path = "target" + File.separator + "classes" + File.separator + "test.txt";

        CurrencyDatabaseUpdated curb = new CurrencyDatabaseUpdated(path);
        Scanner sc = new Scanner(System.in);

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
                    List<Currency> currencies = curb.getCurrencyList();
                    System.out.println("\nAvailable Currencies\n--------------------");
                    for (Currency currency : currencies) {
                        System.out.println(currency.toString());
                    }
                    break;

                case 4:
                    
            }
        }

    }
}
