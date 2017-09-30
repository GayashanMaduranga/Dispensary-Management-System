package com.EntityClasses;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;

import javax.persistence.*;

/**
 * Created by DAMMA on 8/31/2017.
 */

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "ITEM_TYPE", discriminatorType = DiscriminatorType.STRING)
public class Item extends RecursiveTreeObject<Item> {


    private SimpleIntegerProperty itemID;
    private SimpleIntegerProperty stock;

    public Item() {

        this.itemID = new SimpleIntegerProperty();
        this.stock = new SimpleIntegerProperty();
    }

    @Id
    @GeneratedValue
    public int getItemID() {
        return itemID.get();
    }

    public SimpleIntegerProperty itemIDProperty() {
        return itemID;
    }

    public void setItemID(int equipmentID) {
        this.itemID.set(equipmentID);
    }

    @Column(name = "stock")
    public int getStock() {
        return stock.get();
    }

    public SimpleIntegerProperty stockProperty() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

}
