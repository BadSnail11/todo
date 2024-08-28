package todo.project.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Topic implements Parentable {
    private String name;
    private List<Task> subTasks;

    public Topic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public String toString() {
        return getName();
    }
}
