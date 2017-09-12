package patientManagement;

import com.EntityClasses.Complaint;
import com.EntityClasses.Patient;
import com.EntityClasses.Prescription;
import db.UserSession;
import org.hibernate.Session;
import org.junit.Test;

import javax.persistence.Column;
import java.sql.Date;

/**
 * Created by gayashan on 9/12/2017.
 */
public class PatientManagementTest {

    @Test
    public void canAddPatient(){

        Patient patient = new Patient();
        patient.setPname("SomeOne");
        patient.setContactNumber("012345566");
        patient.setAddress("12/34 Anuradapura");
        patient.setDOB(Date.valueOf("1990-03-03"));
        patient.setEmail("Someting@mail.com");
        patient.setFamilyHistory("adlfjasldf ;lfkjas;d sjfdlajd lfsjdf sdlfhjhdfhasdif hadfkjh akdjfh kasdjfh kalsdfh");


        Session session = UserSession.getSession();

        session.beginTransaction();

        session.save(patient);

        session.getTransaction().commit();
        session.close();


    }


    @Test
    public void canComplaint(){
        Session session = UserSession.getSession();

        Patient patient = (Patient)session.get(Patient.class,1);

        Complaint complaint = new Complaint();
        complaint.setDate(new Date(System.currentTimeMillis()));
        complaint.setAssociations("asdfadfasdf");

        patient.getComplaints().add(complaint);


        session.beginTransaction();
        session.update(patient);
        session.getTransaction().commit();


    }


    @Test
    public void canAddPrescription(){
        Session session = UserSession.getSession();

        Patient patient = (Patient)session.get(Patient.class,1);

        Prescription prescription = new Prescription();
        prescription.setDate(new Date(System.currentTimeMillis()));
        prescription.setQuantity(123);

        patient.getPrescriptions().add(prescription);


        session.beginTransaction();
        session.update(patient);
        session.getTransaction().commit();


    }







}
