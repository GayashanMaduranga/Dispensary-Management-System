import com.EntityClasses.*;
import db.UserSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

/**
 * Created by gayashan on 8/27/2017.
 */
public class MyTest {

//    @Test
//    public void canAddDoctor(){
//        //given
//        Doctor doctor = new Doctor();
//        doctor.setEmployeeID(123);
//        doctor.setFullName("Test Some Name");
//        doctor.setDateOfBirth(Date.valueOf("2017-08-27"));
//        doctor.setContactNo("1234455666");
//        doctor.setEmail("Kaiz@Mail.com");
//        doctor.setChargePerVisit(300.0);
//        doctor.setGender('M');
//
//
//        //when
//        Configuration configuration = new Configuration().configure();
//
//        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//        SessionFactory sessionFactory =configuration.buildSessionFactory(serviceRegistry);
//        Session session=sessionFactory.openSession();
//
//        session.beginTransaction();
//        session.save(doctor);
//        session.getTransaction().commit();
//
//        //Then
//
//
//    }

    @org.junit.Test
    public void CanAddUser(){

        //given
        User user = new User();
        user.setUsername("user");
        user.setPassword("pass");
        user.setAccessLevel(6);


        Configuration configuration = new Configuration().configure();

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(user);

        session.getTransaction().commit();

        session.close();
    }

    @org.junit.Test
    public void CanAddMedication(){

        //given

        Medication med = new Medication();
        med.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
        med.setDosage(1);
        med.setDosageType("tablets");
        med.setFrequency("once daily");
        med.setMedication("ciprofloxacin");
        med.setDiscontinued(true);

        Configuration configuration = new Configuration().configure();

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Query patientNameQuery = session.createQuery("select p from Patient p where p.pname = 'patient0'");
        List<Patient> patients = patientNameQuery.list();
        Patient patient = patients.get(0);

        patient.getMedications().add(med);
        session.persist(patient);

        session.getTransaction().commit();

        session.close();
    }

    @Test
    public void canRetriveData(){

        Session session = UserSession.getSession();

        SupplyOrder supplier = (SupplyOrder)session.get(SupplyOrder.class,1);
        Supplier supplier1 = supplier.getSupplier();

        System.out.println(supplier1.getSupname());

    }

}
