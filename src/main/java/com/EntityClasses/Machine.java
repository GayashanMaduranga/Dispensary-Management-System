package com.EntityClasses;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by DAMMA on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "Machine  ", schema = "entitydb")
public class Machine {


    private SimpleIntegerProperty servicePeriod;
    private Date DateLastServiced;

    public Machine() {

        this.servicePeriod = new SimpleIntegerProperty();

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
}
