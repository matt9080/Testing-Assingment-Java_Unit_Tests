package edu.uom.currencymanager.currencies;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String args[]) throws Exception {

        String path = "src/main/resources/currencies.txt";
        List<Currency> currencies = new ArrayList<Currency>();

        CurrencyDatabaseUpdated co = new CurrencyDatabaseUpdated(path,currencies);

    }
}
