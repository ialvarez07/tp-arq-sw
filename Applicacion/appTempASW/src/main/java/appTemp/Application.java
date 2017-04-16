package appTemp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.jfree.ui.RefineryUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class Application implements ActionListener{
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    private final Timer timer = new Timer(5000, this);
    
    public static void main(String[] args) {
        Grafica demo = Grafica.getInstance();
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
        
        new Application();
    }

    public Application() {
        timer.setInitialDelay(100000);
        timer.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        RestTemplate restTemplate = new RestTemplate();
        Medicion medicion = restTemplate.getForObject("http://192.168.0.200/temperatura", Medicion.class);
        
        log.info(medicion.toString());
        
        Grafica.getInstance().addData(medicion);
    }
    
    
}
