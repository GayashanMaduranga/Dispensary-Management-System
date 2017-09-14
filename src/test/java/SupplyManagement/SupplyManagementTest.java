package SupplyManagement;

import com.EntityClasses.PharmacyBatch;
import com.EntityClasses.PharmacyItem;
import com.EntityClasses.Supplier;
import com.EntityClasses.SupplyOrder;
import db.UserSession;
import org.hibernate.Session;
import org.junit.Test;

import java.sql.Date;

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

    @Test
    public void canAddSupplyOrder(){

        Session session = UserSession.getSession();

        Supplier supplier = (Supplier)session.get(Supplier.class,1);

        SupplyOrder supplyOrder = new SupplyOrder();
        supplyOrder.setSupplier(supplier);
        supplyOrder.setTotal(234);
        supplyOrder.setDate(new Date(System.currentTimeMillis()));



        PharmacyItem item = new PharmacyItem();
        item.setItemName("Some New  Item");
        item.setMRP(1232342);
        item.setReorderLevel(1789313);
        item.setStock(234);

        PharmacyBatch pharmacyBatch = new PharmacyBatch();
        pharmacyBatch.setExpiryDate(Date.valueOf("2030-08-02"));
        pharmacyBatch.setManufacturingDate(Date.valueOf("2010-08-02"));
        pharmacyBatch.setPurchasingPrice(20.23);
        pharmacyBatch.setPharmacyItem(item);

        supplyOrder.getPharmacyBatches().add(pharmacyBatch);


        session.beginTransaction();
        session.save(supplyOrder);
        session.getTransaction().commit();

    }


}
