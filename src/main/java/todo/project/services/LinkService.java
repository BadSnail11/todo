package todo.project.services;

import todo.project.entity.*;

import java.util.List;

import todo.project.dao.*;

public class LinkService {

    private static TopicUserLinkDao topicUserLinkDao = new TopicUserLinkDao();
    private static TaskTopicLinkDao taskTopicLinkDao = new TaskTopicLinkDao();
    private static SubtaskTaskLinkDao subtaskTaskLinkDao = new SubtaskTaskLinkDao();

    protected static void connectTopicUser(Topic topic, User user) {
        TopicUserLink topicUserLink = new TopicUserLink();
        topicUserLink.setTopic_id(topic.getId());
        topicUserLink.setUser_id(user.getId());
        topicUserLinkDao.save(topicUserLink);
    }

    protected static void connectTaskTopic(Task task, Parentable topic) {
        TaskTopicLink taskTopicLink = new TaskTopicLink();
        taskTopicLink.setTask_id(task.getId());
        taskTopicLink.setTopic_id(topic.getId());
        taskTopicLinkDao.save(taskTopicLink);
    }

    protected static void connectSubtaskTask(Task subTask, Parentable task) {
        SubtaskTaskLink subtaskTaskLink = new SubtaskTaskLink();
        subtaskTaskLink.setSubtask_id(subTask.getId());
        subtaskTaskLink.setTask_id(task.getId());
        subtaskTaskLinkDao.save(subtaskTaskLink);
    }
    
    protected static boolean isTopicInUser(Topic topic, User user) {
        List<TopicUserLink> topicUserLinkList = topicUserLinkDao.getAll();
        for (TopicUserLink link : topicUserLinkList) {
            if (link.getTopic_id().equals(topic.getId()) && link.getUser_id().equals(user.getId())) {
                return true;
            }
        }
        return false;
    }

    protected static boolean isTaskInTopic(Task task, Parentable topic) {
        List<TaskTopicLink> taskTopicLinkList = taskTopicLinkDao.getAll();
        for (TaskTopicLink link : taskTopicLinkList) {
            if (link.getTask_id().equals(task.getId()) && link.getTopic_id().equals(topic.getId())) {
                return true;
            }
        }
        return false;
    }

    protected static boolean isSubaskInTask(Task subtask, Parentable task) {
        List<SubtaskTaskLink> subtaskTaskLinkList = subtaskTaskLinkDao.getAll();
        for (SubtaskTaskLink link : subtaskTaskLinkList) {
            if (link.getSubtask_id().equals(subtask.getId()) && link.getTask_id().equals(task.getId())) {
                return true;
            }
        }
        return false;
    }
    
    protected static void deleteTaskFromTopic(Task task, Parentable topic) {
        List<TaskTopicLink> taskTopicLinkList = taskTopicLinkDao.getAll();
        for (TaskTopicLink link : taskTopicLinkList) {
            if (link.getTask_id().equals(task.getId()) && link.getTopic_id().equals(topic.getId())) {
                taskTopicLinkDao.delete(link);
                return;
            }
        }
    }
    
    protected static void deleteSubtaskFromTask(Task subTask, Parentable task) {
        List <SubtaskTaskLink> subtaskTaskLinkList = subtaskTaskLinkDao.getAll();
        for (SubtaskTaskLink link : subtaskTaskLinkList) {
            if (link.getSubtask_id().equals(subTask.getId()) && link.getTask_id().equals(task.getId())) {
                subtaskTaskLinkDao.delete(link);
                return;
            }
        }
    }
}
