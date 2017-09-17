package com.EntityClasses;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damma on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "PharmacyLineItem")
public class PharmacyLineItem {

    private SimpleIntegerProperty id;
    private SimpleIntegerProperty quantity;
    private SimpleDoubleProperty subTotal;


    private PharmacyBatch pharmacyBatch;

    public PharmacyLineItem() {

        this.quantity = new SimpleIntegerProperty();
        this.subTotal = new SimpleDoubleProperty();
        this.id = new SimpleIntegerProperty();

    }

    @Id
    @GeneratedValue
    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    @Column(name = "quantity")
    public int getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    @Column(name = "subTotal")
    public double getSubTotal() {
        return subTotal.get();
    }

    public SimpleDoubleProperty subTotalProperty() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {

        this.subTotal.set(subTotal);
    }


    //Bi directional one to many
    @ManyToOne(cascade = CascadeType.ALL)
    public PharmacyBatch getPharmacyBatch() {
        return pharmacyBatch;
    }

    public void setPharmacyBatch(PharmacyBatch pharmacyBatch) {
        this.pharmacyBatch = pharmacyBatch;
    }
}
