package edu.uom.currencymanager.currencies;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class ReaderWriterTest {


    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    String testPath = "target" + File.separator + "classes" + File.separator + "test.txt";
    ReaderWriter readerWriter;
    Utilities utilities;

    @Before
    public void setup(){
        readerWriter = new ReaderWriter(testPath);
    }

    @After
    public void tearDown(){
        readerWriter = null;
        utilities = null;
    }

    @Test
    public void fileParserSuccess() throws Exception {
        // Setup
        List<String> testList = new LinkedList<String>();
        String firstLine = "code,name,major";
        String secondLine = "AUD,Australian Dollar,false";

        Currency testCurr = Currency.fromString(secondLine);

        testList.add(firstLine);
        testList.add(secondLine);



        // Verify
        assertEquals(testCurr.toString().trim(),readerWriter.fileParser(testList).get(0).toString().trim());
    }

    @Test
    public void fileParserFirstLineErrorTest() {
        //Setup
        List<String> testList = new LinkedList<String>();
        String firstLine = "code,name,major";

        testList.add(firstLine);

        String errorCode = "Parsing error: First line should be code,name,major";

        try{
            readerWriter.fileParser(testList);
        }catch (Exception e){
            assertEquals(errorCode,e.getMessage() );

        }
    }

    @Test
    public void fileParserCurrencyCodeErrorTest() {
        // Setup
        List<String> testList = new LinkedList<String>();
        String firstLine = "code,name,major";
        String secondLine = "AD,Australian Dollar,false";

        testList.add(firstLine);
        testList.add(secondLine);

        String errorCode = "Parsing error: Currency code should be 3 characters";

        try{
            readerWriter.fileParser(testList);
        }catch (Exception e){
            assertEquals(errorCode,e.getMessage() );
        }
    }

    @Test
    public void fileParserCurrencyNameErrorTest(){
        // Setup
        List<String> testList = new LinkedList<String>();
        String firstLine = "code,name,major";
        String secondLine = "AUD,Au,false";

        testList.add(firstLine);
        testList.add(secondLine);

        String errorCode = "Parsing error: Currency name should be longer then 4 characters";

        try{
            readerWriter.fileParser(testList);
        }catch (Exception e){
            assertEquals(errorCode,e.getMessage() );
        }
    }

//    @Mock
//    Utilities utilities;

    @Test
    public void fileParserCurrencyExistsErrorTest() throws Exception {
        // Setup
        List<String> testList = new LinkedList<String>();
        List<Currency> currencyListTest = new LinkedList<Currency>();
        String firstLine = "code,name,major";
        String secondLine = "AUD,Australian Dollar,false";

        Currency currencyTest = Currency.fromString(secondLine);

        currencyListTest.add(currencyTest);
        currencyListTest.add(currencyTest);

        utilities = mock(Utilities.class);
        Mockito.when(utilities.currencyExists(currencyListTest,currencyTest.code)).thenReturn(currencyTest);
        testList.add(firstLine);
        testList.add(secondLine);

        String errorCode = "Parsing error: Currency already exists";

        try{
            readerWriter.fileParser(testList);
        }catch (Exception e){
            assertEquals(errorCode,e.getMessage() );
        }
    }

    @Test
    public void fileParserEmptyTest(){
        List<String> testList = new LinkedList<String>();
        assertTrue(readerWriter.fileParser(testList).isEmpty());
    }


}


