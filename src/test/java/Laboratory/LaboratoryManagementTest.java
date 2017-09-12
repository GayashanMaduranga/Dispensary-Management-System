package Laboratory;

import com.EntityClasses.*;
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
public class LaboratoryManagementTest {

    @Test
    public void canAddMainTest(){
        //When
        Session session = getSession();


        MainTest mainTest = new MainTest();
        mainTest.setTestName("Full Blood Count");

        TestField testField1 = new TestField();
        testField1.setFieldName("Haemoglobin");
        testField1.setUnits("g/dl");

        TestField testField2 = new TestField();
        testField1.setFieldName("Red Blood Cell");
        testField1.setUnits("mill");

        //adding Ranges
        Range r1 = new Range();
        r1.setLowerBound(10);
        r1.setUpperBound(20);
        r1.setMaxPatientAge(10);
        r1.setMinPatientAge(2);
        testField1.getRangeList().add(r1);

        mainTest.getTestFields().add(testField1);
        mainTest.getTestFields().add(testField2);


        session.beginTransaction();
        session.save(mainTest);

        session.getTransaction().commit();

        session.close();

    }

    @Test
    public void canOrderTest(){

        Session session = getSession();

        LabTestOrder labTestOrder = new LabTestOrder();
        labTestOrder.setMainTest((MainTest)session.get(MainTest.class,1));
        labTestOrder.setDate(new Date(System.currentTimeMillis()));


        session.beginTransaction();
        session.save(labTestOrder);

        session.getTransaction().commit();

        session.close();

    }

    @Test
    public void canAddSample(){

        Session session = getSession();

        Sample sample = new Sample();
        sample.setType("somthing");
        sample.setDate(Date.valueOf("2013-12-21"));

        session.beginTransaction();
        session.save(sample);
        session.getTransaction().commit();
        session.close();


    }





    private Session getSession(){
        Configuration configuration = new Configuration().configure();

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        return session;

    }
}
