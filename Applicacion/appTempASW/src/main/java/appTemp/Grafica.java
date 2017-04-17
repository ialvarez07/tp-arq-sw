package appTemp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

public class Grafica extends ApplicationFrame {
    private static Grafica instancia = null;
    
    private final TimeSeries datos;
    
    private Grafica (String chartTitle, String dataTitle, String xTitle, String yTitle) {
        super(chartTitle);
        
        this.datos = new TimeSeries(dataTitle, xTitle, yTitle);
        
        TimeSeriesCollection dataset = new TimeSeriesCollection(this.datos);
        
        JFreeChart chart = createChart(dataset, dataTitle, xTitle, yTitle);
        chart.setBackgroundPaint(Color.LIGHT_GRAY);
        
        JPanel content = new JPanel(new BorderLayout());
        ChartPanel chartPanel = new ChartPanel(chart);
        content.add(chartPanel);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
        setContentPane(content);
    }
 
    private JFreeChart createChart(XYDataset dataset, String dataTitle, String xTitle, String yTitle) {
        JFreeChart result = ChartFactory.createTimeSeriesChart(dataTitle, xTitle, yTitle, dataset, true, true, false);
 
        XYPlot plot = result.getXYPlot();
        
        plot.setBackgroundPaint(new Color(0xffffe0));
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        DateAxis timeAxis = (DateAxis) plot.getDomainAxis();
        timeAxis.setAutoTickUnitSelection(false);
        timeAxis.setTickUnit(new DateTickUnit(DateTickUnit.SECOND, 1));
        String dateFormat = "HH:mm:ss dd/MM/yyyy";
        timeAxis.setDateFormatOverride(new SimpleDateFormat(dateFormat));
        timeAxis.setVerticalTickLabels(true);

        ValueAxis yaxis = plot.getRangeAxis();
        yaxis.setRange(0.0, 40.0);

        MyRenderer renderer = new MyRenderer(true,true, 100);
        renderer.setSeriesShape(0, new Ellipse2D.Double(-3, -3, 6, 6));
        renderer.setSeriesPaint(0, Color.gray);
        renderer.setUseFillPaint(true);
        renderer.setSeriesShapesFilled(0, true);
        renderer.setSeriesShapesVisible(0, true);
        renderer.setUseOutlinePaint(true);
        renderer.setSeriesOutlinePaint(0, Color.gray);
        
        plot.setRenderer(renderer);
        
        return result;
    }
    
    public void addData(Medicion medicion) {
        this.datos.add(new Millisecond(medicion.getDate()), medicion.getTemperatura());
    }
   
    public static Grafica getInstance() {
        if (instancia == null) {
            instancia = new Grafica("Titulo Ventana", "Mediciones", "Tiempo", "Temperatura");
        }
        return instancia;
    }
    
    private static class MyRenderer extends XYLineAndShapeRenderer {

        private List<Color> clut;

        public MyRenderer(boolean lines, boolean shapes, int n) {
            super(lines, shapes);
            clut = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                clut.add(Color.getHSBColor((float) i / n, 1, 1));
            }
        }

        @Override
        public Paint getItemFillPaint(int row, int column) {
            return clut.get(column);
        }
    }
}
