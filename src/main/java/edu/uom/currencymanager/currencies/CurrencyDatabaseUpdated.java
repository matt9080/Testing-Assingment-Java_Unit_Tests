package edu.uom.currencymanager.currencies;

<<<<<<< Updated upstream
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;


public class CurrencyDatabaseUpdated {
    String path;
    List<Currency> currencyList;
    ReaderWriter readerWriter;

    public CurrencyDatabaseUpdated(String path, List<Currency> currencyList){
        this.path = path;
        this.currencyList = currencyList;
        readerWriter = new ReaderWriter(path);
    }

    public boolean currencyExists(String code) {
        return getCurrencyByCode(code) != null;
    }

    public Currency getCurrencyByCode(String code) {

        for (Currency currency : currencyList) {
            if (currency.code.equalsIgnoreCase(code)) {
                return currency;
            }
        }

        return null;
    }



    public String addCurrency(Currency currency) throws Exception {

        //Save to list
        if (currency.code.length() != 3) {
            return "A currency code should have 3 characters.";
        }
        if (currency.name.length() < 4) {
            return "A currency's name should be at least 4 characters long.";
        }
        if (currencyExists(currency.code)) {
            return "The currency " + currency.code + " already exists.";
        }

        currencyList.add(currency);
        readerWriter.writeToFile(currencyList, new BufferedWriter(new FileWriter(path)));
        return "Currency added";
    }
}

=======
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDatabaseUpdated {

    String path = "target" + File.separator + "classes" + File.separator + "test.txt";
    List<Currency> currencyList= new ArrayList<Currency>();
    Utilities utilities;
    ReaderWriter readerWriter;

    public CurrencyDatabaseUpdated()
    {
//        try{
//            utilities = new Utilities();
//            readerWriter = new ReaderWriter(new BufferedReader(new FileReader(path)), new BufferedWriter(new FileWriter(path)));
//        }catch(Exception e ){
//            System.out.println(e.getMessage());
//        }
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

//    public void loadCurrenciesFromFile(){
//        currencyList = readerWriter.readFromFIle();
//    }
}
>>>>>>> Stashed changes
