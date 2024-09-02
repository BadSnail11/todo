package todo.project.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "task")
public class Task implements Serializable, Parentable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    private Integer done = 0;

    private String priority = Priority.none.name();

    
    public static enum Priority {
        none,
        low,
        medium,
        high
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return (done.equals(0)) ? false : true;
    }

    public void setDone(boolean done) {
        this.done = (done) ? 1 : 0;
    }

    public void switchDone() {
        done = (done.equals(0)) ? 1 : 0;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority.name();
    }

}
