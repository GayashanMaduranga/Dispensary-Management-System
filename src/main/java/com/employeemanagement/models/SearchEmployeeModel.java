package com.employeemanagement.models;

import com.EntityClasses.Employee;
import com.EntityClasses.Staff;
import com.common.ScreenController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import org.controlsfx.control.Notifications;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

@SuppressWarnings("Duplicates")
public class SearchEmployeeModel {

    private static Session session = ScreenController.getSession();

    private static List<Employee> staffMemberList = null;

    public static List<Employee> getEmployees() {


        Task<List<Employee>> task = new Task<List<Employee>>() {
            @Override
            protected List<Employee> call() throws Exception {
                try {
                    session.flush();
                    session.clear();

                    session.beginTransaction();
                    Query query = session.createQuery("select s from Employee s");
                    staffMemberList = query.list();
                    session.getTransaction().commit();


                } catch (Exception e) {

                    Platform.runLater(() -> Notifications.create()
                            .title("Error retrieving Data")
                            .text("please check and try to insert again")
                            .darkStyle()
                            .showError());


                }
                return staffMemberList;
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


        return staffMemberList;
    }


    public static void deleteEmployee(Employee emp) {


        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    session.flush();
                    session.clear();

                    session.beginTransaction();
                   session.delete(emp);
                    session.getTransaction().commit();


                } catch (Exception e) {

                    Platform.runLater(() -> Notifications.create()
                            .title("Error Deleting Data")
                            .text("please restart the programme")
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

}
