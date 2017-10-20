package com.employeemanagement.models;

import com.EntityClasses.Employee;
import com.common.ScreenController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import org.controlsfx.control.Notifications;
import org.hibernate.Session;

@SuppressWarnings("Duplicates")
public class AddEmployeeModel {

    private static Session session = ScreenController.getSession();

    public static void addEmployee(Employee employee){

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    session.beginTransaction();
                    session.save(employee);
                    session.getTransaction().commit();

                    Platform.runLater(() ->  Notifications.create()
                            .title("Inserted")
                            .text("successfully Inserted To the Database")
                            .showInformation());
                }catch (Exception e){

                    Platform.runLater(() ->  Notifications.create()
                            .title("Error Inserting Data")
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

    }
}
