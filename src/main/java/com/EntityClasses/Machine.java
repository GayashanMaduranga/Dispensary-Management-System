package com.EntityClasses;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DAMMA on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
@DiscriminatorValue("Machine")
public class Machine extends Item{

    private SimpleIntegerProperty servicePeriod;
    private SimpleStringProperty MachineName;
    private Date DateLastServiced;


    public Machine() {

        this.servicePeriod = new SimpleIntegerProperty();
        this.MachineName = new SimpleStringProperty();

    }


    @Column(name = "servicePeriod")
    public int getServicePeriod() {
        return servicePeriod.get();
    }

    public SimpleIntegerProperty servicePeriodProperty() {
        return servicePeriod;
    }

    public void setServicePeriod(int servicePeriod) {
        this.servicePeriod.set(servicePeriod);
    }


    @Column(name = "MachineName")

    public String getMachineName() {  return MachineName.get();  }

    public SimpleStringProperty machineNameProperty() { return MachineName; }

    public void setMachineName(String machineName) { this.MachineName.set(machineName); }

    @Column(name = "DateLastServiced")
    public Date getDateLastServiced() {
        return DateLastServiced;
    }

    public void setDateLastServiced(Date dateLastServiced) {
        DateLastServiced = dateLastServiced;
    }

    public LocalDate localDateLastServiced(){return DateLastServiced.toLocalDate();}

    public SimpleStringProperty DateServicedProperty(){
        SimpleStringProperty dateString = new SimpleStringProperty();
        dateString.set(this.DateLastServiced.toString());
        return dateString;
    }
}
