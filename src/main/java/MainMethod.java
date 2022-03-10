import utils.CSVDock;
import utils.Search;
import utils.Utils;
import utils.ymlFile;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MainMethod {


    public static ymlFile myFile;
    private static ArrayList<String> myData;
/**
1. список строк аэропортов, отфильтрованных и отсортированных по нужной колонке в
лексикографическом порядке;
2. количество найденных строк;
3. время в миллисекундах, потраченное на поиск.
*/
    public static void main(String[] args) {

        //Ввод
        System.out.println("Введите строку");
        Scanner in=new Scanner(System.in);
        String inputString=in.nextLine();
        //проверка на пустую строку.
        while (inputString.isEmpty()){
            System.out.println("Вы ввели пустую строку, введите заново");
            inputString=in.nextLine();
        }
        //Считываем свойства из application.yml
        myFile=Utils.createYml();

        //ввод колонки
        int columns;
        if(args.length==1){
            columns=Integer.parseInt(args[0]);
        }
        else columns=myFile.getColumn();

        //Запись в масив всех значений заданоой колонки
        try {
            myData=CSVDock.parseCsv(myFile.getPath(), columns);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Сортировка, для дальнейшего поиска
        Collections.sort(myData);


        //Поиск индексов строки, которые соответсвуют введённой строке

        ArrayList<Integer> result= Search.BinarySearch(myData,inputString.toLowerCase());


        int time=result.get(0);
        result.remove(0);

        //Вывод результата.
        System.out.println("Результат поиска:");


        try {
            CSVDock.parseCsvForFindLine(myFile.getPath(),myFile.getColumn(),result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        System.out.println("Кол-во найденных строк: "+(result.size()));
        System.out.println("Время,затраченное на поиск: "+time+" ms");

    }
}
