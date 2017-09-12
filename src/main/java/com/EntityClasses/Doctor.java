package com.EntityClasses;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by gayashan on 8/27/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
public class Doctor extends Employee{

    StringProperty SLMCRegNO;
    DoubleProperty chargePerVisit;


    public Doctor() {
        SLMCRegNO = new SimpleStringProperty();
        chargePerVisit = new SimpleDoubleProperty();

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
}
