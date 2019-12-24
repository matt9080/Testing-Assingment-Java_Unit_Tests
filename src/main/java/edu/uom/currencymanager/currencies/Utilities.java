package edu.uom.currencymanager.currencies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Utilities {

    public Currency currencyExists(List<Currency> currencyList, String codecurrency){
        for (Currency currency : currencyList) {
            if (currency.code.equalsIgnoreCase(codecurrency)) {
                return currency;
            }
        }
        return null;
    }

    public List<Currency> getMajorCurrencies (List<Currency> currencies){
        List<Currency> result = new ArrayList<Currency>();
        for (Currency currency : currencies) {
            if (currency.major) {
                result.add(currency);
            }
        }

        return result;
    }
    public boolean checkExchangeRateExists(String key, HashMap<String,ExchangeRate> exchangeRateHashMap){
        return exchangeRateHashMap.containsKey(key);
    }

    public ExchangeRate getExchangeRate(String key, HashMap<String,ExchangeRate> exchangeRateHashMap) {
        return exchangeRateHashMap.get(key);
    }
}
