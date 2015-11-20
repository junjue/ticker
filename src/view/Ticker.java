package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import controller.SymbolController;
import model.SymbolData;
import model.TableData;

public class Ticker extends JPanel {

    public Ticker(String dataFilePath) {
        JTable table;
        TableData model;
        Font f;

        f = new Font("SanSerif", Font.PLAIN, 24);
        setFont(f);
        setLayout(new BorderLayout());

        model = new TableData(dataFilePath);

        table = new JTable();
        table.setModel(model);
        table.createDefaultColumnsFromModel();
        table.setAutoCreateRowSorter(true);
        model.addTableModelListener(table);

        JScrollPane scrollpane = new JScrollPane(table);
        add(scrollpane);

        TableColumn symbolColumn = table.getColumnModel().getColumn(1);
        JComboBox<String> symbolComboBox = new JComboBox<>();
        List<SymbolData> symbolDataList = SymbolController.getInstance().getSymbols("");
        for (SymbolData symbolData : symbolDataList) {
            symbolComboBox.addItem(symbolData.getName());
        }
        symbolColumn.setCellEditor(new DefaultCellEditor(symbolComboBox));
    }

    public Dimension getPreferredSize() {
        return new Dimension(400, 300);
    }

    public static void main(String s[]) {
        JFrame frame = new JFrame("Data File Table");
        Ticker panel;

        panel = new Ticker("tables.txt");

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel, "Center");

        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
        frame.addWindowListener(new WindowCloser());
    }
}

class WindowCloser extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        Window win = e.getWindow();
        win.setVisible(false);
        System.exit(0);
    }
}