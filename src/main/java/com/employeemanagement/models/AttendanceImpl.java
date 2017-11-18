package com.employeemanagement.models;

import jfxtras.scene.control.agenda.Agenda;

public class AttendanceImpl extends Agenda.AppointmentImplLocal {

    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
