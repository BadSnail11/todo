package todo.project;

import org.hibernate.Session;
import org.hibernate.Transaction;

import todo.project.entity.User;
import todo.project.persistence.HibernateUtil;

public class Main {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            User user = new User();
            user.setLogin("Ivan");
            user.setPassword(Service.passwordCypher("123"));
            session.save(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        HibernateUtil.shutdown();
    }

}