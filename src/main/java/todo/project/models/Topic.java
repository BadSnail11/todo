package todo.project.models;

public class Topic implements Parentable {
    private String name;
    private Task subTask;

    public Topic(String name) {
        this.name = name;
        this.subTask = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void addSubTask(Task subTask) {
        if (this.subTask == null) {
            this.subTask = subTask;
        } else {
            this.subTask.addTask(subTask);
        }
    }
}
