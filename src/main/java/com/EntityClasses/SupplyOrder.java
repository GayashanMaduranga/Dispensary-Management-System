package com.EntityClasses;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

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
public class SupplyOrder extends RecursiveTreeObject<SupplyOrder> {


    private SimpleIntegerProperty orderID;
    private Date date;
    private SimpleDoubleProperty total;
    private SimpleStringProperty name;
    private SimpleIntegerProperty qty;
    private SimpleStringProperty supplyname;

    private SimpleBooleanProperty hasArrived;

    private List<PharmacyBatch> pharmacyBatches;
    private List<Equipment> equipmentList;


    private Supplier supplier;

    public SupplyOrder() {
        this.orderID = new SimpleIntegerProperty();
        this.total = new SimpleDoubleProperty();
        this.hasArrived = new SimpleBooleanProperty();
        this.name = new SimpleStringProperty();
        this.supplyname = new SimpleStringProperty();
        this.qty = new SimpleIntegerProperty();
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



    @Column(name = "ItemName")
    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }


    @Column(name = "Qty")
    public int getQty() {
        return qty.get();
    }

    public SimpleIntegerProperty qtyProperty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty.set(qty);
    }






    @Column(name = "SupplyName")
    public String getSupplyname() {
        return supplyname.get();
    }

    public SimpleStringProperty supplynameProperty() {
        return supplyname;
    }

    public void setSupplyname(String supplyname) {
        this.supplyname.set(supplyname);
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

    @Column(name = "isReceived")
    public boolean getHasArrived() {
        return hasArrived.get();
    }

    public SimpleBooleanProperty hasArrivedProperty() {
        return hasArrived;
    }

    public void setHasArrived(boolean hasArrived) {
        this.hasArrived.set(hasArrived);
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
