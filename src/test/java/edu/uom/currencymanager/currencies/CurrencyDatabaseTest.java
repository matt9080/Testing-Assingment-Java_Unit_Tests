package edu.uom.currencymanager.currencies;

import edu.uom.currencymanager.currencyserver.CurrencyServer;
import edu.uom.currencymanager.currencyserver.DefaultCurrencyServer;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.File;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class CurrencyDatabaseTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    CurrencyDatabaseUpdated currDB;
    String path = "target" + File.separator + "classes" + File.separator + "test.txt";
    Utilities utilities;

    final String CODE = "QWE";
    final String NAME = "NAME";
    final boolean MAJOR = true;

    @Before
    public void setup() throws Exception {  // Setup which initializes required classes, generates a fresh list and populates said list with a fake currency.
        currDB = new CurrencyDatabaseUpdated(path);
    }

    @After
    public void tearDown(){ // Nullifies the currDB class.
        currDB = null;
        utilities = null;
    }

    @Test
    public void checkCurrCodeSuccsessTest(){
        assertTrue(currDB.checkCurrCode(CODE));
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

    @Test
    public void checkCurrNameSuccsessLenghtEqualTest(){
        assertTrue(currDB.checkCurrName(NAME));
    }

    @Test
    public void checkCurrNameSuccsessLenghtGreaterTest(){
        String name = "NAMELONG";
        assertTrue(currDB.checkCurrName(name));
    }

    @Test
    public void checkCurrNameFailShortTest(){
        String name = "QWE";
        assertFalse(currDB.checkCurrName(name));
    }

    @Test
    public void addCurrencyTest(){
        Currency currency = new Currency(CODE,NAME,MAJOR);
        int size = currDB.getCurrencyList().size();

        currDB.addCurrency(currency);

        assertEquals(size + 1, currDB.getCurrencyList().size());
    }

    @Test
    public void removeCurrencyTest(){
        Currency currency = new Currency(CODE,NAME,MAJOR);
        currDB.addCurrency(currency);
        int size = currDB.getCurrencyList().size();

        currDB.removeCurrency(currency);

        assertEquals(size - 1, currDB.getCurrencyList().size());
    }
}
