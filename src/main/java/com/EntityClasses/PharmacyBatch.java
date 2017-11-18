package com.EntityClasses;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damma on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "PharmacyBatch")
public class PharmacyBatch extends RecursiveTreeObject<PharmacyBatch> {

    private SimpleIntegerProperty batchId;
    private Date manufacturingDate;
    private Date expiryDate;
    private SimpleDoubleProperty purchasingPrice;
    private PharmacyItem pharmacyItem;
    private List<PharmacyLineItem> pharmacyLineItems;
    private IntegerProperty quantity;

    public PharmacyBatch() {

        this.batchId = new SimpleIntegerProperty();
        this.purchasingPrice = new SimpleDoubleProperty();
        this.quantity = new SimpleIntegerProperty();
        this.pharmacyItem = new PharmacyItem();
        this.pharmacyLineItems = new ArrayList<>();

    }

    @Id
    @Column(name = "batchId")
    @GeneratedValue
    public int getBatchId() {
        return batchId.get();
    }

    public SimpleIntegerProperty batchIdProperty() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId.set(batchId);
    }

    @Column(name = "manufacturingDate")
    public Date getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(Date manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    @Column(name = "expiryDate")
    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public SimpleStringProperty expiryDateProperty(){
        SimpleStringProperty dateString = new SimpleStringProperty();
        dateString.set(this.expiryDate.toString());
        return dateString;
    }

    @Column(name = "purchasingPrice")
    public double getPurchasingPrice() {
        return purchasingPrice.get();
    }

    public SimpleDoubleProperty purchasingPriceProperty() {
        return purchasingPrice;
    }

    public void setPurchasingPrice(double purchasingPrice) {
        this.purchasingPrice.set(purchasingPrice);
    }


    //One to One uni directional
    @ManyToOne(cascade = CascadeType.PERSIST)
    public PharmacyItem getPharmacyItem() {
        return pharmacyItem;
    }
    public void setPharmacyItem(PharmacyItem pharmacyItem) {
        this.pharmacyItem = pharmacyItem;
    }

    @OneToMany(mappedBy = "pharmacyBatch")
    public List<PharmacyLineItem> getPharmacyLineItems() {
        return pharmacyLineItems;
    }

    public void setPharmacyLineItems(List<PharmacyLineItem> pharmacyLineItems) {
        this.pharmacyLineItems = pharmacyLineItems;
    }


}
