package com.EntityClasses;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
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
@DiscriminatorValue("Drug")
public class PharmacyItem extends Item {

//    private SimpleIntegerProperty phId;
    private SimpleStringProperty itemName;
//    private SimpleIntegerProperty stock;
    private SimpleIntegerProperty reorderLevel;
    private SimpleDoubleProperty MRP;
    private List<PharmacyBatch> batches;


    public PharmacyItem() {

//        this.phId = new SimpleIntegerProperty();
        this.itemName = new SimpleStringProperty();
//        this.stock = new SimpleIntegerProperty();
        this.reorderLevel = new SimpleIntegerProperty();
        this.MRP = new SimpleDoubleProperty();
        this.batches = new ArrayList<>();

    }

//    @Id
//    @Column(name = "phId")
//    @GeneratedValue
//    public int getPhId() {
//        return phId.get();
//    }
//
//    public SimpleIntegerProperty phIdProperty() {
//        return phId;
//    }
//
//    public void setPhId(int phId) {
//        this.phId.set(phId);
//    }

    @Column(name = "itemName")
    public String getItemName() {
        return itemName.get();
    }

    public SimpleStringProperty itemNameProperty() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }

//    @Column(name = "stock")
//    public int getStock() {
//        return stock.get();
//    }
//
//    public SimpleIntegerProperty stockProperty() {
//        return stock;
//    }
//
//    public void setStock(int stock) {
//        this.stock.set(stock);
//    }

    @Column(name = "reorderLevel")
    public int getReorderLevel() {
        return reorderLevel.get();
    }

    public SimpleIntegerProperty reorderLevelProperty() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel.set(reorderLevel);
    }

    @Column(name = "MRP")
    public double getMRP() {
        return MRP.get();
    }

    public SimpleDoubleProperty MRPProperty() {
        return MRP;
    }

    public void setMRP(double MRP) {
        this.MRP.set(MRP);
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pharmacyItem")
    public List<PharmacyBatch> getBatches() {
        return batches;
    }

    public void setBatches(List<PharmacyBatch> batches) {
        this.batches = batches;
    }
}
