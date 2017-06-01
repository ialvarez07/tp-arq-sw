package Service;

import Model.Measurement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ignacio on 01/06/17.
 */
public class DataAccess implements ActionListener{
    private static final Logger log = LoggerFactory.getLogger(DataAccess.class);
    private final Timer timer = new Timer(5000, this);

    public DataAccess() {
        timer.setInitialDelay(1000);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RestTemplate restTemplate = new RestTemplate();
        //Measurement measurement = restTemplate.getForObject("http://192.168.0.200/temperatura", Measurement.class);
        Measurement measurement = new Measurement();
        measurement.setTemperature((float) (Math.random()*10+10));
        measurement.setTime(System.currentTimeMillis()/1000);
        log.info(measurement.toString());

    }

}
