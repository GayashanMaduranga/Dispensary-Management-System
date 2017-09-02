package com.EntityClasses;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Damma on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "PharmacyLineItem", schema = "entitydb")
public class PharmacyLineItem {

    private SimpleIntegerProperty quantity;
    private SimpleDoubleProperty subTotal;



    public PharmacyLineItem() {

        this.quantity = new SimpleIntegerProperty();
        this.subTotal = new SimpleDoubleProperty();

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
}
