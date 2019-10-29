package Database;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class PhoneBookUsersDOA extends DatabaseHelper{

    public List<PhoneBookUsers> getAllPhoneBookUsers(){
        Transaction transaction  = null;
        List<PhoneBookUsers> list = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction =session.beginTransaction();
            list = session.createQuery("from PhoneBookUsers",PhoneBookUsers.class).getResultList();
            transaction.commit();
        }catch (Exception ex){
            if (transaction!=null){
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        return list;
    }
}