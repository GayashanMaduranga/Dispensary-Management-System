package com.EntityClasses;

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
@Table(name = "Patient")
public class Patient {


    private SimpleIntegerProperty pId;
    private SimpleStringProperty pname;
    private Date DOB;
    private SimpleStringProperty email;
    private SimpleStringProperty address;
    private SimpleStringProperty occupation;
    private SimpleStringProperty contactNumber;
    private SimpleStringProperty pastIllnesses;
    private SimpleStringProperty medicationHistory;
    private SimpleStringProperty familyHistory;
    private SimpleStringProperty habits;

    private List<LabTestOrder> labTestOrders;
    private List<Complaint> complaints;

    private List<Prescription> prescriptions;

    private List<Appointment> appointments;

    public Patient() {
        this.pId = new SimpleIntegerProperty();
        this.pname = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.occupation = new SimpleStringProperty();
        this.contactNumber = new SimpleStringProperty();
        this.pastIllnesses = new SimpleStringProperty();
        this.medicationHistory = new SimpleStringProperty();
        this.familyHistory = new SimpleStringProperty();
        this.habits = new SimpleStringProperty();

        this.labTestOrders = new ArrayList<>();
        this.complaints = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    //pname
    @Column(name = "pname")
    public String getPname() {
        return pname.get();
    }

    public void setPname(String pname) {
        this.pname.set(pname);
    }

    public SimpleStringProperty pnameProperty() {
        return pname;
    }

    //DOB
    @Column(name = "DOB")
    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }


    //email
    @Column(name = "email")
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    //address
    @Column(name = "address")
    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    //occupation
    @Column(name = "occupation")
    public String getOccupation() {
        return occupation.get();
    }

    public void setOccupation(String occupation) {
        this.occupation.set(occupation);
    }

    public SimpleStringProperty occupationProperty() {
        return occupation;
    }

    //contactNo
    @Column(name = "contactNo", length = 10)
    public String getContactNumber() {
        return contactNumber.get();
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber.set(contactNumber);
    }

    public SimpleStringProperty contactNumberProperty() {
        return contactNumber;
    }

    //pastIllnesses
    @Column(name = "pastIllnesses", columnDefinition = "TEXT")
    public String getPastIllnesses() {
        return pastIllnesses.get();
    }

    public void setPastIllnesses(String pastIllnesses) {
        this.pastIllnesses.set(pastIllnesses);
    }

    public SimpleStringProperty pastIllnessesProperty() {
        return pastIllnesses;
    }

    //medicationHistory
    @Lob
    @Column(name = "medicationHistory")
    public String getMedicationHistory() {
        return medicationHistory.get();
    }

    public void setMedicationHistory(String medicationHistory) {
        this.medicationHistory.set(medicationHistory);
    }

    public SimpleStringProperty medicationHistoryProperty() {
        return medicationHistory;
    }

    //familyHistory
    @Lob
    @Column(name = "familyHistory")
    public String getFamilyHistory() {
        return familyHistory.get();
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory.set(familyHistory);
    }

    public SimpleStringProperty familyHistoryProperty() {
        return familyHistory;
    }

    //habits
    @Lob
    @Column(name = "habits")
    public String getHabits() {
        return habits.get();
    }

    public void setHabits(String habits) {
        this.habits.set(habits);
    }

    public SimpleStringProperty habitsProperty() {
        return habits;
    }

    @Id
    @Column(name = "pID")
    @GeneratedValue
    public int getpId() {
        return pId.get();
    }

    public void setpId(int pId) {
        this.pId.set(pId);
    }

    public SimpleIntegerProperty pIdProperty() {
        return pId;
    }

    @OneToMany(mappedBy = "patient")
    public List<LabTestOrder> getLabTestOrders() {
        return labTestOrders;
    }

    public void setLabTestOrders(List<LabTestOrder> labTestOrders) {
        this.labTestOrders = labTestOrders;
    }

//    @Column(name = "name", columnDefinition = "TEXT")


    @ElementCollection
    public List<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }


    //One to Many uni directional
    @OneToMany(cascade = CascadeType.ALL)
    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }
}
