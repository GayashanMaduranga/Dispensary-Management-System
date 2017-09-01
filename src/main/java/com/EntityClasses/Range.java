package com.EntityClasses;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javax.persistence.*;

/**
 * Created by Damma on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "Range", schema = "entitydb")
public class Range {

    private SimpleIntegerProperty patientAge;
    private SimpleDoubleProperty lowerBound;
    private SimpleDoubleProperty upperBound;


    public Range() {

        this.patientAge = new SimpleIntegerProperty();
        this.lowerBound = new SimpleDoubleProperty();
        this.upperBound = new SimpleDoubleProperty();

    }

    @Column(name = "patientAge")
    public int getPatientAge() {
        return patientAge.get();
    }

    public SimpleIntegerProperty patientAgeProperty() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge.set(patientAge);
    }

    @Column(name = "lowerBound")
    public double getLowerBound() {
        return lowerBound.get();
    }

    public SimpleDoubleProperty lowerBoundProperty() {
        return lowerBound;
    }

    public void setLowerBound(double lowerBound) {
        this.lowerBound.set(lowerBound);
    }

    @Column(name = "upperBound")
    public double getUpperBound() {
        return upperBound.get();
    }

    public SimpleDoubleProperty upperBoundProperty() {
        return upperBound;
    }

    public void setUpperBound(double upperBound) {
        this.upperBound.set(upperBound);
    }
}
