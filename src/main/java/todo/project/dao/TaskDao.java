package todo.project.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import todo.project.entity.Task;

public class TaskDao extends DaoConnection implements Dao<Task> {
    
    @Override
    public Task get(int id) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        Task response = null;
        try {
            tx = session.beginTransaction();
            Task task = session.get(Task.class, id);
            response = task;
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
    public List<Task> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        List<Task> response = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM Task";
            Query<Task> query = session.createQuery(hql, Task.class);
            List<Task> result = query.list();
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
    public void save(Task task) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(task);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Task task) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Task tsk = session.get(Task.class, task.getId());
            if (tsk != null) {
                tsk.setDescription(task.getDescription());
                tsk.setDone(task.isDone());
                tsk.setPriority(Task.Priority.valueOf(task.getPriority()));
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
    public void delete(Task task) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Task tsk = session.get(Task.class, task.getId());
            if (tsk != null) session.remove(tsk);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
