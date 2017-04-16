package appTemp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
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

        ValueAxis xaxis = plot.getDomainAxis();
        xaxis.setAutoRange(true);
        //xaxis.setFixedAutoRange(60000.0);
        xaxis.setVerticalTickLabels(true);

        ValueAxis yaxis = plot.getRangeAxis();
        yaxis.setRange(0.0, 40.0);

        return result;
    }
    
    public void addData(Medicion medicion) {
        Time t = medicion.getTime();
        Millisecond ms = new Millisecond(0, t.getSegundos(), t.getMinutos(), t.getHora(), t.getDia(), t.getMes(), 2017);
        this.datos.add(ms, medicion.getTemperatura());
    }
   
    public static Grafica getInstance() {
        if (instancia == null) {
            instancia = new Grafica("titiitititulo", "Mediciones", "Tiempo", "Temperatura");
        }
        return instancia;
    }
}
