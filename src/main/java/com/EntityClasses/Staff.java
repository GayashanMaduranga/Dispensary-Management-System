package com.EntityClasses;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gayashan on 9/12/2017.
 */
@Entity
@Access(AccessType.FIELD)
public class Staff extends Employee {


    @Column(name = "DATE_OF_APPOINTMENT",nullable = false)
    private Date dateOfAppointment;

    @ElementCollection
    private List<Attendance> attendanceList;

    public Staff() {
        attendanceList = new ArrayList<>();
    }

    public Date getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(Date dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }


    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

}
