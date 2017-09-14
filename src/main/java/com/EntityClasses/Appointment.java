package com.EntityClasses;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by Damma on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "Appointment")
public class Appointment {


    private IntegerProperty id;
    private SimpleDoubleProperty charge;
    private Date Date;
    private Time Time;

    private Doctor doctor;
    private Patient patient;


    public Appointment() {

        this.charge = new SimpleDoubleProperty();
        this.id = new SimpleIntegerProperty();


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


    @Id
    @GeneratedValue
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
