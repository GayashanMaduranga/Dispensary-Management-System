package com.EntityClasses;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
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
public class Patient extends RecursiveTreeObject<Patient> {


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
    private SimpleStringProperty gender;
    private SimpleStringProperty NIC;

    private List<LabTestOrder> labTestOrders;
    private List<Complaint> complaints;
    private List<Prescription> prescriptions;
    private List<Appointment> appointments;
    private List<Medication> medications;
    private List<Measure> measures;
    private List<Allergy> allergies;


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
        this.gender = new SimpleStringProperty();
        this.NIC = new SimpleStringProperty();


        this.labTestOrders = new ArrayList<>();
        this.complaints = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.measures = new ArrayList<>();
        this.allergies = new ArrayList<>();
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

    public SimpleStringProperty DOBProperty(){
        SimpleStringProperty dateString = new SimpleStringProperty();
        dateString.set(this.DOB.toString());
        return dateString;
    }

    @Column(name = "gender")
    public String getGender() {
        return gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    @Column(name = "NIC")
    public String getNIC() {
        return NIC.get();
    }

    public SimpleStringProperty NICProperty() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC.set(NIC);
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
    @Column(name = "contactNo")
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

    @OneToMany(cascade = CascadeType.ALL)
    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<Allergy> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<Allergy> allergies) {
        this.allergies = allergies;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }


}
