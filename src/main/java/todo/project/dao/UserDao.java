package todo.project.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import todo.project.entity.User;

public class UserDao extends DaoConnection implements Dao<User> {
    
    @Override
    public User get(int id) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        User response = null;
        try {
            tx = session.beginTransaction();
            response = session.get(User.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return response;
    }

    @Override
    public List<User> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        List<User> response = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM User";
            Query<User> query = session.createQuery(hql, User.class);
            List<User> result = query.list();
            response = result;
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return response;
    }

    @Override
    public void save(User user) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(user);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(User user) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            User usr = session.get(User.class, user.getId());
            if (usr != null) {
                usr.setLogin(user.getLogin());
                usr.setPassword(user.getPassword());
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(User user) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            User usr = session.get(User.class, user.getId());
            if (usr != null) session.remove(usr);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
