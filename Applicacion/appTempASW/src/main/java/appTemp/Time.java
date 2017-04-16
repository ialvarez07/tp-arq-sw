package appTemp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Time {
    private int dia;
    private int mes;
    private String mesS;
    private int hora;
    private int minutos;
    private int segundos;
    private long secs;

    public Time() {
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
        
        switch (mes) {
            case 1:
                mesS = "Ene";
                break;
            case 2:
                mesS = "Feb";
                break;
            case 3:
                mesS = "Mar";
                break;
            case 4:
                mesS = "Abr";
                break;
            case 5:
                mesS = "May";
                break;
            case 6:
                mesS = "Jun";
                break;
            case 7:
                mesS = "Jul";
                break;
            case 8:
                mesS = "Ago";
                break;
            case 9:
                mesS = "Sep";
                break;
            case 10:
                mesS = "Oct";
                break;
            case 11:
                mesS = "Nov";
                break;
            case 12:
                mesS = "Dic";
                break;
        }
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public long getSecs() {
        return secs;
    }

    public void setSecs(long secs) {
        this.secs = secs;
    }
    
    @Override
    public String toString() {
        return "Time{" +
                dia + " " +
                mesS + " " +
                hora + ":" +
                minutos + ":" +
                segundos + "}";
    }
}
