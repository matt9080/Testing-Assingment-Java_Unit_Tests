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

    public CurrencyDatabaseUpdated() {

    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }


}

