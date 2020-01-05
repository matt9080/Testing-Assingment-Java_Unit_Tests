package currencyServer;

import edu.uom.currencymanager.currencyserver.DefaultCurrencyServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class CurrencyServerTest {

    DefaultCurrencyServer currencyServer;
    @Before
    public void setUp(){
        currencyServer = new DefaultCurrencyServer();
    }

    @After
    public void tearDown(){
        currencyServer = null;
    }

    @Test
    public void getExchangeRateReturnLessThenTest(){
        currencyServer.setSeed(2);
        double test = currencyServer.getExchangeRate("qwe","eqe");
        assertTrue((test < 1.5) && test > 0);
    }



}
