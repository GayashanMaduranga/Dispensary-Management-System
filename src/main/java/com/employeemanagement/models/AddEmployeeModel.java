package com.employeemanagement.models;

import com.EntityClasses.Employee;
import com.common.ScreenController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import org.controlsfx.control.Notifications;
import org.hibernate.Session;

import java.text.SimpleDateFormat;
import java.util.Random;

@SuppressWarnings("Duplicates")
public class AddEmployeeModel {

    private static Session session = ScreenController.getSession();

    public static void addEmployee(Employee employee){

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    session.flush();
                    session.clear();

                    employee.setEmployeeid(genarateID(employee));
                    session.beginTransaction();
                    session.save(employee);
                    session.getTransaction().commit();

                    session.flush();

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
                    e.printStackTrace();

                    try {
                        if(session.getTransaction().isActive())
                        session.getTransaction().rollback();
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }


                }
                return null;
            }
        };

        Thread addEmpThread = new Thread(task);
        addEmpThread.setDaemon(true);
        addEmpThread.start();

    }

    public static Integer genarateID(Employee e){

        SimpleDateFormat dt = new SimpleDateFormat("yyyy");;
       String job = e.getJobRole();
       String dob = dt.format(e.getDateOfBirth());
        String dateOfappointment = dt.format(e.getDateOfAppointment());



        String id=dob;
        id = id.concat(dateOfappointment);
        Double rand = Math.random()*100;
        int randInt = rand.intValue();
        id = id.concat(String.valueOf(randInt));


        return Integer.parseInt(id);
    }
}
