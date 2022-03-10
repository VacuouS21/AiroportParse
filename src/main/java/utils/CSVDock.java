package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
                //Значение и номер строки
                myData.add(nextLine[column].toLowerCase()+","+lineNumber);
            }
        } catch (CsvValidationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return myData;
    }

    public static void parseCsvForFindLine(String path, int column,ArrayList<Integer> result) throws FileNotFoundException {
       //Нужен для сохранения алфавитного порядка
        HashMap<Integer,String> resultSorted =new HashMap<>();
        ArrayList<Integer> resultOriginal =new ArrayList<>();
        resultOriginal.addAll(result);

        Collections.sort(result);

        //Считывание заданной колонки из .dat файла
        try {
            CSVReader reader = new CSVReader(new FileReader(path));
            String [] nextLine;
            int lineNumber = 0;
            int indexInResult=0;
            //Запись в массив всей колонки и номера строки.
            while ((nextLine = reader.readNext()) != null) {
                lineNumber++;
                //нашли строку
                if(indexInResult<result.size() && lineNumber==result.get(indexInResult)){
                    resultSorted.put(result.get(indexInResult),outputString(nextLine));
                    indexInResult++;
                }
            }
            //Вывод строк в алф порядке
            endSortOut(resultSorted,resultOriginal);
        } catch (CsvValidationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //Собирание всей строки из файла
    private static String outputString(String[] line){
        String oneLine = "";
        for (int i=1;i<line.length;i++){
            oneLine+=line[i]+" ";
        }
        return oneLine;
    }

    //выводим из hasmap в порядке отсортированного по значению result
    private static void endSortOut(HashMap<Integer,String> resultSorted,  ArrayList<Integer> resultOriginal){
        for(int i=0;i<resultOriginal.size();i++){
            if(resultSorted.get(resultOriginal.get(i))!=null)
            System.out.println(resultSorted.get(resultOriginal.get(i)));
        }
    }
}
