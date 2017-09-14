package com.EntityClasses;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by DAMMA on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "Prescription")
public class Prescription {


    private SimpleIntegerProperty presId;
    private Date date;
    private DoubleProperty quantity;

    private PharmacyItem pharmacyItem;

    public Prescription() {
        this.presId = new SimpleIntegerProperty();
        this.quantity = new SimpleDoubleProperty();
    }


    //Date
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Id
    @Column(name = "presID")
    @GeneratedValue
    public int getPresId() {
        return presId.get();
    }

    public void setPresId(int presId) {
        this.presId.set(presId);
    }

    public SimpleIntegerProperty presIdProperty() {
        return presId;
    }

    public double getQuantity() {
        return quantity.get();
    }

    public DoubleProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity.set(quantity);
    }


    @OneToOne(cascade = CascadeType.ALL)
    public PharmacyItem getPharmacyItem() {
        return pharmacyItem;
    }

    public void setPharmacyItem(PharmacyItem pharmacyItem) {
        this.pharmacyItem = pharmacyItem;
    }
}
