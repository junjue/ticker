package model;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
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
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

public class LineChart extends ApplicationFrame {
    public LineChart(String title) {
        super(title);
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(212, "Classes", "1");
        dataset.addValue(504, "Classes", "2");
        dataset.addValue(1520, "Classes", "3");
        dataset.addValue(1842, "Classes", "4");
        dataset.addValue(2991, "Classes", "5");
        return dataset;
    }

    private static JFreeChart createChart(CategoryDataset dataset) {
// create the chart...
        JFreeChart chart = ChartFactory.createLineChart(
                "Line Chart", // chart title
                "Name", // domain axis label
                "Count", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                false, // include legend
                true, // tooltips
                false // urls
        );
        chart.addSubtitle(new TextTitle("Line Chart"));
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
        LineAndShapeRenderer renderer
                = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setDrawOutlines(true);
        renderer.setUseFillPaint(true);
        return chart;
    }
    public static JFreeChart createLineChart() {
        JFreeChart chart = createChart(createDataset());

        return chart;
    }
}