package edu.uom.currencymanager.currencies;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDatabaseUpdated {



    String path = "target" + File.separator + "classes" + File.separator + "test.txt";
    List<Currency> currencyList= new ArrayList<Currency>();
    Utilities utilities;
    ReaderWriter readerWriter;



    public CurrencyDatabaseUpdated(String path) {
        readerWriter = new ReaderWriter(path);
        currencyList = readerWriter.read();
    }


    public boolean checkCurrCode(String code){
        return code.length() == 3;
    }

    public boolean checkCurrName(String name){
        return name.length() >= 4;
    }

    public boolean checkCurrExists(String code){
        return utilities.currencyExists(currencyList, code) == null;
    }
    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void addCurrency(Currency currency){
        currencyList.add(currency);
        readerWriter.saveListToFile(currencyList);
    }

    public void removeCurrency(Currency currency){
        currencyList.remove(currency);
        readerWriter.saveListToFile(currencyList);
    }


}

