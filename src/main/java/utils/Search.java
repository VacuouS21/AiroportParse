package utils;

import java.util.ArrayList;

/**
 * 2. Скорость поиска должна быть максимально высокой, перебирать весь файл на диске
 * при поиске - долго; особое внимание уделить микрооптимизациям и алгоритму;
 * 3. Сложность поиска меньше чем o(n) где n - число строк файла;
 * 4. Хранить весь в файл в памяти нельзя;
 * 5. Ошибочные и краевые ситуации должны быть обработаны;
 */
public class Search {
    public static ArrayList<Integer> BinarySearch(ArrayList<String> sortArr,String inputString){
        ArrayList<Integer> result=new ArrayList<Integer>();
        //Первый элемент-время затраченное на поиск.
        result.add(0);

        //Начало отсчёта времени
        long start = System.currentTimeMillis();

        //TODO доделать бинарный поиск подстроки во все строках



        //Конец отсчёта времени
        long finish = System.currentTimeMillis();
        result.set(0, (int) (finish - start)) ;
        return result;
    }
}
