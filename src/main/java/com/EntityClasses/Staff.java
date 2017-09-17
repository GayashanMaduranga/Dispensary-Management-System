package com.EntityClasses;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gayashan on 9/12/2017.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Staff extends Employee {


    private Date dateOfAppointment;
    private StringProperty jobRole;

    private List<Attendance> attendanceList;

    private List<Leave> leaveList;

    private List<Loan> loanList;

    private List<Payroll> payrolls;

    public Staff() {
        this.attendanceList = new ArrayList<>();
        this.leaveList = new ArrayList<>();
        this.payrolls = new ArrayList<>();
        this.jobRole = new SimpleStringProperty();
    }

    @Column(name = "DATE_OF_APPOINTMENT",nullable = false)

    public Date getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(Date dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }


    @ElementCollection
    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    @ElementCollection
    public List<Leave> getLeaveList() {
        return leaveList;
    }

    public void setLeaveList(List<Leave> leaveList) {
        this.leaveList = leaveList;
    }


    @OneToMany(cascade = CascadeType.ALL)
    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }


    @OneToMany(cascade = CascadeType.ALL)
    public List<Payroll> getPayrolls() {
        return payrolls;
    }

    public void setPayrolls(List<Payroll> payrolls) {
        this.payrolls = payrolls;
    }

    public String getJobRole() {
        return jobRole.get();
    }

    public StringProperty occupationProperty() {
        return jobRole;
    }

    public void setJobRole(String occupation) {
        this.jobRole.set(occupation);
    }


}
