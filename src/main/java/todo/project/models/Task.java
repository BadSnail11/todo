package todo.project.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Task implements Parentable, Comparable<Task>, Comparator<Task> {
    private String description;
    private boolean done;
    private List<Task> subTasks;
    private Parentable parent;
    private String priority;

    public static enum Priorities {
        none,
        low,
        medium,
        high,
    }

    public Task(String description){
        this.description = description;
        this.done = false;
        this.subTasks = null;
        this.parent = null;
        this.priority = Priorities.none.name();
    }

    public Task(Parentable parent, String description) {
        this(description);
        parent.addSubTask(this);
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(Priorities priority) {
        this.priority = priority.name();
    }

    public void switchDone() {
        this.done = !this.done;
    }

    public Parentable getParent() {
        return parent;
    }

    public void setParent(Parentable parent) {
        this.parent = parent;
    }

    @Override
    public void addSubTask(Task subTask) {
        if (this.subTasks == null) {
            this.subTasks = new ArrayList<>(Arrays.asList(subTask));
        } else {
            this.subTasks.add(subTask);
        }
        subTask.setParent(this);
    }

    @Override
    public List<Task> getSubTasks() {
        return this.subTasks;
    }

    @Override
    public int compareTo(Task task) {
        Priorities priority1 = Priorities.valueOf(this.priority);
        Priorities priority2 = Priorities.valueOf(task.priority);
        return priority1.compareTo(priority2);
    }

    @Override
    public int compare(Task task1, Task task2) {
        Priorities priority1 = Priorities.valueOf(task1.priority);
        Priorities priority2 = Priorities.valueOf(task2.priority);
        return priority1.compareTo(priority2);
    }

    @Override
    public String toString() {
        return getDescription() + " | " + isDone();
    }
}
