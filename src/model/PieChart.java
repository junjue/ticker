package model;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;
import java.awt.*;

public class PieChart extends ApplicationFrame  {
    //constructor
    public PieChart(String title) {
        super(title);
        DefaultPieDataset dataset = createDataset();
        JFreeChart chart = createPieChart();
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }
    //create data set
    private static DefaultPieDataset createDataset() {
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Category 1", 43.1);
        data.setValue("Category 2", 27.9);
        data.setValue("Category 3", 59.5);
        data.setValue("Category 4", 62.7);
        data.setValue("Category 5", 18.2);
        data.setValue("Category 6", 34.6);
        return data;
    }
    //create pie chart
    private static JFreeChart createChart(DefaultPieDataset data) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Pie Chart",
                data,
                true, // legend?
                true, // tooltips?
                false // URLs?
        );
        //customise layout
        chart.addSubtitle(new TextTitle("Test Pie Chart"));
        chart.setBackgroundPaint(Color.white);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        return chart;
    }
   // create pic chart handler
    public static JFreeChart createPieChart() {
        JFreeChart chart = createChart(createDataset());
        return chart;
    }
}
