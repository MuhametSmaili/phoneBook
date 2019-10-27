package Database;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Locale;

public class UserDAO {

    public void createUser(User user) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            // start a transaction
            transaction = session.beginTransaction();
            // save the person object
            session.save(user);
            // commit transaction
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public User getUserByUsername(String name){
        Transaction transaction = null;
        User user = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("from User where Username=:name",User.class);
            query.setParameter("name", name);
            user = (User) query.uniqueResult();

            transaction.commit();
        }catch (Exception ex){
            if (transaction !=null){
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        return user;
    }
}
