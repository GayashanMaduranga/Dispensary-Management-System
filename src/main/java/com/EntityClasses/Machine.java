package com.EntityClasses;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DAMMA on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
public class Machine extends Equipment{


    private SimpleIntegerProperty servicePeriod;
    private Date DateLastServiced;

    private List<Maintenance> maintenances;

    public Machine() {

        this.servicePeriod = new SimpleIntegerProperty();
        this.maintenances = new ArrayList<>();

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

    @Column(name = "DateLastServiced")
    public Date getDateLastServiced() {
        return DateLastServiced;
    }

    public void setDateLastServiced(Date dateLastServiced) {
        DateLastServiced = dateLastServiced;
    }


    @ElementCollection
    public List<Maintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }
}
