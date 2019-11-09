package edu.uom.currencymanager.currencies;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class CurrencyTest {

    final String CODE = "xxx";
    final String NAME = "qwer";
    final boolean MAJOR = false;

    @Test
    public void toStringSuccessTest() {
        // Setup - Creating a new currency
        Currency x = new Currency(CODE, NAME, MAJOR);
        // Verify - Assert that the toString method returns the currency in the appropriate String format
        assertEquals(CODE + " - " + NAME, x.toString());
    }

    @Test
    public void fromStringSuccessTest() throws Exception {
        // Setup - Creating a new currency
        Currency actual = new Currency(CODE, NAME, MAJOR);
        // Setup - Creating a sting using the same string variables in the previously created currency.
        String testString = CODE + "," + NAME + "," + MAJOR;
        // Exercise - Creating a new currency by using the fromString method.
        Currency expected = Currency.fromString(testString);
        // Verify - Asserting that the previously created currency is equal to the currency created via the fromString method.
        assertTrue(actual.code.equals(expected.code) && actual.name.equals(expected.name) && actual.major == expected.major);

    }
    // Test constructor by creating new currency and check if its bs matches
    @Test
    public void currencyCreationTest(){
        // Exercise - Create a new currency using pre-determined code,name and major.
        Currency newCurrency = new Currency(CODE,NAME,MAJOR);
        // Verify - Assert that the new currency's code, name and major are equal to the variables passed in the creation.
        assertTrue(CODE.equals(newCurrency.code) && NAME.equals(newCurrency.name) && MAJOR == newCurrency.major);
    }

}


//    public static void main(String[] args) {
//    CurrencyTest x = new CurrencyTest();
//    x.fromStringSuccessTest();
//    }
//}
