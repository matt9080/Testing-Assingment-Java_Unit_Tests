package edu.uom.currencymanager.currencies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class UtilitiesTest {

    Utilities utilities;
    List<Currency> currencyList;
    HashMap<String,ExchangeRate> exchangeRateHashMap;

    final String CURRENCY_CODE = "QWE";
    final String CURRENCY_NAME = "NAME";
    final boolean CURRENCY_MAJOR_TRUE = true;
    final boolean CURRENCY_MAJOR_FALSE = false;


    @Before
    public void setUp(){
        utilities = new Utilities();
        currencyList = new ArrayList<Currency>();
        exchangeRateHashMap = new HashMap<String, ExchangeRate>();
    }

    @After
    public void tearDown(){
        utilities = null;
        currencyList = null;
        exchangeRateHashMap = null;
    }

    @Test
    public void currencyExistsSuccessTest(){
        Currency currency = new Currency(CURRENCY_CODE,CURRENCY_NAME,CURRENCY_MAJOR_TRUE);
        currencyList.add(currency);

        assertNotNull(utilities.currencyExists(currencyList,CURRENCY_CODE));
    }

    @Test
    public void currencyExistsNoCurrTest(){
        assertNull(utilities.currencyExists(currencyList,CURRENCY_CODE));
    }

    @Test
    public void getMajorCurrenciesSuccsessTest(){
        Currency currency_true = new Currency(CURRENCY_CODE,CURRENCY_NAME,CURRENCY_MAJOR_TRUE);
        Currency currency_false = new Currency(CURRENCY_CODE,CURRENCY_NAME,CURRENCY_MAJOR_FALSE);
        currencyList.add(currency_false);
        currencyList.add(currency_true);

        assertEquals(1,utilities.getMajorCurrencies(currencyList).size());
    }

    @Test
    public void getMajorCurrenciesNoMajorTest(){
        Currency currency_false = new Currency(CURRENCY_CODE,CURRENCY_NAME,CURRENCY_MAJOR_FALSE);
        currencyList.add(currency_false);

        assertEquals(0,utilities.getMajorCurrencies(currencyList).size());
    }

    @Test
    public void checkExchangeRateSuccsessTest(){
        Currency currency_true = new Currency(CURRENCY_CODE,CURRENCY_NAME,CURRENCY_MAJOR_TRUE);
        Currency currency_false = new Currency(CURRENCY_CODE,CURRENCY_NAME,CURRENCY_MAJOR_FALSE);

        ExchangeRate exchangeRate = new ExchangeRate(currency_true,currency_false,0.5);
        String key = CURRENCY_CODE + CURRENCY_CODE;
        exchangeRateHashMap.put(key,exchangeRate);

        assertTrue(utilities.checkExchangeRateExists(key,exchangeRateHashMap));
    }

    @Test
    public void checkExchangeRateNoExchangeRateTest(){
        String key = CURRENCY_CODE + CURRENCY_CODE;
        assertFalse(utilities.checkExchangeRateExists(key,exchangeRateHashMap));
    }

    @Test
    public void getExchangeRateSuccsessTest(){
        Currency currency_true = new Currency(CURRENCY_CODE,CURRENCY_NAME,CURRENCY_MAJOR_TRUE);
        Currency currency_false = new Currency(CURRENCY_CODE,CURRENCY_NAME,CURRENCY_MAJOR_FALSE);

        ExchangeRate exchangeRate = new ExchangeRate(currency_true,currency_false,0.5);
        String key = CURRENCY_CODE + CURRENCY_CODE;
        exchangeRateHashMap.put(key,exchangeRate);

        assertEquals(exchangeRate,utilities.getExchangeRate(key,exchangeRateHashMap));
    }

    @Test
    public void getExchangeRateNoExchangeRate(){
        String key = CURRENCY_CODE + CURRENCY_CODE;
        assertNull(utilities.getExchangeRate(key,exchangeRateHashMap));
    }
}
