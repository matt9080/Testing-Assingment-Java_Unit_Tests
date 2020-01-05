import edu.uom.currencymanager.Menu;
import edu.uom.currencymanager.currencies.Currency;
import edu.uom.currencymanager.currencies.CurrencyDatabase;
import edu.uom.currencymanager.currencies.ExchangeRate;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MenuTests {

    Menu menu;

    @Mock
    CurrencyDatabase currencyDatabase;

    @Before
    public void setUp() throws Exception {
        menu = new Menu();
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        menu = null;
    }


    @Test
    public void getStringTestOption1(){
        final String MENU_OPTION_1 = "Dispaying all Currencies";

        assertEquals(MENU_OPTION_1,menu.getStrings(1));
    }

    @Test
    public void getStringTestOption2(){
        final String MENU_OPTION_2 = "Displaying exchange Rates";

        assertEquals(MENU_OPTION_2,menu.getStrings(2));
    }

    @Test
    public void getStringTestOption3(){
        final String MENU_OPTION_3 = "Check Exchange Rate";

        assertEquals(MENU_OPTION_3,menu.getStrings(3));
    }

    @Test
    public void getStringTestOption4(){
        final String MENU_OPTION_4 = "Add Currency";

        assertEquals(MENU_OPTION_4,menu.getStrings(4));
    }

    @Test
    public void getStringTestOption5(){
        final String MENU_OPTION_5 = "Deleting Currency";

        assertEquals(MENU_OPTION_5,menu.getStrings(5));
    }

    @Test
    public void deleteCurrencyFailure() {
        menu.setCurrencyDatabase(currencyDatabase);
        doReturn(false).when(currencyDatabase).currencyExists("qwe");
        try {
            menu.deleteCurrency("qwe");
        } catch (Exception e) {
            assertEquals("Currency does not exist: " + "qwe", e.getMessage());
        }
    }

    @Test
    public void deleteCurrencySuccess() throws Exception {
        menu.setCurrencyDatabase(currencyDatabase);
        doReturn(true).when(currencyDatabase).currencyExists("qwe");
        int size = currencyDatabase.getCurrencies().size();

        menu.deleteCurrency("qwe");

        verify(currencyDatabase, times(1)).deleteCurrency("qwe");
    }

    @Test
    public void addCurrencyShortCode() {
        String code = "po", name = "name";
        String errorCode = "A currency code should have 3 characters.";
        try {
            menu.addCurrency(code, name, true);
        } catch (Exception e) {
            assertEquals(errorCode, e.getMessage());
        }
    }

    @Test
    public void addCurrencyLongCode() {
        String code = "longCode", name = "name";
        String errorCode = "A currency code should have 3 characters.";
        try {
            menu.addCurrency(code, name, true);
        } catch (Exception e) {
            assertEquals(errorCode, e.getMessage());
        }
    }

    @Test
    public void addCurrencyName2Short() {
        String code = "pop", name = "shq";
        String errorCode = "A currency's name should be at least 4 characters long.";

        try {
            menu.addCurrency(code, name, true);
        } catch (Exception e) {
            assertEquals(errorCode, e.getMessage());
        }
    }

    @Test
    public void addCurrencyAlreadyExists() {
        String code = "pop", name = "name";
        String errorCode = "The currency " + code + " already exists.";
        menu.setCurrencyDatabase(currencyDatabase);
        doReturn(true).when(currencyDatabase).currencyExists(anyString());
        try {
            menu.addCurrency(code, name, true);
        } catch (Exception e) {
            assertEquals(errorCode, e.getMessage());
        }
    }

    @Test
    public void addCurrencySuccess() throws Exception {
        menu.setCurrencyDatabase(currencyDatabase);
        String code = "pop", name = "name";
        doReturn(false).when(currencyDatabase).currencyExists(anyString());

        menu.addCurrency(code,name,true);

        verify(currencyDatabase,times(1)).addCurrency(any(Currency.class));
    }

    @Test
    public void checkExchangeRateMethodCall() throws Exception {
        menu.setCurrencyDatabase(currencyDatabase);
        menu.checkExchangeRate("qwe","asd");
        verify(currencyDatabase,times(1)).getExchangeRate(anyString(),anyString());
    }

    @Test
    public void getMajorCurrencyRatesTest() throws Exception {
        menu.setCurrencyDatabase(currencyDatabase);
        List<Currency> currencies = new LinkedList<Currency>();
        currencies.add(new Currency("cod","name",true));
        currencies.add(new Currency("qwe","nawe",true));
        ExchangeRate exchangeRate = new ExchangeRate(new Currency("cod","name",true),new Currency("qwe","nawe",true),0.2);
        doReturn(currencies).when(currencyDatabase).getMajorCurrencies();
        doReturn(currencies).when(currencyDatabase).getMajorCurrencies();
        doReturn(exchangeRate).when(currencyDatabase).getExchangeRate(anyString(),anyString());
        menu.getMajorCurrencyRates();

        assertEquals(exchangeRate,menu.getMajorCurrencyRates().get(0));
    }

    @Test
    public void getAllCurrenciesCallTest(){
        menu.setCurrencyDatabase(currencyDatabase);
        menu.getAllCurrencies();
        verify(currencyDatabase,times(1)).getCurrencies();
    }

    @Test
    public void getExchangeRatesCallTest() throws Exception {
        menu.setCurrencyDatabase(currencyDatabase);
        menu.getExchangeRates();
        verify(currencyDatabase,times(1)).getMajorCurrencies();
    }
}