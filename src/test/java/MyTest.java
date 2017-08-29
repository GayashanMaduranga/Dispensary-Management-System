import com.EntityClasses.Doctor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;

/**
 * Created by gayashan on 8/27/2017.
 */
public class MyTest {

    @Test
    public void canAddDoctor(){
        //given
        Doctor doctor = new Doctor();
        doctor.setEmployeeID(123);
        doctor.setFullName("Test Some Name");
        doctor.setDateOfBirth(Date.valueOf("2017-08-27"));
        doctor.setContactNo("1234455666");
        doctor.setEmail("Kaiz@Mail.com");
        doctor.setChargePerVisit(300.0);
        doctor.setGender('M');


        //when
        Configuration configuration = new Configuration().configure();

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory =configuration.buildSessionFactory(serviceRegistry);
        Session session=sessionFactory.openSession();

        session.beginTransaction();
        session.save(doctor);
        session.getTransaction().commit();

        //Then


    }
}
