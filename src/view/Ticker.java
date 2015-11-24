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

        TableColumn symbolColumn = table.getColumnModel().getColumn(1);
        JComboBox<String> symbolComboBox = new JComboBox<>();
        List<SymbolData> symbolDataList = SymbolController.getInstance().getSymbols("");
        for (SymbolData symbolData : symbolDataList) {
            symbolComboBox.addItem(symbolData.getName());
        }
        symbolComboBox.setEditable(true);
        symbolColumn.setCellEditor(new DefaultCellEditor(symbolComboBox));

        JFrame myFrame = new JFrame("Ticker");
        myFrame.setLocation(100, 100);
        myFrame.setSize(new Dimension(1024, 800));

        GridLayout layout = new GridLayout(2, 1);
        myFrame.getContentPane().setLayout(layout);

        JPanel jp = new JPanel();
        jp.setBackground(new Color(0x00555555));

        myFrame.getContentPane().add(table);
        myFrame.getContentPane().add(jp);

        myFrame.setVisible(true);
        myFrame.addWindowListener(new WindowCloser());
    }

    public Dimension getPreferredSize() {
        return new Dimension(400, 300);
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