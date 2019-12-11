package edu.uom.currencymanager.currencies;

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
    }


}

