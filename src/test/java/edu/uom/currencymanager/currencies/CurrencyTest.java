package edu.uom.currencymanager.currencies;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class CurrencyTest {

    final String CODE = "xxx";
    final String NAME = "qwer";
    final boolean MAJOR = true;

    @Test
    public void toStringSuccessTest() {
        Currency x = new Currency(CODE, NAME, MAJOR);
        System.out.println(x.toString());
        assertEquals(CODE + " - " + NAME, x.toString());
    }

    @Test
    public void fromStringSuccessTest() throws Exception {
        Currency x = new Currency(CODE, NAME, MAJOR);
        String testString = CODE + "," + NAME + "," + MAJOR;
        System.out.println(testString);
        assertTrue(objectTester(new Currency(CODE, NAME, MAJOR),Currency.fromString(testString)));

    }
    public boolean objectTester(Currency actual, Currency expected) {
        if (actual.code.equals(expected.code) && actual.name.equals(expected.name) && actual.major == expected.major) {
            return true;
        } else {
            return false;
        }
    }

}


//    public static void main(String[] args) {
//    CurrencyTest x = new CurrencyTest();
//    x.fromStringSuccessTest();
//    }
//}
