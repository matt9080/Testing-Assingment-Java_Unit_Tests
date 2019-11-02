package edu.uom.currencymanager.currencies;

import edu.uom.currencymanager.currencyserver.CurrencyServer;
import edu.uom.currencymanager.currencyserver.DefaultCurrencyServer;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class CurrencyDatabaseTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    final String CURR_CODE = "TES";
    final String CURR_NAME = "TESTING";
    final String CURR_CODE_2 = "YEE";
    final String CURR_NAME_2 = "YEETING";
    CurrencyDatabase currDB;
    Currency currency;

    @Before
    public void setup() throws Exception {  // Setup which initializes required classes, generates a fresh list and populates said list with a fake currency.
        currDB = new CurrencyDatabase();
        currDB.currencies = new LinkedList<Currency>();
        currDB.exchangeRates = new HashMap<String, ExchangeRate>();
        Currency currency_2 = new Currency(CURR_CODE_2,CURR_NAME_2,true);
        currDB.currencies.add(currency_2);
    }

    @After
    public void tearDown(){ // Nullifies the currDB class.
        currDB = null;
    }

    @Test
    public void addCurrencyTest() { // Test to add a currency
        currency = new Currency(CURR_CODE, CURR_NAME, true);

        int currentSizeOfList = currDB.currencies.size();
        currDB.currencies.add(currency);

        assertEquals(currentSizeOfList + 1, currDB.currencies.size());
    }

//    @Test
//    public void AddCurrencyPreExistingTest() {
//        // Test to add currency already in the list.
//
//    }

    @Test
    public void getCurrencyCodeSuccessTest() {  // Test to check if a correct currency code is returned.
        currency = new Currency(CURR_CODE, CURR_NAME, true);
        currDB.currencies.add(currency);
        Currency currencyTest = currDB.getCurrencyByCode(currency.code);

        assertEquals(currency.code, currencyTest.code);

    }

    @Test
    public void getCurrencyCodeNullTest() { // Test to check if currency doesnt exist method returns null.
        assertNull(currDB.getCurrencyByCode(CURR_CODE));
    }

    @Test
    public void currencyExistsSuccess() {   // Test to check if currency exists, mocks getCurrencyByCode method.
        CurrencyDatabase currDB = spy(CurrencyDatabase.class);
        doReturn(currency = new Currency(CURR_CODE,CURR_NAME,true)).when(currDB).getCurrencyByCode(CURR_CODE);
        assertTrue(currDB.currencyExists(CURR_CODE));
    }

    @Test
    public void getCurrenciesListUsedTest(){
        //To Test if list is returning properly
        List<Currency> cloneList = currDB.getCurrencies();
        assertEquals(cloneList,currDB.getCurrencies());
    }

    @Test
    public void getMajorCurrenciesSuccessTest(){    // Problem expected list returns with space behind object
        Currency nonMajorCurrency = new Currency(CURR_CODE,CURR_NAME,false);
        Currency MajorCurrency = new Currency(CURR_CODE_2,CURR_NAME_2,true);

        currDB.currencies.add(nonMajorCurrency);

        List<Currency> actualList= currDB.getMajorCurrencies();
        assertTrue(actualList.size() == 1 && actualList.get(0).major);

    }

    @Test
    public void getMajorCurrenciesNoMajorTest(){
        currDB.currencies.clear();
        Currency nonMajorCurrency = new Currency(CURR_CODE,CURR_NAME,false);
        currDB.currencies.add(nonMajorCurrency);
        assertTrue(currDB.getMajorCurrencies().isEmpty());
    }

    @Test
    public void deleteCurrencySuccessTest() throws Exception {

        CurrencyDatabase currDB = spy(CurrencyDatabase.class);
        Currency currency_2 = new Currency(CURR_CODE_2,CURR_NAME_2,true);
        Currency currency = new Currency(CURR_CODE,CURR_NAME,true);
        currDB.currencies.add(currency_2);
        currDB.currencies.add(currency);
        doReturn(currency_2).when(currDB).getCurrencyByCode(currency_2.code);

        int sizeBeforeDelete = currDB.currencies.size();
        currDB.deleteCurrency(currency_2.code);

        assertEquals(sizeBeforeDelete -1, currDB.currencies.size());

    }

//    @Test(expected = IllegalArgumentException.class)
//    public void deleteCurrencyThrowsExceptionTest() throws Exception {
//        CurrencyDatabase currDB = spy(CurrencyDatabase.class);
//        Currency currency_2 = new Currency(CURR_CODE_2,CURR_NAME_2,true);
//
//        doReturn(null).when(currDB).getCurrencyByCode(currency_2.code);
//        currDB.deleteCurrency(currency_2.code);
//
//    }

//    @Test
//    public void exchangeRateSuccessTest() throws Exception {
//        Currency currency_1 = new Currency(CURR_CODE,CURR_NAME,false);
//        Currency currency_2 = new Currency(CURR_CODE_2,CURR_NAME_2,true);
//        CurrencyDatabase currDB = spy(CurrencyDatabase.class);
//        DefaultCurrencyServer currencyServer = mock(DefaultCurrencyServer.class);
//
//        doReturn(currency_1).when(currDB).getCurrencyByCode(currency_1.code);
//        doReturn(currency_2).when(currDB).getCurrencyByCode(currency_2.code);
//        ExchangeRate exchangeRate = new ExchangeRate(currency_1,currency_2,1.5);
//
////        stub(currencyServer.getExchangeRate(currency_1.code,currency_2.code)).toReturn(1.5);
////        when(currencyServer.getExchangeRate(currency_1.code,currency_2.code)).thenReturn(1.5);
//        doReturn(1.5).when(currencyServer.getExchangeRate(currency_1.code,currency_2.code));
//
//
//        double xz = currencyServer.getExchangeRate(currency_1.code, currency_2.code);
//        ExchangeRate axeq = currDB.getExchangeRate(currency_1.code, currency_2.code);
//
//        assertEquals(exchangeRate,axeq);
//
//    }




}
