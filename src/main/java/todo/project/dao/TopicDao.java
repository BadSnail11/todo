package todo.project.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import todo.project.entity.Topic;

public class TopicDao extends DaoConnection implements Dao<Topic> {
    
    @Override
    public Topic get(int id) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        Topic response = null;
        try {
            tx = session.beginTransaction();
            Topic topic = session.get(Topic.class, id);
            response = topic;
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
    public List<Topic> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        List<Topic> response = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM Topic";
            Query<Topic> query = session.createQuery(hql, Topic.class);
            List<Topic> result = query.list();
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
    public void save(Topic topic) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(topic);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Topic topic) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Topic tpc = session.get(Topic.class, topic.getId());
            if (tpc != null) {
                tpc.setName(topic.getName());
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
    public void delete(Topic topic) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Topic tpc = session.get(Topic.class, topic.getId());
            if (tpc != null) session.remove(tpc);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
