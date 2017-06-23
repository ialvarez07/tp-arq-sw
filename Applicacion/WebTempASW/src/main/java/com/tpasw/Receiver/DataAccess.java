package com.tpasw.Receiver;



import com.tpasw.Model.Measurement;
import com.tpasw.Model.Sensor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ignacio on 01/06/17.
 */
public class DataAccess implements ActionListener{
    private static DataAccess INSTANCE;
    private static final Logger log = LoggerFactory.getLogger(DataAccess.class);
    private final Timer timer = new Timer(500, this);

    //news
    private Map<Integer, com.tpasw.Model.Measurement> temps;

    private DataAccess() {
        createArrayList();
        timer.setInitialDelay(1000);
        timer.start();
    }

    private synchronized static void createInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataAccess();
        }
    }
    public static DataAccess getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RestTemplate restTemplate = new RestTemplate();
        //com.tpasw.Model.Measurement measurement = restTemplate.getForObject("http://192.168.0.200/temperatura", com.tpasw.Model.Measurement.class);
        Measurement measurement = new Measurement();
        measurement.setSensor(new Sensor((int)(Math.random() * 10)));

        measurement.setTemperature((float) (Math.random()*10+10));
        measurement.setTime(System.currentTimeMillis()/1000);
        temps.put(measurement.getSensor().getId(),measurement);
        log.info(measurement.toString());
    }



    //TODO the initialisation should be in other class
    public void createArrayList(){
        temps = new HashMap<>();
        for(int i=0; i< 10; i++){
            Measurement m = new Measurement();
            Sensor s = new Sensor();
            s.setId(i);
            m.setSensor(s);
            m.setTemperature((float)Math.random() * 30);
            m.setTime((long)(Math.random() * 5000));
            temps.put(s.getId(),m);
        }
    }

    public List<Measurement> getTemperaturesList() {
        return new ArrayList<>(temps.values());
    }


}
