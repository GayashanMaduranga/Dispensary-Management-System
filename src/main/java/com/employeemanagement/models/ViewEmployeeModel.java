package com.employeemanagement.models;

import com.EntityClasses.Attendance;
import com.EntityClasses.Employee;
import com.EntityClasses.Staff;
import com.common.ScreenController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import jfxtras.scene.control.agenda.Agenda;
import org.controlsfx.control.Notifications;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class ViewEmployeeModel {

    private static Session session = ScreenController.getSession();

    public static void updateEmployee(Employee employee){

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {

                    session.flush();
                    session.clear();

                    session.beginTransaction();
                    session.update(employee);
                    session.getTransaction().commit();

                    Platform.runLater(() ->  Notifications.create()
                            .title("Updated")
                            .text("successfully Updated the recode")
                            .showInformation());
                }catch (Exception e){

                    Platform.runLater(() ->  Notifications.create()
                            .title("Error Update Data")
                            .text("please check and try to update again")
                            .darkStyle()
                            .showError());

                }
                return null;
            }
        };

        Thread addEmpThread = new Thread(task);
        addEmpThread.setDaemon(true);
        addEmpThread.start();

    }



    public int addNewAttendance(Agenda.AppointmentImplLocal newAppointment,Employee employee){


        Attendance attendance = new Attendance();

        attendance.setStartTime(Timestamp.valueOf(newAppointment.getStartLocalDateTime()));
        attendance.setEndTime(Timestamp.valueOf(newAppointment.getEndLocalDateTime()));
        attendance.setStaff((Staff)employee);

        session.beginTransaction();
        session.save(attendance);
        session.getTransaction().commit();

        //returns generated id value
        return attendance.getId();







    }


    private List<Attendance> getAppointmentEntities(LocalDateTime startTime, LocalDateTime endTime,Employee e){


        String hql = "FROM Attendance a  where a.staff = :staff and a.startTime between :startTime and :endTime ";
        Query query = session.createQuery(hql);
        query.setParameter("staff",e);

        query.setParameter("startTime",Timestamp.valueOf(startTime));
        query.setParameter("endTime",Timestamp.valueOf(endTime));

        List<Attendance> results = query.list();



        return results;
    }

    public List<AttendanceImpl> getAppointments(LocalDateTime startTime,LocalDateTime endTime,Employee e){
        List<Attendance> entityList =getAppointmentEntities(startTime,endTime,e);
        List<AttendanceImpl> attendanceList = new ArrayList<>();



        for(Attendance entity:entityList){

            Agenda.AppointmentImplLocal appointmentImplLocal= new AttendanceImpl()
                    .withStartLocalDateTime(entity.getStartTime().toLocalDateTime())
                    .withEndLocalDateTime(entity.getEndTime().toLocalDateTime())
                    .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1"));


            AttendanceImpl appointment = (AttendanceImpl)appointmentImplLocal;
            appointment.setId(entity.getId());
            attendanceList.add(appointment);


        }

        return attendanceList;
    }


    public void deleteAppointment(int id){

        Attendance entity = (Attendance) session.get(Attendance.class,id);

        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }


    public void updateAppointment(AttendanceImpl newAppointment) {

        Attendance entity = (Attendance) session.get(Attendance.class,newAppointment.getId());

        entity.setStartTime(Timestamp.valueOf(newAppointment.getStartLocalDateTime()));
        entity.setEndTime(Timestamp.valueOf(newAppointment.getEndLocalDateTime()));


        if(entity==null)
            System.out.println("NULL");
        else{
            System.out.println(entity.getId() + entity.getStartTime().toLocalDateTime().toLocalDate().toString());
        }
        System.out.println(newAppointment.toString());

        try {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
