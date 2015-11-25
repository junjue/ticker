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

    public TableData(String f) {
        datafile = f;
        init();
    }

    public void init() {
        symbolController = SymbolController.getInstance();
        data = new ArrayList<>();
        columnNames = new Vector<>();

        try {
            FileInputStream fin = new FileInputStream(datafile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));
            // extract column names
            StringTokenizer st1 = new StringTokenizer(br.readLine(), "|");
            while (st1.hasMoreTokens())
                columnNames.addElement(st1.nextToken());
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //for (SymbolData symbolData : symbolController.getSymbols("")) {

        RecordData recordData = new RecordData(new MarketData("US"), new SymbolData("Choose Symbol"), 100, 100, 5, 5000, 0.02, 20, 0.6, 20, 10, 45, 300, 300);
        data.add(recordData);
        //}

    }

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return columnNames.size();
    }

    public String getColumnName(int columnIndex) {
        String colName = "";

        if (columnIndex <= getColumnCount())
            colName = columnNames.elementAt(columnIndex);

        return colName;
    }

    public Class getColumnClass(int columnIndex) {
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
            return recordData.getPercentageOfShares();
        else if (columnIndex == 3)
            return recordData.getOfferedShares();
        else if (columnIndex == 4)
            return recordData.getPercentageOfWall();
        else if (columnIndex == 5)
            return recordData.getSharesOfWall();
        else if (columnIndex == 6)
            return recordData.getDollarAboveWall();
        else if (columnIndex == 7)
            return recordData.getPercentageRemainingOfWall();
        else if (columnIndex == 8)
            return recordData.getMpp();
        else if (columnIndex == 9)
            return recordData.getMppLose();
        else if (columnIndex == 10)
            return recordData.getT1();
        else if (columnIndex == 11)
            return recordData.getT2();
        else if (columnIndex == 12)
            return recordData.getT3();
        else if (columnIndex == 13)
            return recordData.getT4();
        return null;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        RecordData recordData = data.get(rowIndex);
        if (columnIndex == 0)
            recordData.getMarketData().setName((String) aValue);
        else if (columnIndex == 1)
            recordData.getSymbolData().setName((String) aValue);
        else if (columnIndex == 2)
            recordData.setPercentageOfShares(Integer.parseInt((String) aValue));
        else if (columnIndex == 3)
            recordData.setOfferedShares(Integer.parseInt((String) aValue));
        else if (columnIndex == 4)
            recordData.setPercentageOfWall(Integer.parseInt((String) aValue));
        else if (columnIndex == 5)
            recordData.setSharesOfWall(Integer.parseInt((String) aValue));
        else if (columnIndex == 6)
            recordData.setDollarAboveWall(Double.parseDouble((String) aValue));
        else if (columnIndex == 7)
            recordData.setPercentageRemainingOfWall(Integer.parseInt((String) aValue));
        else if (columnIndex == 8)
            recordData.setMpp(Double.parseDouble((String) aValue));
        else if (columnIndex == 9)
            recordData.setMppLose((int) aValue);
        else if (columnIndex == 10)
            recordData.setT1(Integer.parseInt((String) aValue));
        else if (columnIndex == 11)
            recordData.setT2(Integer.parseInt((String) aValue));
        else if (columnIndex == 12)
            recordData.setT3(Integer.parseInt((String) aValue));
        else if (columnIndex == 13)
            recordData.setT4(Integer.parseInt((String) aValue));
    }
}