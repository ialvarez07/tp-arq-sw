package appTemp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Medicion {
    private float temperatura;
    private long time;
    private Date date;

    public Medicion() {
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
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
        return "Medicion{" +
                "temperatura= " + temperatura +
                ", date= " + date.toString() +
                "}";
    }
}
