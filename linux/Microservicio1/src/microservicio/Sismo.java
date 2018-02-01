/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservicio;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author RICHARD
 */
//En este objeto se implementara hilos para generar varios registros del 
public class Sismo implements Serializable {
   private int id;
   private String sensor;
   private Date fecha;
   private Time hora;

    public Sismo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sismo other = (Sismo) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.sensor, other.sensor)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.hora, other.hora)) {
            return false;
        }
        return true;
    }



    
    
    @Override
    public String toString() {
        return   "," + sensor + "," + fecha + "," + hora ;
    }
   
   
}
