package EmployeeManagement;

import com.EntityClasses.Attendance;
import com.EntityClasses.Doctor;
import com.EntityClasses.Leave;
import com.EntityClasses.Staff;
import db.UserSession;
import org.hibernate.Session;
import org.junit.Test;

import java.sql.Date;
import java.sql.Timestamp;

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
        staff.setEmployeeid(2);



        session.beginTransaction();
        session.save(staff);
        session.getTransaction().commit();
        session.close();


    }

    @Test
    public void canAddStaffAttendence(){

        Session session = UserSession.getSession();

        Staff staff =(Staff)session.get(Staff.class,1);

        Attendance attendance = new Attendance();
        attendance.setArrivalTime(new Timestamp(System.currentTimeMillis()));
        attendance.setLeaveTime(new Timestamp(System.currentTimeMillis()));
        attendance.setDate(new Date(System.currentTimeMillis()));

        staff.getAttendanceList().add(attendance);




        session.beginTransaction();
        session.update(staff);
        session.getTransaction().commit();
        session.close();


    }

    @Test
    public void canAddDoctor(){

        Session session = UserSession.getSession();
        Doctor doc = new Doctor();

        doc.setName("ABC");
        doc.setDateOfBirth(Date.valueOf("1980-03-01"));
        doc.setContactNumber("0345234");
        doc.setGender("M");
        doc.setEmployeeid(1);



        session.beginTransaction();
        session.save(doc);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void canApplyLeave(){

        Session session = UserSession.getSession();

        Staff staff =(Staff)session.get(Staff.class,1);


        Leave leave = new Leave();
        leave.setDate(Date.valueOf("2017-10-10"));
        leave.setReason("Personal");

        staff.getLeaveList().add(leave);




        session.beginTransaction();
        session.update(staff);
        session.getTransaction().commit();
        session.close();


    }


    @Test
    public void canApplyLoan(){

        Session session = UserSession.getSession();

        Staff staff =(Staff)session.get(Staff.class,1);







        session.beginTransaction();
        session.update(staff);
        session.getTransaction().commit();
        session.close();
    }

}
