package com.EntityClasses;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Damma on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
public class Medication {

    private SimpleIntegerProperty medID;
    private SimpleStringProperty medication;
    private Date date;
    private SimpleIntegerProperty dosage;
    private SimpleStringProperty dosageType;
    private SimpleStringProperty frequency;
    private SimpleStringProperty discontinuedReason;
    private SimpleBooleanProperty discontinued;


    public Medication() {

        this.medID = new SimpleIntegerProperty();
        this.medication = new SimpleStringProperty();
        this.dosage = new SimpleIntegerProperty();
        this.dosageType = new SimpleStringProperty();
        this.frequency = new SimpleStringProperty();
        this.discontinued = new SimpleBooleanProperty();
        this.discontinuedReason = new SimpleStringProperty();

    }

    @Id
    @Column(name = "medID")
    @GeneratedValue
    public int getMedID() {
        return medID.get();
    }

    public SimpleIntegerProperty medIDProperty() {
        return medID;
    }

    public void setMedID(int medID) {
        this.medID.set(medID);
    }

    //Date
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SimpleStringProperty dateProperty(){
        SimpleStringProperty dateString = new SimpleStringProperty();
        dateString.set(this.date.toString());
        return dateString;
    }

    @Column(name = "medication")
    public String getMedication() {
        return medication.get();
    }

    public SimpleStringProperty medicationProperty() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication.set(medication);
    }

    @Transient
    public SimpleStringProperty dosageStringProperty(){
        SimpleStringProperty dosageString = new SimpleStringProperty();
        dosageString.set(Integer.toString(this.getDosage())+" "+this.getDosageType());
        return dosageString;
    }

    @Column(name = "dosage")
    public int getDosage() {
        return dosage.get();
    }

    public SimpleIntegerProperty dosageProperty() {
//        SimpleStringProperty dosageString = new SimpleStringProperty();
//        dosageString.set(Integer.toString(this.getWeight())+" "+this.getDosageType());
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage.set(dosage);
    }

    @Column(name = "dosageType")
    public String getDosageType() {
        return dosageType.get();
    }

    public SimpleStringProperty dosageTypeProperty() {
        return dosageType;
    }

    public void setDosageType(String dosageType) {
        this.dosageType.set(dosageType);
    }

    @Column(name = "frequency")
    public String getFrequency() {
        return frequency.get();
    }

    public SimpleStringProperty frequencyProperty() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency.set(frequency);
    }

    @Column(name = "discontinued")
    public boolean isDiscontinued() {
        return discontinued.get();
    }

    public SimpleBooleanProperty discontinuedProperty() {
        return discontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued.set(discontinued);
    }

    @Column(name = "reason")
    public String getDiscontinuedReason() {
        return discontinuedReason.get();
    }

    public SimpleStringProperty discontinuedReasonProperty() {
        return discontinuedReason;
    }

    public void setDiscontinuedReason(String discontinuedReason) {
        this.discontinuedReason.set(discontinuedReason);
    }
}
