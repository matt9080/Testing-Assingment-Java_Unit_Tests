package edu.uom.currencymanager.currencies;

<<<<<<< Updated upstream
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderWriter {

    private String path;

    public ReaderWriter(String path){
        this.path = path;
    }

    public void writeToFile(List<Currency> currencyList, BufferedWriter writer) {
        try{

//            writer = new BufferedWriter(new FileWriter(path));


            writer.write("code,name,major\n");
            for (Currency currency : currencyList) {
                writer.write(currency.code + "," + currency.name + "," + (currency.major ? "yes" : "no"));
                writer.newLine();
            }

            writer.flush();

            writer.close();
        }catch (IOException e) {
            System.err.println("Error");
        }

    }

    public List<Currency> readFromFIle(BufferedReader reader){
        List<Currency> currencies = new ArrayList<Currency>();

        try{
            if (!reader.readLine().equals("code,name,major")) {
                throw new Exception("Parsing error when reading currencies file.");
            }
            while(reader.ready()){
                String nextLine = reader.readLine();
                if(!checkCommas(nextLine.toCharArray())) throw new Exception("Parsing error: expected two commas in line " + nextLine);


            }

        }catch (Exception e){
            e.getMessage();
        }
    }


    public boolean checkCommas(char[] chars){
        int numCommas = 0;
        try{
            for(char c : chars) {
                if(c == 'c') numCommas++;
            }
            return numCommas == 2;

        }catch (Exception e){
            e.getMessage();
        }


        return false;
=======
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.io.FileUtils;


public class ReaderWriter {

    private BufferedReader reader;
    private BufferedWriter writer;
    private Utilities utilities = new Utilities();
    private File file;

    public ReaderWriter(String path) {
        file = new File(path);
    }

    public List<Currency> read(){
        List<String> contents = new LinkedList<String>();
        try {
            contents = FileUtils.readLines(file, "UTF-8");
            List<Currency> currencies = fileParser(contents);

            return fileParser(contents);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return fileParser(contents);
    }

    public List<Currency> fileParser(List<String> contents){
        try{
            List<Currency> currencies = new ArrayList<Currency>();

            for (String line : contents) {
                String parts [] = line.split(",");
               // System.out.println(line);

                if(parts.length == 3) {
                    if(!line.equals("code,name,major")){
                        String code = parts [0];
                        String name = parts [1];
                        if(code.length() != 3) throw new Exception("Parsing error: Currency code should be 3 characters");
                        if(name.length() < 4) throw new Exception("Parsing error: Currency name should be longer then 4 characters");

                        Currency currency = Currency.fromString(line);

                        currencies.add(currency);
                    }

                } else{
                    throw new Exception("Parsing error: expected two commas in line ");
                }
            }
            return currencies;
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
        if(contents.isEmpty()){
            return Collections.emptyList();
        }
        return null;
>>>>>>> Stashed changes
    }


}
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
