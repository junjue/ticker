package model;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class TextAreaOutputStream extends OutputStream {

    private byte[] oneByte;
    private Appender appender;

    public TextAreaOutputStream(JTextArea txtara) {
        this(txtara, 1000);
    }

    public TextAreaOutputStream(JTextArea txtara, int maxlin) {
        if (maxlin < 1) {
            throw new IllegalArgumentException("TextAreaOutputStream maximum lines must be positive (value=" + maxlin + ")");
        }
        oneByte = new byte[1];
        appender = new Appender(txtara, maxlin);
    }

    /**
     * Clear the current console text area.
     */
    public synchronized void clear() {
        if (appender != null) {
            appender.clear();
        }
    }

    public synchronized void close() {
        appender = null;
    }

    @Override
    public void write(int b) throws IOException {

    }

    public synchronized void flush() {
    }

    public synchronized void write(String value) {
        if (appender != null) {
            appender.append(value);
        }
    }

// *************************************************************************************************
// STATIC MEMBERS
// *************************************************************************************************

    static class Appender implements Runnable {
        private final JTextArea textArea;
        private final int maxLines;                                                   // maximum lines allowed in text area
        private final LinkedList<Integer> lengths;                                                    // length of lines within text area
        private final List<String> values;                                                     // values waiting to be appended

        private int curLength;                                                  // length of current line
        private boolean clear;
        private boolean queue;

        Appender(JTextArea txtara, int maxlin) {
            textArea = txtara;
            maxLines = maxlin;
            lengths = new LinkedList<Integer>();
            values = new ArrayList<String>();

            curLength = 0;
            clear = false;
            queue = true;
        }

        synchronized void append(String val) {
            values.add(val);
            if (queue) {
                queue = false;
                EventQueue.invokeLater(this);
            }
        }

        synchronized void clear() {
            clear = true;
            curLength = 0;
            lengths.clear();
            values.clear();
            if (queue) {
                queue = false;
                EventQueue.invokeLater(this);
            }
        }

        // MUST BE THE ONLY METHOD THAT TOUCHES textArea!
        public synchronized void run() {
            if (clear) {
                textArea.setText("");
            }
            for (String val : values) {
                curLength += val.length();
                if (val.endsWith(EOL1) || val.endsWith(EOL2)) {
                    if (lengths.size() >= maxLines) {
                        textArea.replaceRange("", 0, lengths.removeFirst());
                    }
                    lengths.addLast(curLength);
                    curLength = 0;
                }
                textArea.append(val);
            }
            values.clear();
            clear = false;
            queue = true;
        }

        static private final String EOL1 = "\n";
        static private final String EOL2 = System.getProperty("line.separator", EOL1);
    }

}
