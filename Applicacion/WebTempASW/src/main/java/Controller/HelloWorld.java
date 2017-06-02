package Controller;

import Model.Measurement;
import Model.Sensor;
import Service.DataAccess;
import Service.DataReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.*;

@ManagedBean(name = "helloWorld", eager = true)
@ViewScoped
public class HelloWorld implements Serializable {
    private static HelloWorld INSTANCE = null;
    private DataAccess dataAccess;
    private DataReceiver dataReceiver;


    public HelloWorld(){
        dataAccess = DataAccess.getInstance();
        dataReceiver = DataReceiver.getInstance();
    }



    public String getMessage() {
        return "Hello World";
    }


    public void showTemperature(){
        dataAccess.getTemperaturesList();
    }

    public DataAccess getDataAccess() {
        return dataAccess;
    }

    public void setDataAccess(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }
}