package edu.uom.currencymanager.currencies;

import edu.uom.currencymanager.CurrencyManager;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.LinkedList;

public class CurrencyManagerTest {

    CurrencyManager currencyManager;
    CurrencyDatabase currDB;

    final String CURR_CODE = "TES";
    final String CURR_NAME = "TESTING";

    @Before
    public void setup() throws Exception {  // Setup which initializes required classes, generates a fresh list and populates said list with a fake currency.
        currencyManager = new CurrencyManager();
        currDB =  spy(CurrencyDatabase.class);
    }

    @After
    public void tearDown(){ // Nullifies the currDB class.
        currencyManager = null;
        currDB = null;
    }

    @Test
    public void addCurrencySuccessTest() throws Exception {

        int size = currDB.currencies.size();
        doReturn(null).when(currDB).getCurrencies();
        doReturn(true).when(currDB).currencyExists(CURR_CODE);

        currencyManager.addCurrency("xxx",CURR_NAME,true);
        assertEquals(size + 1,currDB.currencies.size());

    }
}
