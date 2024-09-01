package todo.project.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import todo.project.entity.TopicUserLink;

public class TopicUserLinkDao extends DaoConnection implements Dao<TopicUserLink> {
    
    @Override
    public TopicUserLink get(int id) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        TopicUserLink response = null;
        try {
            tx = session.beginTransaction();
            TopicUserLink topicUserLink = session.get(TopicUserLink.class, id);
            response = topicUserLink;
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
    public List<TopicUserLink> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        List<TopicUserLink> response = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM TopicUserLink";
            Query<TopicUserLink> query = session.createQuery(hql, TopicUserLink.class);
            List<TopicUserLink> result = query.list();
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
    public void save(TopicUserLink topicUserLink) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(topicUserLink);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(TopicUserLink topicUserLink) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            TopicUserLink tul = session.get(TopicUserLink.class, topicUserLink.getId());
            if (tul != null) {
                tul.setTopic_id(topicUserLink.getTopic_id());
                tul.setUser_id(topicUserLink.getUser_id());
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
    public void delete(TopicUserLink topicUserLink) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            TopicUserLink tul = session.get(TopicUserLink.class, topicUserLink.getId());
            if (tul != null) session.remove(tul);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
