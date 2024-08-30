package todo.project;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import todo.project.entity.User;
import todo.project.persistence.HibernateUtil;

import todo.project.services.HibernateService;

public class Main {

    public static void main(String[] args) {
        User user = HibernateService.signInUser("Ivan", "123");
        System.out.println(user.getLogin());
        User new_user = HibernateService.signUpUser("who", "who");
        System.out.println(new_user.getLogin());
        HibernateUtil.shutdown();
    }
}