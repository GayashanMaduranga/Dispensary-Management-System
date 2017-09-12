package patientManagement;

import com.EntityClasses.Patient;
import db.UserSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

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



}
