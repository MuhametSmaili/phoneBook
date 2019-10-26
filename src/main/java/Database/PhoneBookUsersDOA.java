package Database;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

    public List<PhoneBookUsers> getAllPhoneBookUsers(){
        Transaction transaction  = null;
        List<PhoneBookUsers> list = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction =session.beginTransaction();

//            Query query = session.createQuery("select new PhoneBookUsers(p.Name,p.Surname,p.Number) from PhoneBookUsers p", PhoneBookUsers.class);
//            List<Object[]> test = query.list();
//            list = session.createQuery("select new PhoneBookUsers(p.Name,p.Surname,p.Number) from Database.PhoneBookUsers p", PhoneBookUsers.class).getResultList();
            list = session.createQuery("from PhoneBookUsers",PhoneBookUsers.class).getResultList();

//            list =session.createQuery("SELECT p.Name,p.Surame,p.Number from PhoneBookUsers p",PhoneBookUsers.class).getResultList();
            System.out.println(list);
            System.out.println(list.get(1).getName());
//            System.out.println(test.get());
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
