package edu.uom.currencymanager.currencies;

import edu.uom.currencymanager.Menu;
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
}