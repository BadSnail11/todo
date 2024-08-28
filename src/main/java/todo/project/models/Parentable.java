package todo.project.models;

import java.util.List;

public interface Parentable {
    
    public void addSubTask(Task subTask);

    public List<Task> getSubTasks();
}
