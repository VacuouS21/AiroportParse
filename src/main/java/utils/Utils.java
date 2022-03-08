package utils;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

     public  class Utils {

     //yml Файл, создаём экземпляр ymFile,Считываем данные из файла.
     public static ymlFile createYml(){

         //Чтение с application.yml
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("src/main/resources/application.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Экземпляр класса ymlFile
        Yaml yaml = new Yaml(new Constructor(ymlFile.class));
        ymlFile data = yaml.load(inputStream);
        return data;
    }
}
