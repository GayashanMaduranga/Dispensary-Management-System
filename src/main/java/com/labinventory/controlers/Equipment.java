package com.labinventory.controlers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import javax.xml.soap.Name;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by chamara on 9/17/2017.
 */
@Entity
//@Table(name ="Equipment_Table")
@Access(AccessType.PROPERTY)
public class Equipment {


    StringProperty name;
    IntegerProperty id;
    IntegerProperty rquant;
    IntegerProperty addquantity;
    IntegerProperty totquantity;
    StringProperty supplier;
    Date lifetime;
    Date today;


    public Equipment() {
        this.name = new SimpleStringProperty();
        this.id = new SimpleIntegerProperty();
        this.rquant = new SimpleIntegerProperty();
        this.addquantity = new SimpleIntegerProperty();
        this.totquantity = new SimpleIntegerProperty();
        this.supplier = new SimpleStringProperty();


    }

    @Id
    @Column(name = "Equipment_Name")
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;


    }

    @Column(name = "Equipment_ID")

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    @Column(name = "Equipment_RemainingQty")

    public int getRquant() {
        return rquant.get();
    }

    public IntegerProperty rquantProperty() {
        return rquant;
    }

    public void setRquant(int rquant) {
        this.rquant.set(rquant);
    }

    @Column(name = "Equipment_AddQty")

    public int getAddquantity() {
        return addquantity.get();
    }

    public IntegerProperty addquantityProperty() {
        return addquantity;
    }

    public void setAddquantity(int addquantity) {
        this.addquantity.set(addquantity);
    }

    @Column(name = "Equipment_TotalQty")

    public int getTotquantity() {
        return totquantity.get();
    }

    public IntegerProperty totquantityProperty() {
        return totquantity;
    }

    public void setTotquantity(int totquantity) {
        this.totquantity.set(totquantity);
    }

    @Column(name = "Equipment_Supplier")

    public String getSupplier() {
        return supplier.get();
    }

    public StringProperty supplierProperty() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier.set(supplier);
    }

    @Column(name ="Equipment_Lifetime")

    public Date getLifetime() {
        return lifetime;
    }

    public void setLifetime(Date lifetime) {
        this.lifetime = lifetime;
    }

    @Column(name = "Equipment_Today")

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }
}
