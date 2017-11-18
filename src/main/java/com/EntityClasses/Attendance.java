package com.EntityClasses;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by gayashan on 9/12/2017.
 */
@Entity
@Access(AccessType.FIELD)
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Timestamp startTime;
    private Timestamp endTime;
    @ManyToOne
    private Staff staff;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}

