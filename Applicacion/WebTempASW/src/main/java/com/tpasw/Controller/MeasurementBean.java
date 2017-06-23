package com.tpasw.Controller;


import com.tpasw.Receiver.DataAccess;
import com.tpasw.Receiver.DataReceiver;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name = "measurementBean", eager = true)
@ViewScoped
public class MeasurementBean implements Serializable {
    private static MeasurementBean INSTANCE = null;
    private DataAccess dataAccess;
    private DataReceiver dataReceiver;


    public MeasurementBean(){
        //dataAccess = DataAccess.getInstance();
        dataReceiver = DataReceiver.getInstance();
    }



    public String getMessage() {
        return "Hello World";
    }


    public void showTemperature(){
        dataReceiver.getTemperaturesList();
    }

    public DataAccess getDataAccess() {
        return dataAccess;
    }

    public void setDataAccess(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    public DataReceiver getDataReceiver() {
        return dataReceiver;
    }

    public void setDataReceiver(DataReceiver dataReceiver) {
        this.dataReceiver = dataReceiver;
    }
}