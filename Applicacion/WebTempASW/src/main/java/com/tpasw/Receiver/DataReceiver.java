package com.tpasw.Receiver;

/**
 * Created by ignacio on 02/06/17.
 */

import com.tpasw.Model.Measurement;
import com.tpasw.Model.Sensor;
import com.tpasw.Service.MeasurementService;
import com.rabbitmq.client.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataReceiver {
    private static DataReceiver INSTANCE;
    private final static String QUEUE_NAME = "measurement";
    private static final String EXCHANGE_NAME = "amq.topic";
    //news
    private static Map<Integer, Measurement> temps;


    private synchronized static void createInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataReceiver();
            createQueue();
            temps = new HashMap<>();
        }
    }

    public static DataReceiver getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }

    private static void createQueue() {
        try {
            startQueue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startQueue() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic", true);
        String queueName = channel.queueDeclare().getQueue();


        channel.queueBind(queueName, EXCHANGE_NAME, "measurement");


        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                float temp = Float.parseFloat(message);
                //Load of measurment
                Measurement m = new Measurement();
                Sensor s = new Sensor();
                s.setId((int) (Math.random() * 17));
                m.setSensor(s);
                m.setTemperature(temp);
                m.setTime((long) (Math.random() * 5000));
                saveMeasurement(m);
                temps.put(s.getId(), m);
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }

    public static void saveMeasurement(Measurement m) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(DataReceiver.class);
        MeasurementService service = (MeasurementService) context.getBean("measurementService");
        service.saveMeasurement(m);
    }

    public List<Measurement> getTemperaturesList() {
        return new ArrayList<>(temps.values());
    }
}
