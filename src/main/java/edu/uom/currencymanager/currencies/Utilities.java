package edu.uom.currencymanager.currencies;

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
}
