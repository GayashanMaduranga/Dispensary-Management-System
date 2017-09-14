package com.EntityClasses;

import javax.persistence.Embeddable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by gayashan on 9/12/2017.
 */
@Embeddable
public class Attendance {

    private Date date;
    private Timestamp arrivalTime;
    private Timestamp leaveTime;
    private int OT_Hours;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Timestamp getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Timestamp leaveTime) {
        this.leaveTime = leaveTime;
    }

    public int getOT_Hours() {
        return OT_Hours;
    }

    public void setOT_Hours(int OT_Hours) {
        this.OT_Hours = OT_Hours;
    }
}

