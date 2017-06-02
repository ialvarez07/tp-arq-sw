package Model;

/**
 * Created by ignacio on 02/06/17.
 */
public class Sensor {
    private int id;
    private String ip;

    public Sensor() {
    }

    public Sensor(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                '}';
    }
}
