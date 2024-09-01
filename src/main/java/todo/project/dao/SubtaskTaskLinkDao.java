package todo.project.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import todo.project.entity.SubtaskTaskLink;

public class SubtaskTaskLinkDao extends DaoConnection implements Dao<SubtaskTaskLink> {
    
    @Override
    public SubtaskTaskLink get(int id) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        SubtaskTaskLink response = null;
        try {
            tx = session.beginTransaction();
            SubtaskTaskLink subtaskTaskLink = session.get(SubtaskTaskLink.class, id);
            response = subtaskTaskLink;
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
    public List<SubtaskTaskLink> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        List<SubtaskTaskLink> response = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM SubtaskTaskLink";
            Query<SubtaskTaskLink> query = session.createQuery(hql, SubtaskTaskLink.class);
            List<SubtaskTaskLink> result = query.list();
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
    public void save(SubtaskTaskLink subtaskTaskLink) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(subtaskTaskLink);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(SubtaskTaskLink subtaskTaskLink) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SubtaskTaskLink stl = session.get(SubtaskTaskLink.class, subtaskTaskLink.getId());
            if (stl != null) {
                stl.setSubtask_id(subtaskTaskLink.getSubtask_id());
                stl.setTask_id(subtaskTaskLink.getTask_id());
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
    public void delete(SubtaskTaskLink subtaskTaskLink) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SubtaskTaskLink stl = session.get(SubtaskTaskLink.class, subtaskTaskLink.getId());
            if (stl != null) session.remove(stl);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
