package PharmacyManagement;

import com.EntityClasses.PharmacyBatch;
import com.EntityClasses.PharmacyItem;
import db.UserSession;
import org.hibernate.Session;
import org.junit.Test;

import java.sql.Date;

/**
 * Created by gayashan on 9/12/2017.
 */
public class PharmacyManagementTest {

    @Test
    public void canAddPharmacyItem(){
        Session session = UserSession.getSession();

        PharmacyItem item = new PharmacyItem();
        item.setItemName("Some Item");
        item.setMRP(123);
        item.setReorderLevel(12312313);
        item.setStock(23234);

        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();


    }

    @Test
    public void canAddPharmacyBatch(){
        Session session = UserSession.getSession();

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

        session.beginTransaction();
        session.save(pharmacyBatch);
        session.getTransaction().commit();


    }

}
