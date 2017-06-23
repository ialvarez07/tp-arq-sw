package com.tpasw.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Sensor sensor;
    @Column(name = "temperature", nullable = false)
    private float temperature;
    @Column(name = "time", nullable = false)
    private long time;
    private Date date;

    public Measurement() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "com.tpasw.Model.Measurement{" +
                "temperature= " + temperature +
                ", date= " + date.toString() +
                "}";
    }
}
