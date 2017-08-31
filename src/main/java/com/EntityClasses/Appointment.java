package com.EntityClasses;

import javafx.beans.property.SimpleDoubleProperty;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by Damma on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "Appointment", schema = "entitydb")
public class Appointment {

    private SimpleDoubleProperty charge;
    private Date Date;
    private Time Time;



    public Appointment() {

        this.charge = new SimpleDoubleProperty();

    }



    @Column(name = "charge")
    public double getCharge() {
        return charge.get();
    }

    public SimpleDoubleProperty chargeProperty() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge.set(charge);
    }

    @Column(name = "Date")
    public java.sql.Date getDate() {
        return Date;
    }

    public void setDate(java.sql.Date date) {
        Date = date;
    }

    @Column(name = "Time")
    public java.sql.Time getTime() {
        return Time;
    }

    public void setTime(java.sql.Time time) {
        Time = time;
    }
}
