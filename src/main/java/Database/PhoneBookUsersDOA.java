package Database;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

    public List<PhoneBookUsers> getAllPhoneBookUserByNameOrPhone(String name){
        Transaction transaction = null;
        List<PhoneBookUsers> phoneBookUsers = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery("from PhoneBookUsers where Name like :name or Number like :number",PhoneBookUsers.class);
            query.setParameter("name",name+"%");
            query.setParameter("number",name+"%");
            phoneBookUsers = query.getResultList();
            transaction.commit();
        }catch (Exception ex){
            if (transaction!=null){
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        return phoneBookUsers;
    }
}