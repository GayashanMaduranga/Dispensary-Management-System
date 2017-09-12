package SupplyManagement;

import com.EntityClasses.Supplier;
import db.UserSession;
import org.hibernate.Session;
import org.junit.Test;

/**
 * Created by gayashan on 9/12/2017.
 */
public class SupplyManagementTest {


    @Test
    public void canAddSupplier(){

        Session session = UserSession.getSession();

        Supplier supplier = new Supplier();
        supplier.setSupname("Some Name steaeae ");
        supplier.setEmail("Mail@mail.com");
        supplier.setContactNumber("2349034");


        session.beginTransaction();
        session.save(supplier);
        session.getTransaction().commit();

    }




}
