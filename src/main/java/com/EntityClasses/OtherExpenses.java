package com.EntityClasses;

import javafx.beans.property.*;

import javax.persistence.*;

/**
 * Created by gayashan on 9/16/2017.
 */
@Entity
@Access(AccessType.PROPERTY)
public class OtherExpenses {

    IntegerProperty id;
    StringProperty name;
    StringProperty description;
    DoubleProperty amount;
    StringProperty remark;


    public OtherExpenses() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.remark = new SimpleStringProperty();
        this.amount = new SimpleDoubleProperty();
    }


    @Id
    @GeneratedValue
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Lob
    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public double getAmount() {
        return amount.get();
    }

    public DoubleProperty amountProperty() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }

    @Lob
    public String getRemark() {
        return remark.get();
    }

    public StringProperty remarkProperty() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark.set(remark);
    }
}
