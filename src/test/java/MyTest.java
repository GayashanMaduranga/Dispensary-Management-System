import com.EntityClasses.Measure;
import com.EntityClasses.Medication;
import com.EntityClasses.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

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
        user.setUsername("admin");
        user.setPassword("pass");
        user.setAccessLevel(1);


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
        session.save(med);

        session.getTransaction().commit();

        session.close();
    }

    @org.junit.Test
    public void CanAddMeasure(){

        //given
        Measure mes = new Measure();

        mes.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
        mes.setBloodGlucose(999);
        mes.setBp(999);
        mes.setHeight(999);
        mes.setPulseRate(999);
        mes.setRespRate(999);
        mes.setTemp(999);
        mes.setWeight(999);


        Configuration configuration = new Configuration().configure();

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(mes);

        session.getTransaction().commit();

        session.close();
    }
}
