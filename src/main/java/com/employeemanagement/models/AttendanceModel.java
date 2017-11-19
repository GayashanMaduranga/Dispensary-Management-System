package com.employeemanagement.models;

import com.EntityClasses.Attendance;
import com.EntityClasses.Employee;
import com.EntityClasses.Staff;
import com.common.ScreenController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import org.controlsfx.control.Notifications;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class AttendanceModel {
    private static Session session = ScreenController.getSession();

    public static Staff staff;

    public static void addArival(String code) {


        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {


                    Attendance attendance = new Attendance();
                    attendance.setStartTime(Timestamp.valueOf(LocalDateTime.now()));

                    session.flush();
                    session.clear();
                    staff = (Staff) session.get(Staff.class,Integer.parseInt(code));
                    attendance.setStaff(staff);
                    staff.getAttendanceList().add(attendance);

                    session.update(staff);
                    session.beginTransaction();
//                    Query query = session.createQuery("select s from Staff s join Attendance a where s.employeeid :id");
//                    staffMemberList = query.list();


                    session.getTransaction().commit();





                } catch (Exception e) {
                    e.printStackTrace();
                    Platform.runLater(() -> Notifications.create()
                            .title("Invalid Staff ID")
                            .text("please check and try to insert again")
                            .darkStyle()
                            .showError());


                }
                return null;
            }
        };

        Thread addEmpThread = new Thread(task);
        addEmpThread.setDaemon(true);
        addEmpThread.start();
        try {
            addEmpThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

    public static void addDeparture(String code) {


        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {


//                    Attendance attendance = new Attendance();
//                    attendance.setStartTime(Timestamp.valueOf(LocalDateTime.now()));

                    session.flush();
                    session.clear();
                    staff = (Staff) session.get(Staff.class,Integer.parseInt(code));

                    Query query = session.createQuery("from  Attendance a where a.staff = :staff and a.endTime is null");
                    query.setParameter("staff",staff);

                    List <Attendance> list = query.list();
                    for(Attendance a :list){
                        a.setEndTime(Timestamp.valueOf(LocalDateTime.now()));
                    }

                    session.update(staff);
                    session.beginTransaction();
//


                    session.getTransaction().commit();





                } catch (Exception e) {
                    e.printStackTrace();
                    Platform.runLater(() -> Notifications.create()
                            .title("Invalid Staff ID")
                            .text("please check and try to insert again")
                            .darkStyle()
                            .showError());


                }
                return null;
            }
        };

        Thread addEmpThread = new Thread(task);
        addEmpThread.setDaemon(true);
        addEmpThread.start();
        try {
            addEmpThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

    public static String getGreeting(){
        String greet = null;
        GregorianCalendar time = new GregorianCalendar();
        int hour = time.get(Calendar.HOUR_OF_DAY);

        if (hour < 12)
            greet = "Good Morning!";
        else if (hour < 17 && !(hour == 12))
            greet = "Good Afternoon";
        else if (hour == 12)
            greet = "Good Noon";
        else
            greet = "Good Evening";

        return greet;
    }


    }



