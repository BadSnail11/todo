package todo.project;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import todo.project.entity.Task;
import todo.project.entity.Topic;
import todo.project.entity.TopicUserLink;
import todo.project.entity.User;
import todo.project.persistence.HibernateUtil;

import todo.project.services.HibernateService;

import todo.project.dao.DaoConnection;
import todo.project.dao.TaskDao;
import todo.project.dao.UserDao;
import todo.project.dao.TopicDao;
import todo.project.dao.TopicUserLinkDao;

public class Main {

    public static void main(String[] args) {
        // User user = HibernateService.signInUser("Ivan", "123");
        // System.out.println(user.getLogin());
        // User new_user = HibernateService.signUpUser("who", "who");
        // System.out.println(new_user.getLogin());
        // HibernateUtil.shutdown();
        // UserDao userDao = new UserDao();
        // // User user = userDao.get(1);
        // // user.setLogin("Alex");
        // // userDao.update(user);
        // User user = userDao.get(4);
        // userDao.delete(user);
        // TaskDao taskDao = new TaskDao();
        // Task task = taskDao.get(2);
        // taskDao.delete(task);
        // TopicDao topicDao = new TopicDao();
        // Topic topic = new Topic();
        // topic.setName("main");
        // topicDao.save(topic);
        TopicDao topicDao = new TopicDao();
        UserDao userDao = new UserDao();
        TopicUserLinkDao topicUserLinkDao = new TopicUserLinkDao();
        Topic topic = topicDao.get(1);
        User user = userDao.get(1);
        TopicUserLink topicUserLink = new TopicUserLink();
        topicUserLink.setTopic_id(topic.getId());
        topicUserLink.setUser_id(user.getId());
        topicUserLinkDao.save(topicUserLink);
        HibernateUtil.shutdown();
    }
}