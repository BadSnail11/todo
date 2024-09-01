package todo.project.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "subtask_task_link")
public class SubtaskTaskLink implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer subtask_id;

    private Integer task_id;

    public Integer getId() {
        return id;
    }

    public Integer getSubtask_id() {
        return subtask_id;
    }

    public void setSubtask_id(Integer subtask_id) {
        this.subtask_id = subtask_id;
    }

    public Integer getTask_id() {
        return task_id;
    }

    public void setTask_id(Integer task_id) {
        this.task_id = task_id;
    }

}
