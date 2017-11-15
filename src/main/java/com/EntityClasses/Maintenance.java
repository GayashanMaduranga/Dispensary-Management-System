package com.EntityClasses;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by DAMMA on 8/31/2017.
 */

//@Embeddable
    @Entity                           //new
    @Access(AccessType.PROPERTY)       //new
    @DiscriminatorValue("Maintenance") //new
public class Maintenance extends RecursiveTreeObject<Maintenance> {


    private SimpleStringProperty reason;
    private Date Date;
    private SimpleDoubleProperty cost;
    private Date DateLastServiced;

    public Maintenance() {

        this.reason = new SimpleStringProperty();
        this.cost = new SimpleDoubleProperty();
    }

    @Column(name = "reason")
    public String getReason() {
        return reason.get();
    }

    public SimpleStringProperty reasonProperty() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason.set(reason);
    }

    @Column(name = "Date")
    public java.sql.Date getDate() {
        return Date;
    }

    public void setDate(java.sql.Date date) {
        Date = date;
    }

    @Column(name = "cost")
    public double getCost() {
        return cost.get();
    }

    public SimpleDoubleProperty costProperty() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost.set(cost);
    }

    @Column(name = "DateLastServiced")
    public Date getDateLastServiced() {return DateLastServiced;}

    public void setDateLastServiced(java.sql.Date dateLastServiced) {DateLastServiced = dateLastServiced;}

    public LocalDate localDateLastServiced(){return DateLastServiced.toLocalDate();}


    public SimpleStringProperty DateServicedProperty(){
        SimpleStringProperty dateString = new SimpleStringProperty();
        dateString.set(this.DateLastServiced.toString());
        return dateString;
    }
}
