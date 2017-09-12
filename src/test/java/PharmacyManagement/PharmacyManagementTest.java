package PharmacyManagement;

import com.EntityClasses.PharmacyItem;
import db.UserSession;
import org.hibernate.Session;
import org.junit.Test;

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

}
