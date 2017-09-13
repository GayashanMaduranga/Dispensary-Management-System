package com.EntityClasses;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DAMMA on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "SupplyOrder")
public class SupplyOrder {


    private SimpleIntegerProperty orderID;
    private Date date;
    private SimpleDoubleProperty total;

    private List<PharmacyBatch> pharmacyBatches;
    private List<Equipment> equipmentList;


    private Supplier supplier;

    public SupplyOrder() {
        this.orderID = new SimpleIntegerProperty();
        this.total = new SimpleDoubleProperty();
        this.pharmacyBatches  = new ArrayList<>();
        this.equipmentList = new ArrayList<>();
    }


    //Date


    @Id
    @Column(name = "orderID")
    @GeneratedValue
    public int getOrderID() {
        return orderID.get();
    }

    public SimpleIntegerProperty orderIDProperty() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID.set(orderID);
    }

    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "total")
    public double getTotal() {
        return total.get();
    }

    public SimpleDoubleProperty totalProperty() {
        return total;
    }

    public void setTotal(double total) {
        this.total.set(total);
    }

    //uni directional one to many
    @ManyToOne(cascade = CascadeType.ALL)
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<PharmacyBatch> getPharmacyBatches() {
        return pharmacyBatches;
    }

    public void setPharmacyBatches(List<PharmacyBatch> pharmacyBatches) {
        this.pharmacyBatches = pharmacyBatches;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }
}
