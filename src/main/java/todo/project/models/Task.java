package todo.project.models;

public class Task implements Parentable, Comparable<Task> {
    private String description;
    private boolean done;
    private Task nextTask;
    private Task subTask;
    private Parentable parent;
    private String priority;

    static enum Priorities {
        none,
        low,
        medium,
        high,
    }

    public Task(String description){
        this.description = description;
        this.done = false;
        this.nextTask = null;
        this.subTask = null;
        this.parent = null;
        this.priority = Priorities.none.name();
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

    public Parentable getParent() {
        return parent;
    }

    public void setParent(Parentable parent) {
        this.parent = parent;
    }

    /**Returns last task in task list*/
    private Task getLastTask(Task task) {
        while (task.nextTask != null) {
            task = task.nextTask;
        }
        return task;
    }

    private void setNextTask(Task nextTask) {
        this.nextTask = nextTask;
    }

    public void addTask(Task task) {
        getLastTask(this).setNextTask(task);
    }

    @Override
    public void addSubTask(Task subTask) {
        if (this.subTask == null) {
            this.subTask = subTask;
        } else {
            this.subTask.addTask(subTask);
        }
    }

    @Override
    public int compareTo(Task task) {
        Priorities priority1 = Priorities.valueOf(this.priority);
        Priorities priority2 = Priorities.valueOf(task.priority);
        return priority1.compareTo(priority2);
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
