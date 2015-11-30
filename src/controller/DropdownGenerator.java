package controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DropdownGenerator {
    private static DropdownGenerator instance;
    public static DropdownGenerator getInstance() {
        if(instance == null)
            instance = new DropdownGenerator();
        return instance;
    }
    public List<String> getStringListFromFile(String filename) {
        List<String> dataList = new ArrayList<>();
        //read file and add string into data list
        try {
            Scanner s = new Scanner(new File(filename));
            while (s.hasNext()) {
                String data = s.next();
                dataList.add(data);
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.print("File not found!");
        }
        return dataList;
    }
    public List<Integer> getIntArrayFromFile(String filename) {
        List<Integer> dataList = new ArrayList<>();
        //read file and add int into data list
        try {
            Scanner s = new Scanner(new File(filename));
            while (s.hasNext()) {
                int data = Integer.parseInt(s.next());
                dataList.add(data);
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.print("File not found!");
        }
        return dataList;
    }
    public List<Float> getFloatArrayFromFile(String filename){
        List<Float> dataList = new ArrayList<>();
        //read file and add float  into data list
        try{
            Scanner s = new Scanner(new File(filename));
            while (s.hasNext()){
                float data = Float.parseFloat(s.next());
                dataList.add(data);
            }
            s.close();
        } catch (FileNotFoundException e){
            System.out.print("File not found!");
        }
        return dataList;
    }
}
