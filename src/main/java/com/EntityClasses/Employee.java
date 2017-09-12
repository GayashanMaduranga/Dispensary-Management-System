package com.EntityClasses;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gayashan on 9/10/2017.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Access(AccessType.PROPERTY)
public class Employee {

    private IntegerProperty employeeid;
    private StringProperty name;
    private StringProperty gender;
    private Date dateOfBirth;
    private StringProperty email;
    private StringProperty contactNumber;
    private StringProperty qualifications;


    private List<Attendance> attendanceList;


    public Employee() {
        employeeid = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        gender = new SimpleStringProperty();
        email = new SimpleStringProperty();
        contactNumber = new SimpleStringProperty();
        qualifications = new SimpleStringProperty();

        attendanceList = new ArrayList<>();
    }

    @ElementCollection
    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    @Id
    public int getEmployeeid() {
        return employeeid.get();
    }

    public IntegerProperty employeeidProperty() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid.set(employeeid);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getContactNumber() {
        return contactNumber.get();
    }

    public StringProperty contactNumberProperty() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber.set(contactNumber);
    }

    @Lob
    public String getQualifications() {
        return qualifications.get();
    }

    public StringProperty qualificationsProperty() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications.set(qualifications);
    }
}
