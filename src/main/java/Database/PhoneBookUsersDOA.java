package Database;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class PhoneBookUsersDOA {

    public void addUser(PhoneBookUsers users) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            // start a transaction
            transaction = session.beginTransaction();
            // save the person object
            session.save(users);
            // commit transaction
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void updateUser(PhoneBookUsers users) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            // start a transaction
            transaction = session.beginTransaction();
            // save the person object
            session.update(users);
            // commit transaction
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void deleteUser(PhoneBookUsers users) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            // start a transaction
            transaction = session.beginTransaction();
            // save the person object
            session.delete(users);
            // commit transaction
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

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
