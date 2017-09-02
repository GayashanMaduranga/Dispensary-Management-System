package com.EntityClasses;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Damma on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "Complaint", schema = "entitydb")
public class Complaint {

    private SimpleStringProperty type;
    private Date Date;
    private SimpleStringProperty onset;
    private SimpleStringProperty associations;
    private SimpleIntegerProperty severity;
    private SimpleStringProperty relievingFactors;
    private SimpleStringProperty exacerbatingFactors;
    private SimpleStringProperty radiation;

    public Complaint() {

        this.type = new SimpleStringProperty();
        this.onset = new SimpleStringProperty();
        this.severity = new SimpleIntegerProperty();
        this.associations = new SimpleStringProperty();
        this.radiation = new SimpleStringProperty();
        this.relievingFactors = new SimpleStringProperty();
        this.exacerbatingFactors = new SimpleStringProperty();

    }

    //type
    @Column(name = "type")
    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    //date
    @Column(name = "date")
    public java.sql.Date getDate() {
        return Date;
    }

    public void setDate(java.sql.Date date) {
        Date = date;
    }

    //onset
    @Column(name = "onset")
    public String getOnset() {
        return onset.get();
    }

    public void setOnset(String onset) {
        this.onset.set(onset);
    }

    public SimpleStringProperty onsetProperty() {
        return onset;
    }

    //associations
    @Column(name = "associations", columnDefinition = "TEXT")
    public String getAssociations() {
        return associations.get();
    }

    public void setAssociations(String associations) {
        this.associations.set(associations);
    }

    public SimpleStringProperty associationsProperty() {
        return associations;
    }

    //severity
    @Column(name = "severity")
    public int getSeverity() {
        return severity.get();
    }

    public void setSeverity(int severity) {
        this.severity.set(severity);
    }

    public SimpleIntegerProperty severityProperty() {
        return severity;
    }

    //relieving
    @Column(name = "relieving", columnDefinition = "TEXT")
    public String getRelievingFactors() {
        return relievingFactors.get();
    }

    public void setRelievingFactors(String relievingFactors) {
        this.relievingFactors.set(relievingFactors);
    }

    public SimpleStringProperty relievingFactorsProperty() {
        return relievingFactors;
    }

    //exacerbating
    @Column(name = "exacerbating", columnDefinition = "TEXT")
    public String getExacerbatingFactors() {
        return exacerbatingFactors.get();
    }

    public void setExacerbatingFactors(String exacerbatingFactors) {
        this.exacerbatingFactors.set(exacerbatingFactors);
    }

    public SimpleStringProperty exacerbatingFactorsProperty() {
        return exacerbatingFactors;
    }

    //radiation
    @Column(name = "radiation")
    public String getRadiation() {
        return radiation.get();
    }

    public void setRadiation(String radiation) {
        this.radiation.set(radiation);
    }

    public SimpleStringProperty radiationProperty() {
        return radiation;
    }
}
