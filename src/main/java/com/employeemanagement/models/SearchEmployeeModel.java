package com.employeemanagement.models;

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

    private static List<Staff> staffMemberList = null;

    public static List<Staff> getEmployees() {


        Task<List<Staff>> task = new Task<List<Staff>>() {
            @Override
            protected List<Staff> call() throws Exception {
                try {
                    session.beginTransaction();
                    Query query = session.createQuery("select s from Staff s");
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
}
