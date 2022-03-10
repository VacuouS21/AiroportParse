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
        int low = 0;
        int high = sortArr.size() - 1;
        int mid;


        ArrayList<Integer> result= new ArrayList<>();
        //Первый элемент-время затраченное на поиск.
        result.add(0);

        //Начало отсчёта времени
        long start = System.currentTimeMillis();

        //бинарный поиск
        while (low <= high) {
            mid = (low + high) / 2;

            if (sortArr.get(mid).compareTo(inputString) < 0) {
                low = mid + 1;
                if(sortArr.get(mid).startsWith(inputString)){

                    result=smallSearch(sortArr,result,mid,inputString);
                    break;
                }
            } else if (sortArr.get(mid).compareTo(inputString) > 0) {
                high = mid - 1;
                //проверяем начало строки
                if(sortArr.get(mid).startsWith(inputString)){

                    result=smallSearch(sortArr,result,mid,inputString);
                    break;
                }
            } else {
                result=smallSearch(sortArr,result,mid,inputString);

                break;
            }

        }


        //Конец отсчёта времени
        long finish = System.currentTimeMillis();
        result.set(0, (int) (finish - start)) ;

        return result;
    }


    //Поиск вверх и вниз от найденной строки
    private static ArrayList<Integer> smallSearch(ArrayList<String> sortArr,ArrayList<Integer> result,int index,String inputString) {
        boolean notFindAllWords=true;
        boolean checkIndexUp=true;
        boolean checkIndexDown=true;
        int indexUp=index;
        int indexDown=index;

        //Пока сверху или снизу будет строки начинающиеся с заданной строки
        while (notFindAllWords){

            if(indexUp<sortArr.size()-1 && sortArr.get(indexUp+1).startsWith(inputString)){

                indexUp++;

            }
            else checkIndexUp=false;
            if(indexDown>=1 && sortArr.get(indexDown-1).startsWith(inputString)){

                indexDown--;
            }
            else checkIndexDown=false;
            notFindAllWords=checkIndexDown || checkIndexUp;

        }

        return parseArray(indexUp,indexDown,sortArr,result);
    }

    //Берём номер строки, после ,
    private static ArrayList<Integer> parseArray(int indexUp,int indexDown, ArrayList<String> sortedArr, ArrayList<Integer> result){


        for(;indexDown<=indexUp;indexDown++){

            String[] oneOfTheFindStr=sortedArr.get(indexDown).split(",");
            result.add(Integer.parseInt(oneOfTheFindStr[oneOfTheFindStr.length-1]));
        }

        return result;

    }
}
