package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class CSVDock {
    public static ArrayList<String> parseCsv(String path, int column) throws FileNotFoundException {
        ArrayList<String> myData=new ArrayList<>();
        //Считывание заданной колонки из .dat файла
        try {
            CSVReader reader = new CSVReader(new FileReader(path));
            String [] nextLine;
            int lineNumber = 0;
            //Запись в массив всей колонки и номера строки.
            while ((nextLine = reader.readNext()) != null) {
                lineNumber++;
                myData.add(nextLine[column]+","+lineNumber);
            }
        } catch (CsvValidationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myData;
    }
}
