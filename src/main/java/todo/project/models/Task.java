package todo.project.models;

public class Task implements Parentable {
    private String description;
    private boolean isDone;
    private Task nextTask;
    private Task subTask;
    private Task parentTask;
    private String priority;

    static enum Priorities {
        none,
        low,
        medium,
        high,
    }

    public Task(String description){
        this.description = description;
        this.isDone = false;
        this.nextTask = null;
        this.subTask = null;
        this.parentTask = null;
        this.priority = Priorities.none.name();
    }

    public void setParent(Task parentTask) {
        this.parentTask = parentTask;
    }

    public void addSubTask(Task subTask) {

    }
}
