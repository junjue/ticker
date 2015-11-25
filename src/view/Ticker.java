package view;
import javafx.scene.chart.Chart;
import model.LineChart;
import controller.DropdownGenerator;
import controller.SymbolController;
import model.SymbolData;
import model.TableData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class Ticker extends JFrame {

    public Ticker(String dataFilePath) {
        JTable table = tableInit(dataFilePath);
        layoutInit(table);
        Font f;
        f = new Font("SanSerif", Font.PLAIN, 24);
        setFont(f);
        getContentPane().setLayout(new BorderLayout());
    }

    public Dimension getPreferredSize() {
        return new Dimension(400, 300);
    }

    public JTable tableInit(String dataFilePath) {
        JTable table;
        TableData model;
        model = new TableData(dataFilePath);
        table = new JTable();
        table.setModel(model);
        table.setRowHeight(25);
        table.createDefaultColumnsFromModel();
        table.setAutoCreateRowSorter(true);
        model.addTableModelListener(table);
        getStringListFromFile(table, "market.txt", 0);
        getSymbolFromFile(table, 1);
//        getStringListFromFile(table, "symbols,txt", 1);
        getIntArrayFromFile(table, "percentageOfShares.txt", 2);
        getIntArrayFromFile(table, "percentageOfWall.txt", 4);
        getFloatArrayFromFile(table, "dollarAboveWall.txt", 6);
        getIntArrayFromFile(table, "remainingOfWall.txt", 7);
        getFloatArrayFromFile(table, "mpp.txt", 8);
        getIntArrayFromFile(table, "mppLose.txt", 9);
        return table;
    }
    //generate string drop-down menu from file
    public void getStringListFromFile(JTable table, String filename, int columnNumber){
        TableColumn addDropdownColumn = table.getColumnModel().getColumn(columnNumber);
        JComboBox <String> columnComboBox = new JComboBox<>();
        List<String> columnDataList = DropdownGenerator.getInstance().getStringListFromFile(filename);
        for (String x : columnDataList){
            columnComboBox.addItem(String.valueOf(x));
        }
//        columnComboBox.setEditable(true);
        addDropdownColumn.setCellEditor(new DefaultCellEditor(columnComboBox));
    }
    public void getSymbolFromFile(JTable table, int columnNumber){
        TableColumn symbolColumn = table.getColumnModel().getColumn(columnNumber);

        JComboBox<String> symbolComboBox = new JComboBox<>();
        List<SymbolData> symbolDataList = SymbolController.getInstance().getSymbols("");
        for (SymbolData symbolData : symbolDataList) {
            symbolComboBox.addItem(symbolData.getName());
        }
        symbolComboBox.setEditable(true);
        symbolColumn.setCellEditor(new DefaultCellEditor(symbolComboBox));
    }
    //generate int drop-down menu from file
    public void getIntArrayFromFile(JTable table, String filename, int columnNumber){
        TableColumn addDropdownColumn = table.getColumnModel().getColumn(columnNumber);
        JComboBox <String> columnComboBox = new JComboBox<>();
        List<Integer> columnDataList = DropdownGenerator.getInstance().getIntArrayFromFile(filename);
        for (int x : columnDataList){
            columnComboBox.addItem(String.valueOf(x));
        }
//        columnComboBox.setEditable(true);
        addDropdownColumn.setCellEditor(new DefaultCellEditor(columnComboBox));
    }
    //generate float drop-down menu from file
    public void getFloatArrayFromFile(JTable table, String filename, int columnNumber){
        TableColumn addDropdownColumn = table.getColumnModel().getColumn(columnNumber);
        JComboBox <String> columnComboBox = new JComboBox<>();
        List<Float> columnDataList = DropdownGenerator.getInstance().getFloatArrayFromFile(filename);
        for (float x : columnDataList){
            columnComboBox.addItem(String.valueOf(x));
        }
//        columnComboBox.setEditable(true);
        addDropdownColumn.setCellEditor(new DefaultCellEditor(columnComboBox));
    }

    public void layoutInit(JTable table) {

        JFrame myFrame = new JFrame("Algo v1.0");
        myFrame.setLocation(200, 100);
        myFrame.setSize(new Dimension(1100, 800));

        GridLayout gridLayout = new GridLayout(3, 1);
        myFrame.getContentPane().setLayout(gridLayout);
        //top panel for input
        JPanel jpInput = new JPanel();
        jpInput.setBackground(new Color(0x003F3E));
        jpInput.add(table);

        JButton button = new JButton();
        button.setText("Submit");
        jpInput.add(button);
        JButton button_1 = new JButton();
        button_1.setText("Cancel");
        jpInput.add(button_1);
        JButton button_2 = new JButton();
        button_2.setText("Close");
        jpInput.add(button_2);

        //center panel split for TickData and Charts
        JPanel jpSplit = new JPanel();
        jpSplit.setLayout(new java.awt.BorderLayout());
        jpSplit.setLayout(new GridLayout(1, 2));

        jpSplit.add(new JButton());
        //add charts
        ChartPanel cp = drawChart();
//        jpSplit.add(cp, BorderLayout.CENTER);
        ChartPanel cp2 = lineChart();
        //create holder panel to hold all charts
        JPanel chartHolder = new JPanel();
        chartHolder.setLayout(new GridLayout(1,2));
        chartHolder.add(cp);
        chartHolder.add(cp2);
        //add holder panel into jpSplit
        jpSplit.add(chartHolder);

        //Bottom panel for OrderBook
        JPanel jpResult = new JPanel();
        jpResult.setBackground(new Color(0x00555555));

        myFrame.getContentPane().add(jpInput);
        myFrame.getContentPane().add(jpSplit);
        myFrame.getContentPane().add(jpResult);
        myFrame.setVisible(true);
        myFrame.addWindowListener(new WindowCloser());
    }
    public ChartPanel lineChart(){
        JFreeChart chart = LineChart.createLineChart();
        ChartPanel cp = new ChartPanel(chart);
        return cp;
    }
    public ChartPanel drawChart(){
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Category 1", 43.1);
        data.setValue("Category 2", 27.9);
        data.setValue("Category 3", 79.5);
        JFreeChart chart1 = ChartFactory.createPieChart(
                "Pie Chart",
                data,
                true, // legend?
                true, // tooltips?
                false // URLs?
        );

        ChartPanel cp = new ChartPanel(chart1);
        return cp;
    }
    public static void main(String s[]) {
        new Ticker("tables.txt");
    }
}

class WindowCloser extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        Window win = e.getWindow();
        win.setVisible(false);
        System.exit(0);
    }
}