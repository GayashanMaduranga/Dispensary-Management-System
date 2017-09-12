package EmployeeManagement;

import com.EntityClasses.Staff;
import db.UserSession;
import org.hibernate.Session;
import org.junit.Test;

import java.sql.Date;

/**
 * Created by gayashan on 9/13/2017.
 */
public class EmployeeManagementTest {

    @Test
    public void canAddStaffMember(){

        Session session = UserSession.getSession();

        Staff staff = new Staff();
        staff.setDateOfAppointment(new Date(System.currentTimeMillis()));
        staff.setName("ABC");
        staff.setDateOfBirth(Date.valueOf("1980-03-01"));
        staff.setContactNumber("0345234");
        staff.setGender("M");



        session.beginTransaction();
        session.save(staff);
        session.getTransaction().commit();
        session.close();


    }

}
