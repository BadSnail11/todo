package todo.project.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;

import todo.project.entity.User;
import todo.project.persistence.HibernateUtil;

public class HibernateService {

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public static User signInUser(String login, String password) {
        // Session session = HibernateUtil.getSessionFactory().openSession();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        User response = null;
        try {
            tx = session.beginTransaction();
            // User user = new User();
            // user.setLogin("Ivan");
            // user.setPassword(Service.passwordCypher("123"));
            // session.save(user);
            String hql = "FROM User";
            Query<User> query = session.createQuery(hql, User.class);
            List<User> result = query.list();
            for (User user : result) {
                if (user.getLogin().equals(login) && user.getPassword().equals(Service.passwordCypher(password))){
                    response = user;
                }
            }
            // tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        // HibernateUtil.shutdown();
        return response;
    }

    public static User signUpUser(String login, String password) {
        // Session session = HibernateUtil.getSessionFactory().openSession();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        User response = null;
        try {
            tx = session.beginTransaction();
            User user = new User();
            user.setLogin(login);
            user.setPassword(Service.passwordCypher(password));
            response = user;
            session.persist(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        // HibernateUtil.shutdown();
        return response;
    }
}
