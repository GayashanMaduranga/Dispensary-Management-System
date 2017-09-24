package com.EntityClasses;

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
public class Machine extends Equipment{

    private SimpleIntegerProperty MachineId;
    private SimpleIntegerProperty servicePeriod;
    private SimpleStringProperty MachineName;
   // private Date DateLastServiced;

    private List<Maintenance> maintenances;

    public Machine() {

       this.MachineId = new SimpleIntegerProperty();
        this.servicePeriod = new SimpleIntegerProperty();
        this.MachineName = new SimpleStringProperty();
        this.maintenances = new ArrayList<>();

    }

   // @Id    .....................//Display Error//
    @GeneratedValue
    public int getMachineId() {return MachineId.get();}
    public SimpleIntegerProperty machineId() {return MachineId; }

    public void setMachineId(int machineId) {this.MachineId.set(machineId);}




    @Column(name = "servicePeriod")
    public int getServicePeriod() {
        return servicePeriod.get();
    }

    public SimpleIntegerProperty servicePeriodProperty() {
        return servicePeriod;
    }

    public void setServicePeriod(int servicePeriod) {
        this.servicePeriod.set(servicePeriod);
    }


    @Column(name = "MachineName")

    public String getMachineName() {  return MachineName.get();  }

    public SimpleStringProperty machineNameProperty() { return MachineName; }

    public void setMachineName(String machineName) { this.MachineName.set(machineName); }


   /* @Column(name = "DateLastServiced")
    public Date getDateLastServiced() {
        return DateLastServiced;
    }

    public void setDateLastServiced(Date dateLastServiced) {
        DateLastServiced = dateLastServiced;
    }*/


    @ElementCollection
    public List<Maintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }
}
