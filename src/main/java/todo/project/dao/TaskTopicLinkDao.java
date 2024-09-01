package todo.project.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import todo.project.entity.TaskTopicLink;

public class TaskTopicLinkDao extends DaoConnection implements Dao<TaskTopicLink> {
    
    @Override
    public TaskTopicLink get(int id) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        TaskTopicLink response = null;
        try {
            tx = session.beginTransaction();
            TaskTopicLink taskTopicLink = session.get(TaskTopicLink.class, id);
            response = taskTopicLink;
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
    public List<TaskTopicLink> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        List<TaskTopicLink> response = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM TaskTopicLink";
            Query<TaskTopicLink> query = session.createQuery(hql, TaskTopicLink.class);
            List<TaskTopicLink> result = query.list();
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
    public void save(TaskTopicLink taskTopicLink) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(taskTopicLink);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(TaskTopicLink taskTopicLink) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            TaskTopicLink ttl = session.get(TaskTopicLink.class, taskTopicLink.getId());
            if (ttl != null) {
                ttl.setTask_id(taskTopicLink.getTask_id());
                ttl.setTopic_id(taskTopicLink.getTopic_id());
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
    public void delete(TaskTopicLink taskTopicLink) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            TaskTopicLink ttl = session.get(TaskTopicLink.class, taskTopicLink.getId());
            if (ttl != null) session.remove(ttl);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
