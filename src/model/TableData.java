package model;

import controller.SymbolController;

import javax.swing.table.AbstractTableModel;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class TableData extends AbstractTableModel {
	
    protected List<RecordData> data;
    protected Vector<String> columnNames;
    protected String datafile;
    private SymbolController symbolController;
     
    public TableData(String f){
        datafile = f;
        init();
    }
     
    public void init() {
        symbolController = SymbolController.getInstance();
        data = new ArrayList<>();
        columnNames = new Vector<>();

        try {
            FileInputStream fin =  new FileInputStream(datafile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));
            // extract column names
            StringTokenizer st1 =
                    new StringTokenizer(br.readLine(), "|");
            while(st1.hasMoreTokens())
                columnNames.addElement(st1.nextToken());
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //for (SymbolData symbolData : symbolController.getSymbols("")) {
        RecordData recordData = new RecordData(new MarketData("US"), new SymbolData("Please Choose One"), "2", "222","2");
        data.add(recordData);
        //}

    }
     
    public int getRowCount() {
        return data.size();
    }
     
    public int getColumnCount(){
        return columnNames.size();
    }
     
    public String getColumnName(int columnIndex) {
        String colName = "";
         
        if (columnIndex <= getColumnCount())
            colName = columnNames.elementAt(columnIndex);
         
        return colName;
    }
     
    public Class getColumnClass(int columnIndex){
        return String.class;
    }
     
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
     
    public Object getValueAt(int rowIndex, int columnIndex) {
        RecordData recordData = data.get(rowIndex);
        if (columnIndex == 0)
            return recordData.getMarketData().getName();
        else if (columnIndex == 1)
            return recordData.getSymbolData().getName();
        else if (columnIndex == 2)
            return recordData.getProfit();
        else if (columnIndex == 3)
            return recordData.getMmp();
        else if (columnIndex == 4)
            return recordData.getMmp2();
        return null;
    }
     
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        RecordData recordData = data.get(rowIndex);
        if (columnIndex == 0)
            recordData.getMarketData().setName((String)aValue);
        else if (columnIndex == 1)
            recordData.getSymbolData().setName((String)aValue);
        else if (columnIndex == 2)
            recordData.setProfit((String)aValue);
        else if (columnIndex == 3)
            recordData.setMmp((String)aValue);
        else if (columnIndex == 4)
            recordData.setMmp2((String)aValue);
    }
}