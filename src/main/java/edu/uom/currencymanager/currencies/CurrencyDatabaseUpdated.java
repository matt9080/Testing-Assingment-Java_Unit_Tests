package edu.uom.currencymanager.currencies;

import edu.uom.currencymanager.currencyserver.CurrencyServer;
import edu.uom.currencymanager.currencyserver.DefaultCurrencyServer;

import java.util.HashMap;
import java.util.List;
import java.io.*;
import java.util.ArrayList;

public class CurrencyDatabaseUpdated {



    String path = "target" + File.separator + "classes" + File.separator + "test.txt";
    List<Currency> currencyList= new ArrayList<Currency>();
    Utilities utilities;
    ReaderWriter readerWriter;
    HashMap<String, ExchangeRate> exchangeRates = new HashMap<String, ExchangeRate>();
    CurrencyServer currencyServer;



    public CurrencyDatabaseUpdated(String path) {
        readerWriter = new ReaderWriter(path);
        currencyList = readerWriter.read();
        currencyServer = new DefaultCurrencyServer();

    }


    public boolean checkCurrCode(String code){
        return code.length() == 3;
    }

    public boolean checkCurrName(String name){
        return name.length() >= 4;
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public List<Currency> getMajorCurrencies(){
        return utilities.getMajorCurrencies(currencyList);
    }

    public void addCurrency(Currency currency){
        currencyList.add(currency);
        readerWriter.saveListToFile(currencyList);
    }

    public void removeCurrency(Currency currency){
        currencyList.remove(currency);
        readerWriter.saveListToFile(currencyList);
    }

    public boolean checkCurrencyNull(Currency currency) {
        return currency == null; // RETURN TRUE IF CURRENCY IS NULL
    }

    public ExchangeRate getExchangeRate(String sourceCurrencyCode, String destinationCurrencyCode) throws  Exception {

        try{
            long FIVE_MINUTES_IN_MILLIS = 300000;  //5*60*100
            ExchangeRate result = null;

            Currency sourceCurrency = utilities.currencyExists(currencyList,sourceCurrencyCode);
            if(checkCurrencyNull(sourceCurrency)) throw new Exception("Invalid Currency Code - Currency does not exist");

            Currency destinationCurrency = utilities.currencyExists(currencyList,destinationCurrencyCode);
            if(checkCurrencyNull(destinationCurrency)) throw new Exception("Invalid Currency Code - Currency does not exist");

            String key = sourceCurrencyCode + destinationCurrencyCode;
            if(utilities.checkExchangeRateExists(key,exchangeRates)){
                result = utilities.getExchangeRate(key,exchangeRates);
                if (System.currentTimeMillis() - result.timeLastChecked > FIVE_MINUTES_IN_MILLIS) {
                    result = null;
                }
            }else throw new Exception("Exchange Rate does not exist in Database");

            if (result == null) {
                double rate = currencyServer.getExchangeRate(sourceCurrencyCode,destinationCurrencyCode);
                result = new ExchangeRate(sourceCurrency,destinationCurrency,rate);

                exchangeRates.put(key,result);

                String inverseKey = destinationCurrencyCode + sourceCurrencyCode;
                exchangeRates.put(inverseKey, new ExchangeRate(destinationCurrency,sourceCurrency, 1/rate));
            }

            return result;


        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }


    }

