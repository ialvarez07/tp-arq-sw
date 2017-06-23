package appTemp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class AccesoADatos implements ActionListener{
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private final Timer timer = new Timer(5000, this);

    public AccesoADatos() {
        timer.setInitialDelay(1000);
        timer.start();
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        RestTemplate restTemplate = new RestTemplate();
        Medicion medicion = restTemplate.getForObject("http://192.168.0.200/temperatura", Medicion.class);
        /*Medicion medicion = new Medicion();
        medicion.setTemperatura((float) (Math.random()*10+10));
        medicion.setTime(System.currentTimeMillis()/1000);*/
        
        log.info(medicion.toString());
        Grafica.getInstance().addData(medicion);
    }
    
}
