package edu.uom.currencymanager.currencies;

        import org.junit.After;
        import org.junit.Before;
        import org.junit.Test;
        import static org.junit.Assert.*;

        import java.util.LinkedList;
        import java.util.List;

public class UtilitiesTest {
    Utilities utilities;
    final String CODE = "pop";
    final String NAME = "poppy";
    final boolean BOOL = true;

    @Before
    public void setUp(){
        utilities = new Utilities();
    }

    @After
    public void tearDown(){
        utilities = null;
    }

    @Test
    public void currencyExistsSuccessTest(){
        Currency currency = new Currency(CODE,NAME,BOOL);
        List<Currency> currencyList = new LinkedList<Currency>();
        currencyList.add(currency);

        Currency newCurrency = utilities.currencyExists(currencyList,CODE);

        assertEquals(currency,newCurrency);
    }
    @Test
    public void currencyExistsDiffCurrTest(){
        Currency currency = new Currency("wqe","name",BOOL);
        List<Currency> currencyList = new LinkedList<Currency>();
        currencyList.add(currency);

        Currency newCurrency = utilities.currencyExists(currencyList,CODE);

        assertNull(newCurrency);
    }

    @Test
    public void currencyExistsNullTest(){
        List<Currency> currencyList = new LinkedList<Currency>();
        Currency newCurrency = utilities.currencyExists(currencyList,CODE);

        assertNull(newCurrency);
    }
}
