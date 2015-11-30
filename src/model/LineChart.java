package model;
import java.awt.Color;
import java.awt.Dimension;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class LineChart extends ApplicationFrame {
    //constructor
    public LineChart(String title) {
        super(title);
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    //create database
    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1212, "Class 1", "1");
        dataset.addValue(804, "Class 1", "2");
        dataset.addValue(1520, "Class 1", "3");
        dataset.addValue(1842, "Class 1", "4");
        dataset.addValue(1991, "Class 1", "5");
        dataset.addValue(918, "Class 2", "1");
        dataset.addValue(1226, "Class 2", "2");
        dataset.addValue(1020, "Class 2", "3");
        dataset.addValue(1862, "Class 2", "4");
        dataset.addValue(1581, "Class 2", "5");
        dataset.addValue(1035, "Class 3", "1");
        dataset.addValue(998, "Class 3", "2");
        dataset.addValue(1240, "Class 3", "3");
        dataset.addValue(2263, "Class 3", "4");
        dataset.addValue(880, "Class 3", "5");
        return dataset;
    }

    // create the chart...
    private static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createLineChart(
                "Line Chart", // chart title
                "Name", // domain axis label
                "Count", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips
                false // urls
        );
        //customise chart layout
        chart.addSubtitle(new TextTitle("Test Line Chart"));
//        TextTitle source = new TextTitle("example");
//        source.setFont(new Font("SansSerif", Font.PLAIN, 10));
//        source.setPosition(RectangleEdge.BOTTOM);
//        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
//        chart.addSubtitle(source);
        chart.setBackgroundPaint(Color.white);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);
        // customise the range axis...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // customise the renderer...
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setDrawOutlines(true);
        renderer.setUseFillPaint(true);
        return chart;
    }

    //create line chart
    public static JFreeChart createLineChart() {
        JFreeChart chart = createChart(createDataset());
        return chart;
    }
}