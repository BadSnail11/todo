package todo.project;

import todo.project.entity.User;
import todo.project.persistence.HibernateUtil;
import org.hibernate.Session;

public class Main {

    public static void main(String[] args) {
        System.out.println("Maven + Hibernate + MySQL");
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        User user = new User();

        user.setLogin("Alex");
        user.setPassword(Service.passwordCypher("abc"));

        session.save(user);
        session.getTransaction().commit();
    }

}