package todo.project;

import java.util.List;

import todo.project.services.Service;

import todo.project.entity.*;
import todo.project.persistence.HibernateUtil;

public class Main {

    public static void main(String[] args) {
        // User user = Service.signUpUser("Me", "Myself");
        // Topic topic = Service.createTopic(user, "Main");
        // Task task = Service.createTask(topic, "Joke");
        User user = Service.signInUser("Me", "Myself");
        List<Topic> topics = Service.getTopics(user);
        Topic topic = topics.get(0);
        List<Task> tasks = Service.getTasks(topic);
        for (Task task : tasks) {
            Service.deleteTaskFromParent(task);
        }
        HibernateUtil.shutdown();
    }
}