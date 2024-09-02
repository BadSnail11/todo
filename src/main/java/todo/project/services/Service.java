package todo.project.services;

import todo.project.entity.*;
import todo.project.dao.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.charset.StandardCharsets;

public class Service {

    private static TaskDao taskDao = new TaskDao();
    private static TopicDao topicDao = new TopicDao();
    private static UserDao userDao = new UserDao();

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private static String passwordCypher(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedHash);
    }

    public static User signUpUser(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(passwordCypher(password));
        try {
            userDao.save(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User signInUser(String login, String password) {
        List<User> users = userDao.getAll();
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(passwordCypher(password))) {
                return user;
            }
        }
        return null;
    }

    public static void updateUser(User user) {
        userDao.update(user);
    }

    public static void deleteUser(User user) {
        userDao.delete(user);
    }

    public static Topic createTopic(User user, String name) {
        Topic topic = new Topic();
        topic.setName(name);
        try {
            topicDao.save(topic);
            LinkService.connectTopicUser(topic, user);
            return topic;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Topic> getTopics(User user) {
        List<Topic> topics = topicDao.getAll();
        List<Topic> userTopics = new ArrayList<>(Arrays.asList());
        for (Topic topic : topics) {
            if (LinkService.isTopicInUser(topic, user)) {
                userTopics.add(topic);
            }
        }
        return (userTopics.isEmpty()) ? null : userTopics;
    }

    public static Task createTask(Parentable parent, String description) {
        Task task = new Task();
        task.setDescription(description);
        try {
            taskDao.save(task);
            if (parent.getClass().equals(Task.class)) {
                LinkService.connectSubtaskTask(task, parent);
            } else {
                LinkService.connectTaskTopic(task, parent);
            }
            return task;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Task> getTasks(Parentable parent) {
        List<Task> parentTasks = new ArrayList<>(Arrays.asList());
        List<Task> tasks = taskDao.getAll();
        if (parent.getClass().equals(Topic.class)) {
            for (Task task : tasks) {
                if (LinkService.isTaskInTopic(task, parent)) {
                    parentTasks.add(task);
                }
            }
        } else {
            for (Task task : tasks) {
                if (LinkService.isSubaskInTask(task, parent)){
                    parentTasks.add(task);
                }
            }
        }
        return (parentTasks.isEmpty()) ? null : parentTasks;
    }

    public static Parentable getTaskParent(Task task) {
        List<Topic> topics = topicDao.getAll();
        for (Topic topic : topics) {
            if (LinkService.isTaskInTopic(task, topic)) {
                return topic;
            }
        }
        List<Task> tasks = taskDao.getAll();
        for (Task taskIter : tasks) {
            if (LinkService.isSubaskInTask(task, taskIter)) {
                return taskIter;
            }
        }
        return null;
    }

    public static void deleteTaskFromParent(Task task) {
        Parentable parent = getTaskParent(task);
        if (parent.getClass().equals(Topic.class)) {
            LinkService.deleteTaskFromTopic(task, parent);
        } else {
            LinkService.deleteSubtaskFromTask(task, parent);
        }
    }
}
