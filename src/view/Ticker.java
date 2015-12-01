package view;
import model.*;
import model.TableData;
import controller.DropdownGenerator;
import controller.SymbolController;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class Ticker extends JFrame {

    public Ticker(String dataFilePath) {
        //read table column file
        JTable table = tableInit(dataFilePath);
        //customise layout
        layoutInit(table);
        Font f;
        f = new Font("SanSerif", Font.PLAIN, 24);
        setFont(f);
        getContentPane().setLayout(new BorderLayout());
    }

    public Dimension getPreferredSize() {
        return new Dimension(400, 300);
    }

    //table initialization
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
        //read table drop down options from file
        getStringListFromFile(table, "market.txt", 0);
        getSymbolFromFile(table, 1);
        getIntArrayFromFile(table, "percentageOfShares.txt", 2);
        getIntArrayFromFile(table, "percentageOfWall.txt", 4);
        getFloatArrayFromFile(table, "dollarAboveWall.txt", 6);
        getIntArrayFromFile(table, "remainingOfWall.txt", 7);
        getFloatArrayFromFile(table, "mpp.txt", 8);
        getIntArrayFromFile(table, "mppLose.txt", 9);
        return table;
    }

    //generate string drop-down menu from file
    public void getStringListFromFile(JTable table, String filename, int columnNumber) {
        TableColumn addDropdownColumn = table.getColumnModel().getColumn(columnNumber);
        JComboBox<String> columnComboBox = new JComboBox<>();
        List<String> columnDataList = DropdownGenerator.getInstance().getStringListFromFile(filename);
        for (String x : columnDataList) {
            columnComboBox.addItem(String.valueOf(x));
        }
        columnComboBox.setEditable(true);
        addDropdownColumn.setCellEditor(new DefaultCellEditor(columnComboBox));
    }

    //generate symbol drop down menu from file
    public void getSymbolFromFile(JTable table, int columnNumber) {
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
    public void getIntArrayFromFile(JTable table, String filename, int columnNumber) {
        TableColumn addDropdownColumn = table.getColumnModel().getColumn(columnNumber);
        JComboBox<String> columnComboBox = new JComboBox<>();
        List<Integer> columnDataList = DropdownGenerator.getInstance().getIntArrayFromFile(filename);
        for (int x : columnDataList) {
            columnComboBox.addItem(String.valueOf(x));
        }
        columnComboBox.setEditable(true);
        addDropdownColumn.setCellEditor(new DefaultCellEditor(columnComboBox));
    }

    //generate float drop-down menu from file
    public void getFloatArrayFromFile(JTable table, String filename, int columnNumber) {
        TableColumn addDropdownColumn = table.getColumnModel().getColumn(columnNumber);
        JComboBox<String> columnComboBox = new JComboBox<>();
        List<Float> columnDataList = DropdownGenerator.getInstance().getFloatArrayFromFile(filename);
        for (float x : columnDataList) {
            columnComboBox.addItem(String.valueOf(x));
        }
        columnComboBox.setEditable(true);
        addDropdownColumn.setCellEditor(new DefaultCellEditor(columnComboBox));
    }

    public void layoutInit(JTable table) {
        //customize frame layout
        JFrame myFrame = new JFrame("Algo v1.0");
        myFrame.setLocation(200, 100);
        myFrame.setSize(new Dimension(1100, 800));
        //set grid layout 3*1
        GridLayout gridLayout = new GridLayout(3, 1);
        myFrame.getContentPane().setLayout(gridLayout);
        //top panel for input
        JPanel jpInput = new JPanel();
        jpInput.setBackground(new Color(0x003F3E));
//        jpInput.add(table);
        jpInput.add(new JScrollPane(table));
        //added buttons
        JButton submitButton = new JButton("Submit");
        jpInput.add(submitButton);
        JButton cancelButton = new JButton("Charts");
        jpInput.add(cancelButton);
        JButton closeButton = new JButton("Close");
        jpInput.add(closeButton);
        //center panel split for TickData and Charts
        JPanel jpSplit = new JPanel();
        jpSplit.setLayout(new java.awt.BorderLayout());
        jpSplit.setLayout(new GridLayout(1, 2));
        JTextArea text1 = new JTextArea();
        JScrollPane scroll = new JScrollPane (text1,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new java.awt.Dimension(1600, 300));
        jpSplit.add(scroll);
        JPanel chartHolder = new JPanel();
        jpSplit.add(chartHolder);

        submitButton.addActionListener(e -> {
            submitButton.setText("Submitted");
            String data = " ";
            String arg = " ";
            for(int i= 0; i<14 ; i++) {
                String value = (table.getModel().getValueAt(0, i)).toString();
                String name = table.getModel().getColumnName(i);
                String temp = name+":"+value+"\n";
                arg = arg + value+" ";
                data = data + temp;
            }
            text1.setText("Submit button clicked!"+"\n"+"\nParameterString:\n"+arg+"\n"+
                    "-------------------------------------------\n"+data);
        });
        cancelButton.addActionListener(e -> {
            cancelButton.setText("Charts generated");
            //add charts
            ChartPanel cp1 = pieChart();
            ChartPanel cp2 = lineChart();
            //create holder panel to hold all charts
            chartHolder.setLayout(new GridLayout(1, 2));
            chartHolder.add(cp1);
            chartHolder.add(cp2);
        });
        closeButton.addActionListener(e -> {
            closeButton.setText("Cleared");
        });

        //Bottom panel for OrderBook
        JPanel jpResult = new JPanel();
        jpResult.setBackground(new Color(0x00555555));

        //added 3 panels into frame
        myFrame.getContentPane().add(jpInput);
        myFrame.getContentPane().add(jpSplit);
        myFrame.getContentPane().add(jpResult);
        myFrame.setVisible(true);
        myFrame.addWindowListener(new WindowCloser());
    }

    //generate line chart
    public ChartPanel lineChart() {
        JFreeChart chart = LineChart.createLineChart();
        return new ChartPanel(chart);
    }

    //generate pie chart
    public ChartPanel pieChart() {
        JFreeChart chart = PieChart.createPieChart();
        return new ChartPanel(chart);
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