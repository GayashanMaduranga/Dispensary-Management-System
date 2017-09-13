package PharmacyManagement;

import com.EntityClasses.PharmacyBatch;
import com.EntityClasses.PharmacyBill;
import com.EntityClasses.PharmacyItem;
import com.EntityClasses.PharmacyLineItem;
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


    @Test
    public void canAddPharmacyLineItem(){
        Session session = UserSession.getSession();


        PharmacyBatch pharmacyBatch = (PharmacyBatch)session.get(PharmacyBatch.class,1);

        PharmacyLineItem item = new PharmacyLineItem() ;
        item.setPharmacyBatch(pharmacyBatch);
        item.setQuantity(12);
        item.setSubTotal(234.023);

        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();


    }


    @Test
    public void canAddPharmacyBill(){
        Session session = UserSession.getSession();


        PharmacyBatch pharmacyBatch = (PharmacyBatch)session.get(PharmacyBatch.class,1);


        PharmacyLineItem item = new PharmacyLineItem() ;
        item.setPharmacyBatch(pharmacyBatch);
        item.setQuantity(124);
        item.setSubTotal(234.023);

        PharmacyBill bill = new PharmacyBill();
        bill.setDate(new Date(System.currentTimeMillis()));
        bill.setTotal(123);
        bill.getPharmacyLineItems().add(item);


        session.beginTransaction();
        session.save(bill);
        session.getTransaction().commit();


    }

    @Test
    public  void dilShankaBiil(){

        Session session = UserSession.getSession();

        PharmacyBill bill = new PharmacyBill();
        bill.setDate(new Date(System.currentTimeMillis()));

        PharmacyLineItem  item1 = new PharmacyLineItem();
        PharmacyBatch pharmacyBatch1 = (PharmacyBatch)session.get(PharmacyBatch.class,1);
            item1.setPharmacyBatch(pharmacyBatch1);

            item1.setQuantity(5);
            item1.setSubTotal(pharmacyBatch1.getPurchasingPrice()*5);

        System.out.println("------------------------------------------------------------------$$$$$$");
        System.out.println(pharmacyBatch1.getPharmacyItem().getItemName());


        PharmacyLineItem  item2 = new PharmacyLineItem();
        PharmacyBatch pharmacyBatch2 = (PharmacyBatch)session.get(PharmacyBatch.class,2);
        item2.setPharmacyBatch(pharmacyBatch2);

        item2.setQuantity(20);
        item2.setSubTotal(pharmacyBatch2.getPurchasingPrice()*20);

        System.out.println("------------------------------------------------------------------$$$$$$");

        System.out.println(pharmacyBatch2.getPharmacyItem().getItemName());

        PharmacyLineItem  item3 = new PharmacyLineItem();
        PharmacyBatch pharmacyBatch3 = (PharmacyBatch)session.get(PharmacyBatch.class,3);
        item3.setPharmacyBatch(pharmacyBatch3);

        System.out.println("------------------------------------------------------------------$$$$$$");

        System.out.println(pharmacyBatch3.getPharmacyItem().getItemName());

        item3.setQuantity(40);
        item3.setSubTotal(pharmacyBatch1.getPurchasingPrice()*40);


        bill.getPharmacyLineItems().add(item1);
        bill.getPharmacyLineItems().add(item2);
        bill.getPharmacyLineItems().add(item3);

        double total = 0;

        for (PharmacyLineItem item : bill.getPharmacyLineItems()
             ) {
            total += item.getSubTotal();
        }



        bill.setTotal(total);


        session.beginTransaction();
        session.save(bill);
        session.getTransaction().commit();

    }
}
