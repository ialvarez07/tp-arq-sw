package Controller;

import Service.DataAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name = "helloWorld", eager = true)
@ViewScoped
public class HelloWorld implements Serializable {
    private int temp = 10;

    public HelloWorld() {
        //new DataAccess();
        System.out.println("HelloWorld started!");
    }

    public int getCount(){
        return temp;
    }
    public void random(){
        temp=(int)(Math.random()  * 1000);
    }

    public String getMessage() {
        return "Hello World";
    }
}