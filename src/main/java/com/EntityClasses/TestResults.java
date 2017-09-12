package com.EntityClasses;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by gayashan on 9/12/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
public class TestResults {



    private int id;

    private DoubleProperty result;


    TestField testField;



    LabTestOrder labTestOrder;

    public TestResults() {
        result = new SimpleDoubleProperty();
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    @Column(name = "result")
    public double getResult() {
        return result.get();
    }

    public DoubleProperty resultProperty() {
        return result;
    }

    public void setResult(double result) {
        this.result.set(result);
    }

    //Uni directional one to one relationship
    @OneToOne
    public TestField getTestField() {
        return testField;
    }

    public void setTestField(TestField testField) {
        this.testField = testField;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public LabTestOrder getLabTestOrder() {
        return labTestOrder;
    }

    public void setLabTestOrder(LabTestOrder labTestOrder) {
        this.labTestOrder = labTestOrder;
    }
}
