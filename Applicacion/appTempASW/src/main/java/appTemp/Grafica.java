package appTemp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
 
    private JFreeChart createChart(final XYDataset dataset, String dataTitle, String xTitle, String yTitle) {
        JFreeChart result = ChartFactory.createTimeSeriesChart(dataTitle, xTitle, yTitle, dataset, true, true, false);
 
        XYPlot plot = result.getXYPlot();
        
        plot.setBackgroundPaint(new Color(0xffffe0));
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        //Distancia cada marca del eje X = 5 segundos
        DateAxis timeAxis = (DateAxis) plot.getDomainAxis();
        timeAxis.setAutoTickUnitSelection(false);
        timeAxis.setTickUnit(new DateTickUnit(DateTickUnit.SECOND, 5));
        
        //Seteo el formato de las labels de eje X
        String dateFormat = "HH:mm:ss dd/MM/yyyy";
        timeAxis.setDateFormatOverride(new SimpleDateFormat(dateFormat));
        timeAxis.setVerticalTickLabels(true);
        
//        timeAxis.setMinimumDate(new Date()); //No funciona

        ValueAxis yaxis = plot.getRangeAxis();
        yaxis.setRange(0.0, 40.0);

        int cantColors = 40;
        List<Color> paletaColores = new ArrayList<>(cantColors);
        for (int i = 0; i < cantColors; i++) {
//            System.out.println("aaa: "+(float) (2.0/3 - (float)i / (cantColors-1) /1.5));
            paletaColores.add(Color.getHSBColor((float) (2.0/3 - ((float)i / (cantColors-1) * 2.0/3)), 1, 1));
            // 2.0/3 -> empieza del azul
            // i / (cantColors-1) -> va auumentando en <cantColor> steps
            // i / (cantColors-1) /1.5 -> para que lo maximo que crezca sea hasta 2/3
            // i / (cantColors-1) /1.5 -> equivalente a dividirlo por 1.5
        }
        
        
        //Creo un controlador de render que pinte las mediciones segun su temperatura
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true) {
            
            @Override
            public Paint getItemFillPaint(int row, int column) {
                //row = series, column = n° medicion en serie
                int y = datos.getValue(column).intValue(); //value = temperatura
                return paletaColores.get(y);
            }
        };
        
        //Mediciones serán indicadas con un circulo con bordes gris oscuro
        renderer.setSeriesShape(0, new Ellipse2D.Double(-3, -3, 6, 6));
        renderer.setSeriesPaint(0, Color.gray); //Linea que unes los puntos
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
    
    //Singleton
    public static Grafica getInstance() {
        if (instancia == null) {
            instancia = new Grafica("App Temperatura - TP ADS", "Mediciones", "Tiempo", "Temperatura");
        }
        return instancia;
    }
}
