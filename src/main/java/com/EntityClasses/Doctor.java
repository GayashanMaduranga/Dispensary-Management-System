package com.EntityClasses;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gayashan on 8/27/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
public class Doctor extends Employee{

    private StringProperty SLMCRegNO;
    private DoubleProperty chargePerVisit;
    private List<Appointment> appointments;


    public Doctor() {
        this.SLMCRegNO = new SimpleStringProperty();
        this.chargePerVisit = new SimpleDoubleProperty();
        this.appointments = new ArrayList<>();
    }

    public String getSLMCRegNO() {
        return SLMCRegNO.get();
    }

    public StringProperty SLMCRegNOProperty() {
        return SLMCRegNO;
    }

    public void setSLMCRegNO(String SLMCRegNO) {
        this.SLMCRegNO.set(SLMCRegNO);
    }

    public double getChargePerVisit() {
        return chargePerVisit.get();
    }

    public DoubleProperty chargePerVisitProperty() {
        return chargePerVisit;
    }

    public void setChargePerVisit(double chargePerVisit) {
        this.chargePerVisit.set(chargePerVisit);
    }


    @OneToMany(mappedBy = "doctor")
    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
