package com.EntityClasses;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javax.persistence.*;

/**
 * Created by Damma on 8/31/2017.
 */

@Embeddable
@Access(AccessType.PROPERTY)
public class Range {

    private SimpleIntegerProperty minPatientAge;
    private SimpleIntegerProperty maxPatientAge;
    private SimpleDoubleProperty lowerBound;
    private SimpleDoubleProperty upperBound;


    public Range() {

        this.minPatientAge = new SimpleIntegerProperty();
        this.maxPatientAge = new SimpleIntegerProperty();
        this.lowerBound = new SimpleDoubleProperty();
        this.upperBound = new SimpleDoubleProperty();

    }

//    @Column(name = "MinPatientAge")
    public int getMinPatientAge() {
        return minPatientAge.get();
    }

    public SimpleIntegerProperty minPatientAgeProperty() {
        return minPatientAge;
    }

    public void setMinPatientAge(int minPatientAge) {
        this.minPatientAge.set(minPatientAge);
    }

//    @Column(name = "MaxPatientAge")
    public int getMaxPatientAge() {
        return maxPatientAge.get();
    }

    public SimpleIntegerProperty maxPatientAgeProperty() {
        return maxPatientAge;
    }

    public void setMaxPatientAge(int maxPatientAge) {
        this.maxPatientAge.set(maxPatientAge);
    }

//    @Column(name = "lowerBound")
    public double getLowerBound() {
        return lowerBound.get();
    }

    public SimpleDoubleProperty lowerBoundProperty() {
        return lowerBound;
    }

    public void setLowerBound(double lowerBound) {
        this.lowerBound.set(lowerBound);
    }

//    @Column(name = "upperBound")
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
