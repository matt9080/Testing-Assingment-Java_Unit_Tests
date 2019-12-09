package edu.uom.currencymanager.currencies;

import static junit.framework.TestCase.assertTrue;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExchangeRateTestr {




    final String CURR_CODE = "TES";
    final String CURR_NAME = "TESTING";
    final boolean CURR_TRUE = true;
    final String CURR_CODE_2 = "YEE";
    final String CURR_NAME_2 = "YEETING";
    final boolean CURR_FALSE = false;
    final double RATE = 0.42;

    @Test
    public void toStringSuccessTest() {
        Currency x = new Currency(CURR_CODE,CURR_NAME, CURR_TRUE);
        Currency y = new Currency(CURR_CODE_2,CURR_NAME_2, CURR_TRUE);
        ExchangeRate exchangeRate = new ExchangeRate(x,y,RATE);
        assertEquals(CURR_CODE +" 1 = "+ CURR_CODE_2 + " " + RATE, exchangeRate.toString());
    }

    @Test
    public void exchangeRateCreationTest() {
        Currency newCurrency = new Currency(CURR_CODE,CURR_NAME,CURR_TRUE);
        Currency newCurrency2 = new Currency(CURR_CODE_2,CURR_NAME_2,CURR_FALSE);
        long currTime = System.currentTimeMillis();
        ExchangeRate exchangeRate = new ExchangeRate(newCurrency,newCurrency2,RATE);

        //assertEquals(System.currentTimeMillis(),exchangeRate.timeLastChecked);
        assertTrue((exchangeRate.timeLastChecked == System.currentTimeMillis())
            && (exchangeRate.rate == RATE)
            && (exchangeRate.sourceCurrency.code.equals(CURR_CODE)
            && (exchangeRate.destinationCurrency.code.equals(CURR_CODE_2))));
    }
}
