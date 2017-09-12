package com.EntityClasses;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damma on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
//@Table(name = "Main_Test")
public class MainTest {

    private SimpleIntegerProperty tId;
    private SimpleStringProperty testName;
    private SimpleDoubleProperty testPrice;



//    private List<LabTestOrder> labTestOrders;





    private List<TestField> testFields;


    public MainTest() {

        this.tId = new SimpleIntegerProperty();
        this.testName = new SimpleStringProperty();
        this.testPrice = new SimpleDoubleProperty();
//        this.labTestOrders = new ArrayList<>();
        this.testFields = new ArrayList<>();
    }



    @Id
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

//    @Column(name = "test_Name")
    public String getTestName() {
        return testName.get();
    }

    public SimpleStringProperty testNameProperty() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName.set(testName);
    }

//    @Column(name = "test_Price")
    public double getTestPrice() {
        return testPrice.get();
    }

    public SimpleDoubleProperty testPriceProperty() {
        return testPrice;
    }

    public void setTestPrice(double testPrice) {
        this.testPrice.set(testPrice);
    }


    //Uni directional one to many Relationship
    @OneToMany(cascade = CascadeType.ALL)
    public List<TestField> getTestFields() {
        return testFields;
    }

    public void setTestFields(List<TestField> testFields) {
        this.testFields = testFields;
    }

    //Bi directional one to many Relationship
//    @OneToMany(mappedBy = "mainTest")
//    public List<LabTestOrder> getLabTestOrders() {
//        return labTestOrders;
//    }
//
//    public void setLabTestOrders(List<LabTestOrder> labTestOrders) {
//        this.labTestOrders = labTestOrders;
//    }
}
