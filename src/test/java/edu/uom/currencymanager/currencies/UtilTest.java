package edu.uom.currencymanager.currencies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void utilFormatTest(){
        final double TEST = 3;
        final String RESPONSE = "3.00";
        String testString = Util.formatAmount(TEST);
        assertEquals(RESPONSE,testString);
    }
}
