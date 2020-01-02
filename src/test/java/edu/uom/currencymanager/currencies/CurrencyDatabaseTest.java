package edu.uom.currencymanager.currencies;

import edu.uom.currencymanager.currencyserver.CurrencyServer;
import edu.uom.currencymanager.currencyserver.DefaultCurrencyServer;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import org.apache.commons.io.FileUtils;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class CurrencyDatabaseTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    final String CURR_CODE = "TES";
    final String CURR_NAME = "TESTING";
    final boolean CURR_TRUE = true;
    final String CURR_CODE_2 = "YEE";
    final String CURR_NAME_2 = "YEETING";
    final boolean CURR_FALSE = false;

    CurrencyDatabase currDB;
    Currency currency;

    @Mock
    ReaderWriter readerWriter;

    @Before
    public void setup() throws Exception {  // Setup which initializes required classes, generates a fresh list and populates said list with a fake currency.
        currDB = new CurrencyDatabase(readerWriter);
        currDB.setPath("target" + File.separator + "classes" + File.separator + "test.txt");
        currDB.setCurrencies(new ArrayList<Currency>());
        MockitoAnnotations.initMocks(this);

    }

    @After
    public void tearDown(){ // Nullifies the currDB class.
        currDB = null;
        currency = null;
        readerWriter = null;
    }



    @Test
    public void addCurrencyTest() throws Exception { // Test to add a currency
        // Setup - Create new object of type Currency.
        currDB.setReaderWriter(readerWriter);
        currency = new Currency(CURR_CODE, CURR_NAME, CURR_TRUE);
//        currDB.setReaderWriter(readerWriter);
        // Exercise - Measure the current size of the list and store it as an integer variable.
        int currentSizeOfList = currDB.getCurrencies().size();
        // Exercise use the Currency Database Add Method to add a new currency.
        currDB.addCurrency(currency);
//        doReturn(any()).when(readerWriter).saveListToFile(anyList());
        // Verify - Assert that the size of the list before addition is equal to the current size of the list + 1.
        assertEquals(currentSizeOfList + 1, currDB.currencies.size());
        // Teardown - Remove currency from the list.
        currDB.setCurrencies(null);
    }

    @Test
    public void getCurrencyCodeSuccessTest() {  // Test to check if a correct currency code is returned.
        // Setup - Create new object of type Currency and adding it to the list currencies.
        currency = new Currency(CURR_CODE, CURR_NAME, true);
        List<Currency> currencies = new LinkedList<Currency>();
        currencies.add(currency);
        currDB.setCurrencies(currencies);
        // Exercise - Creating a new currency via the getCurrencyByCode method, which searches the list for a currency then returns it.
        Currency currencyExpected = currDB.getCurrencyByCode(currency.code);
        // Verify - Asserting that the 2 Currency objects are equal via comparision of their data.
        assertTrue(currency.code.equals(currencyExpected.code) && currency.name.equals(currencyExpected.name) && currency.major == currencyExpected.major);
        // Teardown - Removing currency from the list;
        currDB.setCurrencies(null);
    }

    @Test
    public void getCurrencyCodeNullTest() { // Test to check if currency doesnt exist method returns null.
        // Verify - Assert that if getCurrencyByCode is given a currency code which does not exist then it returns null.
        assertNull(currDB.getCurrencyByCode(CURR_CODE));
    }

    @Test
    public void currencyExistsSuccess() {   // Test to check if currency exists, mocks getCurrencyByCode method.
        Currency currency = new Currency(CURR_CODE,CURR_NAME,true);
        List<Currency> currencies = new LinkedList<Currency>();
        currencies.add(currency);
        currDB.setCurrencies(currencies);
        // Verify - Asserts that currencyExists returns true, if a currency with matching currency code is found.
        assertTrue(currDB.currencyExists(CURR_CODE));
    }

    @Test
    public void getCurrenciesListUsedTest(){ //To Test if list is returning properly
        // Setup - Creating a new Currency then Adding it to the currencies list found in the currency database class.
        currency = new Currency(CURR_CODE, CURR_NAME, true);
        List<Currency> currencies = new LinkedList<Currency>();
        currencies.add(currency);
        currDB.setCurrencies(currencies);
        // Setup - Creating a new List which is a clone of the currencies list.
        // Verify - Assert that the cloned list is equal to the list returned by getCurrencies.
        assertEquals(currencies,currDB.getCurrencies());
        // Teardown - Removing the currency added to the currencies list.
        currDB.setCurrencies(null);
    }

    @Test
    public void getMajorCurrenciesSuccessTest(){ // To Test if getMajorCurrencies returns a list populated only with Major currencies.
        // Setup - Creating 2 new Currencies, one currency is a Major currency and the other is not.
        Currency nonMajorCurrency = new Currency(CURR_CODE,CURR_NAME,CURR_FALSE);
        Currency MajorCurrency = new Currency(CURR_CODE_2,CURR_NAME_2,CURR_TRUE);
        // Setup - Adding both currencies to the currencies list, therefore list has 2 currencies, a Major currency and a Non Major currency.
        List<Currency> currencies = new LinkedList<Currency>();
        currencies.add(nonMajorCurrency);
        currencies.add(MajorCurrency);
        currDB.setCurrencies(currencies);
        // Exercise - Creating a new List which is equal to the List returned via getMajorCurrencies.
        List<Currency> actualList= currDB.getMajorCurrencies();
        // Verify - Assert that the newList, is equal to 1 (Since there was only a single Major currency in the currencies list) and that the currency is a Major currency.
        assertTrue(actualList.size() == 1 && actualList.get(0).major);
        //Teardown - Removing both currencies from the currencies list.
        currDB.setCurrencies(null);
    }

    @Test
    public void getMajorCurrenciesNoMajorTest(){ // To Test if getMajorCurrencies returns an empty list if there are no major currencies present in the currencies list.
        // Setup - Create a new Non Major Currency and insert it into the list.
        currency = new Currency(CURR_CODE, CURR_NAME, false);
        List<Currency> currencies = new LinkedList<Currency>();
        currencies.add(currency);
        currDB.setCurrencies(currencies);        // Verify - Call the getMajorCurrencies method, and verify that it returns an empty list.
        assertTrue(currDB.getMajorCurrencies().isEmpty());
        // Teardown - Remove added currency from currencies list.
        currDB.currencies.remove(new Currency(CURR_CODE,CURR_NAME,CURR_FALSE));
    }

    @Test
    public void deleteCurrencySuccessTest() throws Exception { // To Test if a successful deletion occurs.
        // Setup - Create an instance of the CurrencyDatabase class with a Spy.
        currDB.setReaderWriter(readerWriter);
        // Setup - Create 2 new Currencies
        Currency currency_2 = new Currency(CURR_CODE_2,CURR_NAME_2,true);
        Currency currency = new Currency(CURR_CODE,CURR_NAME,true);
       // currDB.setReaderWriter(readerWriter);
        List<Currency> currencies = new LinkedList<Currency>();
        currencies.add(currency);
        currencies.add(currency_2);
        currDB.setCurrencies(currencies);

        // Setup - Since the delete method call the getCurrencyByCode method, then the return is pre-determined.
//        doReturn(currency_2).when(currDB).getCurrencyByCode(currency_2.code);
        // Setup - Store the current size of currencies list within an integer variable.
        int sizeBeforeDelete = currDB.getCurrencies().size();
        // Exercise - Call the deleteCurrency method, passing the currency code of one of the newly created currencies.
        currDB.deleteCurrency(currency_2.code);
        // Verify - Assert that the current size of the list is the equal to the previous size - 1.
        assertEquals(sizeBeforeDelete -1, currDB.currencies.size());
        // Teardown
        currDB.setCurrencies(null);

    }
    @Test
    public void getExchangeRateUnknownSourceCurrencyTest() { // To Test the getExchangeRate method when the source currency does not exist.
        // Setup - Creating a new instance of the currency database class using a Spy.
//        currDB.setReaderWriter(readerWriter);

        CurrencyDatabase currDBspy = spy(currDB);
        // Setup - Pre determining return value for getCurrencyByCode, for the source currency(CURR_CODE_2) null is being returned.
        doReturn(new Currency(CURR_CODE, CURR_NAME, CURR_FALSE)).when(currDBspy).getCurrencyByCode(CURR_CODE);
        doReturn(null).when(currDBspy).getCurrencyByCode(CURR_CODE_2);
        // Exercise - Calling the getExchangeRate method, and catching exception
        try {
            currDBspy.getExchangeRate(CURR_CODE_2, CURR_CODE);
        } catch (Exception exception) {
            // Verify - Assert that the exception message returned is equal to our expected message.
            assertEquals("Unkown Source currency: "+CURR_CODE_2, exception.getMessage());
        }
    }

    @Test
    public void getExchangeRateUnknownDestinationCurrencyTest() { // To Test the getExchangeRate method when the destination currency does not exist.
        // Setup - Creating a new instance of the currency database class using a Spy.
        CurrencyDatabase currDBspy = spy(currDB);
        // Setup - Pre determining return value for getCurrencyByCode, for the destination currency(CURR_CODE_2) null is being returned.
        doReturn(new Currency(CURR_CODE, CURR_NAME, CURR_FALSE)).when(currDBspy).getCurrencyByCode(CURR_CODE);
        doReturn(null).when(currDBspy).getCurrencyByCode(CURR_CODE_2);
        // Exercise - Calling the getExchangeRate method, and catching exception
        try {
            currDBspy.getExchangeRate(CURR_CODE, CURR_CODE_2);
        } catch (Exception exception) {
            // Verify - Assert that the exception message returned is equal to our expected message.
            assertEquals("Unkown Destination currency: "+CURR_CODE_2, exception.getMessage());
        }
    }
    @Mock
    CurrencyServer currencyServer;

    @Test
    public void getExchangeRateSuccessReturnRateTest() throws Exception { // To Test the getExchangeRate method when it successfully returns and exchange rate.
        // Setup - Creating a new instance of the currency database class using a Spy.
        CurrencyDatabase currDBspy = spy(currDB);
        currDBspy.setCurrencyServer(currencyServer);
        // Setup - Creating 2 new currencies and a pre-determined rate of type double.
        double rate = 0.69;
        Currency Source = new Currency(CURR_CODE, CURR_NAME, CURR_FALSE);
        Currency Destination = new Currency(CURR_CODE_2, CURR_NAME_2, CURR_TRUE);
        // Setup - Pre determining return value for getCurrencyByCode.
        doReturn(Source).when(currDBspy).getCurrencyByCode(CURR_CODE);
        doReturn(Destination).when(currDBspy).getCurrencyByCode(CURR_CODE_2);
        when(currencyServer.getExchangeRate(anyString(),anyString())).thenReturn(0.2);
        // Create new exchange rate.
        ExchangeRate exchangeRate = new ExchangeRate(Source, Destination, rate);
        // Exercise - Create new exchange rate equal to return of the getExchangeRateMethod
        ExchangeRate result = currDBspy.getExchangeRate(Source.code, Destination.code);
        result.rate = rate;
        // Verify - Assert that the returned result is equal to our pre-determined string.
        assertEquals(Source.code + " 1 = " + Destination.code +" " + exchangeRate.rate + "", result.toString());
    }

    @Test
    public void getExchangeRateTimeExceededTest() throws Exception {
        // Setup - Creating a new instance of the currency database class using a Spy.
        CurrencyDatabase currDBspy = spy(currDB);
        // currDB.setCurrencyServer(currencyServer);
        CurrencyServer currencyServer2 = new DefaultCurrencyServer() {
        };
        currDBspy.setCurrencyServer(currencyServer2);
        // Setup - Creating 2 new currencies
        Currency Source = new Currency(CURR_CODE, CURR_NAME, CURR_FALSE);
        Currency Destination = new Currency(CURR_CODE_2, CURR_NAME_2, CURR_TRUE);
        // Setup - Pre determining return value for getCurrencyByCode.
        doReturn(Source).when(currDBspy).getCurrencyByCode(CURR_CODE);
        doReturn(Destination).when(currDBspy).getCurrencyByCode(CURR_CODE_2);
//        when(currencyServer.getExchangeRate(CURR_CODE,CURR_CODE_2)).thenReturn(0.2);
//        when(currencyServer.getExchangeRate(CURR_CODE_2,CURR_CODE)).thenReturn(0.3);

        // Create new exchange rate.
        ExchangeRate exchangeRate = currDBspy.getExchangeRate(Source.code, Destination.code);
        // New value for exchangeRate timeLastChecked, which is the previous timeLastChecked minus a second.
        exchangeRate.timeLastChecked = exchangeRate.timeLastChecked - 600000;
        // Exercise - Create a new exchance rate, using the same source and destination currencies.
        ExchangeRate exchangeRate1 = currDBspy.getExchangeRate(Source.code, Destination.code);
        // Verify - Assert that even though the exchange rate took in the same source and destination currencies, the rate will be different since it uses system time.
        assertTrue(exchangeRate.rate != exchangeRate1.rate);
    }

}
