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
        ReaderWriter readerWriter = new ReaderWriter(path);
        List<Currency> currencies = readerWriter.read();

        for(int i = 0; i < currencies.size(); i++){
            System.out.println(currencies.get(i));
        }
        currencies.add(new Currency("qwe","name",true));

        readerWriter.saveListToFile(currencies);

        List<Currency> test = readerWriter.read();

    }
}
