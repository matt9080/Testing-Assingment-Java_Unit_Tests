package edu.uom.currencymanager.currencies;

import edu.uom.currencymanager.currencyserver.CurrencyServer;
import edu.uom.currencymanager.currencyserver.DefaultCurrencyServer;

import java.io.*;
import java.util.*;

public class CurrencyDatabase implements CurrencyDatabaseInterface {

    CurrencyServer currencyServer = new DefaultCurrencyServer();
    List<Currency> currencies = new ArrayList<Currency>();
    HashMap<String, ExchangeRate> exchangeRates = new HashMap<String, ExchangeRate>();

    String path = "target" + File.separator + "classes" + File.separator + "currencies.txt";
    ReaderWriter readerWriter;

    public CurrencyDatabase(ReaderWriter readerWriter) throws Exception {
        this.readerWriter = readerWriter;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
    public void initilizeList(){
        currencies = readerWriter.read();
    }
    public void setPath(String path) {
        this.path = path;
    }

    public void setReaderWriter(ReaderWriter readerWriter) {
        this.readerWriter = readerWriter;
    }

    public Currency getCurrencyByCode(String code) {

        for (Currency currency : currencies) {
            if (currency.code.equalsIgnoreCase(code)) {
                return currency;
            }
        }

        return null;
    }

    public boolean currencyExists(String code) {
        return getCurrencyByCode(code) != null;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public List<Currency> getMajorCurrencies() {
        List<Currency> result = new ArrayList<Currency>();

        for (Currency currency : currencies) {
            if (currency.major) {
                result.add(currency);
            }
        }

        return result;
    }

    public void setCurrencyServer(CurrencyServer currencyServer) {
        this.currencyServer = currencyServer;
    }

    public ExchangeRate getExchangeRate(String sourceCurrencyCode, String destinationCurrencyCode) throws  Exception {
        long FIVE_MINUTES_IN_MILLIS = 300000;  //5*60*100

        ExchangeRate result = null;

        Currency sourceCurrency = getCurrencyByCode(sourceCurrencyCode);
        if (sourceCurrency == null) {
            throw new Exception("Unkown Source currency: " + sourceCurrencyCode);
        }

        Currency destinationCurrency = getCurrencyByCode(destinationCurrencyCode);
        if (destinationCurrency == null) {
            throw new Exception("Unkown Destination currency: " + destinationCurrencyCode);
        }

        //Check if exchange rate exists in database
        String key = sourceCurrencyCode + destinationCurrencyCode;
        if (exchangeRates.containsKey(key)) {
            result = exchangeRates.get(key);
            if (System.currentTimeMillis() - result.timeLastChecked > FIVE_MINUTES_IN_MILLIS) {
                result = null;
            }
        }

        if (result == null) {
            currencyServer.setSeed(new Random().nextInt());
            double rate = currencyServer.getExchangeRate(sourceCurrencyCode, destinationCurrencyCode);
            result = new ExchangeRate(sourceCurrency,destinationCurrency, rate);

            //Cache exchange rate
            exchangeRates.put(key, result);

            //Cache inverse exchange rate
            String inverseKey = destinationCurrencyCode+sourceCurrencyCode;
            exchangeRates.put(inverseKey, new ExchangeRate(destinationCurrency, sourceCurrency, 1/rate));
        }

        return result;
    }

    public void addCurrency(Currency currency) throws Exception {

        //Save to list
        currencies.add(currency);

        //Persist
        readerWriter.saveListToFile(currencies);
    }

    public void deleteCurrency(String code) throws Exception {

        //Save to list
        currencies.remove(getCurrencyByCode(code));

        //Persist
        readerWriter.saveListToFile(currencies);
    }
}
