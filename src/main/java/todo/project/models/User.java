package todo.project.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {
    private String login;
    private String password;
    private List<Topic> topics;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void addTopic(Topic topic) {
        if (this.topics == null) {
            this.topics = new ArrayList<>(Arrays.asList(topic));
        } else {
            this.topics.add(topic);
        }
    }
}
