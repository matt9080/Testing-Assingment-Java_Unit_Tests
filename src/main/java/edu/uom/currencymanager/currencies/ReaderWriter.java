package edu.uom.currencymanager.currencies;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.*;

import org.apache.commons.io.FileUtils;


public class ReaderWriter {

    private BufferedReader reader;
    private BufferedWriter writer;
    private Utilities utilities = new Utilities();
    private File file;

    public ReaderWriter(String path) {
        file = new File(path);

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
            contents.removeAll(Arrays.asList("", null));
            if(!contents.get(0).equals("code,name,major")) throw new Exception("Parsing error: First line should be code,name,major");

            for (int i = 1; i < contents.size(); i++){
                String parts [] = contents.get(i).split(",");
                // System.out.println(line);

                if(parts.length == 3) {
                    String code = parts [0];
                    String name = parts [1];
                    if(code.length() != 3) throw new Exception("Parsing error: Currency code should be 3 characters");
                    if(name.length() < 4) throw new Exception("Parsing error: Currency name should be longer then 4 characters");
                    if(utilities.currencyExists(currencies,code) != null) throw new Exception("Parsing error: Currency already exists");

                    Currency currency = Currency.fromString(contents.get(i));

                    currencies.add(currency);

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
        }else {
            return null;
        }

    }

    public void saveListToFile(List<Currency> contents){

        String startLine = "code,name,major\n";
        try{
            FileUtils.writeStringToFile(file, startLine, "UTF-8");
            for(int i = 0; i < contents.size(); i++){
                String currency = contents.get(i).code + "," + contents.get(i).name + "," + contents.get(i).major + "\n";
                FileUtils.writeStringToFile(file,currency,"UTF-8",true);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}
