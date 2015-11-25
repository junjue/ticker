package view;

import controller.SymbolController;
import model.SymbolData;
import model.TableData;

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

        TableColumn symbolColumn = table.getColumnModel().getColumn(1);

        JComboBox<String> symbolComboBox = new JComboBox<>();
        List<SymbolData> symbolDataList = SymbolController.getInstance().getSymbols("");
        for (SymbolData symbolData : symbolDataList) {
            symbolComboBox.addItem(symbolData.getName());
        }
        symbolComboBox.setEditable(true);
        symbolColumn.setCellEditor(new DefaultCellEditor(symbolComboBox));
        return table;
    }

    public JButton buttonInit() {
        JButton button1 = new JButton();


        return button1;
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
        jpSplit.setLayout(new GridLayout(1, 2));
        jpSplit.add(new JButton());
        jpSplit.add(new JButton());
        //Bottom panel for OrderBook
        JPanel jpResult = new JPanel();
        jpResult.setBackground(new Color(0x00555555));

        myFrame.getContentPane().add(jpInput);
        myFrame.getContentPane().add(jpSplit);
        myFrame.getContentPane().add(jpResult);
        myFrame.setVisible(true);
        myFrame.addWindowListener(new WindowCloser());
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