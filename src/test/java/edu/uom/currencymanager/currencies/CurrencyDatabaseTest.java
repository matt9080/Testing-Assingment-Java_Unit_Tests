package edu.uom.currencymanager.currencies;

import edu.uom.currencymanager.currencyserver.CurrencyServer;
import edu.uom.currencymanager.currencyserver.DefaultCurrencyServer;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.File;
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
    CurrencyDatabaseUpdated currDB;
    Currency currency;
    String path = "target" + File.separator + "classes" + File.separator + "test.txt";

    @Before
    public void setup() throws Exception {  // Setup which initializes required classes, generates a fresh list and populates said list with a fake currency.
        currDB = new CurrencyDatabaseUpdated(path);
        Currency currency_2 = new Currency(CURR_CODE_2,CURR_NAME_2,true);
    }

    @After
    public void tearDown(){ // Nullifies the currDB class.
        currDB = null;
    }

    @Test
    public void checkCurrCodeSuccsessTest(){
        String code = "QWE";
        assertTrue(currDB.checkCurrCode(code));
    }

    @Test
    public void checkCurrCodeFailShortTest(){
        String code = "Q";
        assertFalse(currDB.checkCurrCode(code));
    }

    @Test
    public void checkCurrCodeFailLongTest(){
        String code = "QWERTY";
        assertFalse(currDB.checkCurrCode(code));
    }
}
