package com.EntityClasses;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;

/**
 * Created by Damma on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "MainTest", schema = "entitydb")
public class MainTest {

    private SimpleIntegerProperty tId;
    private SimpleStringProperty testName;
    private SimpleDoubleProperty testPrice;



    public MainTest() {

        this.tId = new SimpleIntegerProperty();
        this.testName = new SimpleStringProperty();
        this.testPrice = new SimpleDoubleProperty();

    }

    @Id
    @Column(name = "sID")
    @GeneratedValue
    public int gettId() {
        return tId.get();
    }

    public SimpleIntegerProperty tIdProperty() {
        return tId;
    }

    public void settId(int tId) {
        this.tId.set(tId);
    }

    @Column(name = "testName")
    public String getTestName() {
        return testName.get();
    }

    public SimpleStringProperty testNameProperty() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName.set(testName);
    }

    @Column(name = "testPrice")
    public double getTestPrice() {
        return testPrice.get();
    }

    public SimpleDoubleProperty testPriceProperty() {
        return testPrice;
    }

    public void setTestPrice(double testPrice) {
        this.testPrice.set(testPrice);
    }
}
