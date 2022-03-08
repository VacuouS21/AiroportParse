import utils.CSVDock;
import utils.Search;
import utils.Utils;
import utils.ymlFile;

import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.sql.Array;
import java.util.*;
import java.util.regex.Pattern;

public class MainMethod {

    private static String inputString;
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
        inputString=in.nextLine();

        //Считываем свойства из application.yml
        myFile=Utils.createYml();

        //Запись в масив всех значений заданоой в yml колонки
        try {
            myData=CSVDock.parseCsv(myFile.getPath(), myFile.getColumn());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Сортировка, для дальнейшего поиска
        Collections.sort(myData);
        System.out.println(myData.get(0));

        //Поиск индексов строки, которые соответсвуют введённой строке
        ArrayList<Integer> result= Search.BinarySearch(myData,inputString);


        //Вывод результата.
        System.out.println("Результат поиска:");

        for(int i=0;i<result.size();i++){
            System.out.println(result.get(i));
        }

        System.out.println("Кол-во найденных строк: "+(result.size()-1));
        System.out.println("Время,затраченное на поиск: "+result.get(0)+" ms");

    }
}
