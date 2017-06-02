package Model;

/**
 * Created by ignacio on 01/06/17.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Measurement {
    private Sensor sensor;
    private float temperature;
    private long time;
    private Date date;

    public Measurement() {
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public void setTime(long time) {
        this.time = time*1000;
        //long timeZoneOffset = - (1000 * 60 * 3);
        long timeZoneOffset = 0;
        setDate(new Date(this.time + timeZoneOffset));
    }

    public long getTime() {
        return time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "temperature= " + temperature +
                ", date= " + date.toString() +
                "}";
    }
}
