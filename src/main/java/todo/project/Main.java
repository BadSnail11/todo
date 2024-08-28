package todo.project;
import todo.project.models.Topic;
import todo.project.models.Task;

public class Main {
    public static void main(String[] args) {
        Topic h1 = new Topic("First");
        Task t2 = new Task(h1, "do task 2");
        Task t1 = new Task("do task 1");
        h1.addSubTask(t1);
        t2.setPriority(Task.Priorities.high);
        t2.switchDone();
        Service.sortTasks(h1);
        for (var task : h1.getSubTasks()) {
            System.out.println(task.toString());
        }
    }
}