package todo.project.dao;

import org.hibernate.SessionFactory;

import todo.project.persistence.HibernateUtil;

public class DaoConnection {
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    protected static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
