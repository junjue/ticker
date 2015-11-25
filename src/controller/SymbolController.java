package controller;

import model.SymbolData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SymbolController {

    private static SymbolController instance;

    public static SymbolController getInstance() {
        if(instance == null)
            instance = new SymbolController();
        return instance;
    }

    public List<SymbolData> getSymbols(String marketName) {
        List<SymbolData> symbolDataList = new ArrayList<>();
        try {
            Scanner s = new Scanner(new File("symbols.txt"));
            while (s.hasNext()) {
                SymbolData symbolData = new SymbolData(s.next());
                symbolDataList.add(symbolData);
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.print("File not found!");
        }
        return symbolDataList;
    }
}
