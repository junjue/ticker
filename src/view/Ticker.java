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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Objects;

public class Ticker extends JFrame {
    //set refresh interval
    public final static int REFRESH_INTERVAL = 3000;
    private TableData tableData;

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
        tableData = new TableData(dataFilePath);
        table = new JTable();
        table.setModel(tableData);
        table.setRowHeight(25);
        table.createDefaultColumnsFromModel();
        table.setAutoCreateRowSorter(true);
        tableData.addTableModelListener(table);
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

    public void layoutInit(final JTable table) {
        //customize frame layout
        JFrame myFrame = new JFrame("Algo v1.0");
        myFrame.setLocation(200, 100);
        myFrame.setSize(new Dimension(1100, 800));
        //set grid layout 3*1
        GridLayout gridLayout = new GridLayout(3, 1);
        myFrame.getContentPane().setLayout(gridLayout);
        //top panel for input
        JPanel jpInput = new JPanel();
        jpInput.setLayout(new GridLayout(2,1));
        jpInput.setBackground(new Color(0x003F3E));
         JScrollPane tableScroll = new JScrollPane(table,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableScroll.setPreferredSize(new java.awt.Dimension(1084, 100));

        JScrollBar vertical = tableScroll.getVerticalScrollBar();
        vertical.setValue( vertical.getMaximum() );
        //added buttons
        JPanel jpBtn = new JPanel();
        final JButton submitButton = new JButton("Submit");
        final JButton cancelButton = new JButton("Get Charts");
        final JButton closeButton = new JButton("Close");
        jpBtn.add(submitButton);
        jpBtn.add(cancelButton);
        jpBtn.add(closeButton);
        jpInput.add(tableScroll);
        jpInput.add(jpBtn);
        //center panel split for TickData and Charts
        JPanel jpSplit = new JPanel();
        jpSplit.setLayout(new java.awt.BorderLayout());
        jpSplit.setLayout(new GridLayout(1, 2));
        final JTextArea consoleText = new JTextArea();
        JScrollPane consoleScroll = new JScrollPane(consoleText,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        consoleScroll.setPreferredSize(new java.awt.Dimension(600, 300));
        jpSplit.add(consoleScroll);
        final JPanel chartHolder = new JPanel();
        jpSplit.add(chartHolder);

        //show parameters value after click
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButton.setText("Submitted");
                closeButton.setText("Clear");
                String shownInfo = " ";
                String argToArgo = " ";
//                for (int i = 0; i < 14; i++) {
                  for (int i=0;  i<table.getColumnCount(); i++){
                    String value = (table.getModel().getValueAt(0, i)).toString();
                    String name = table.getModel().getColumnName(i);
                    String temp = name + ":" + value + "\n";
                    argToArgo += value + " ";
                    shownInfo += temp;
                }
                consoleText.setText("Submit button clicked!" + "\n" + "\nParameterString:\n" + argToArgo + "\n" +
                        "-------------------------------------------\n" + shownInfo);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Objects.equals(cancelButton.getText(), "Charts generated")){
                //add charts
                ChartPanel cp1 = Ticker.this.pieChart();
                ChartPanel cp2 = Ticker.this.lineChart();
                //create holder panel to hold all charts
                chartHolder.setLayout(new GridLayout(1, 2));
                chartHolder.add(cp1);
                chartHolder.add(cp2);
                cancelButton.setText("Charts generated");
                closeButton.setText("Clear");
                }
            }
        });
        //clear text and chart area
         closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButton.setText("Submit");
                cancelButton.setText("Get Chart");
                closeButton.setText("Cleared");
                consoleText.setText("");
                chartHolder.removeAll();
                chartHolder.updateUI();
            }
        });

        //Bottom panel for OrderBook
        JPanel jpResult = new JPanel();

        final JTextArea refreshTextArea= new JTextArea(tableData.getColumnNameString());
        refreshTextArea.setFont(new Font("SanSerif", Font.PLAIN, 12));
        JScrollPane refreshScroll = new JScrollPane(refreshTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        refreshScroll.setPreferredSize(new java.awt.Dimension(1084, 248));
        final TextAreaOutputStream textAreaOutputStream = new TextAreaOutputStream(refreshTextArea);
        jpResult.add(refreshScroll);

        Timer timer = new Timer(REFRESH_INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //Refresh the panel
                textAreaOutputStream.write(tableData.getRowDataString(0));
            }
        });

        timer.start();

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