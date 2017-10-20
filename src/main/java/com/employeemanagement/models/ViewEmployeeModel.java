package com.employeemanagement.models;

import com.EntityClasses.Employee;
import com.common.ScreenController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import org.controlsfx.control.Notifications;
import org.hibernate.Session;

@SuppressWarnings("Duplicates")
public class ViewEmployeeModel {

    private static Session session = ScreenController.getSession();

    public static void updateEmployee(Employee employee){

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
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
}
