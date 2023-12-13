package com.example.demo4;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String name;
    private String description;
    private List<Task> tasks;

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
        this.tasks = new ArrayList<>();
    }

    // Getter and setter methods for existing properties

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        // Remove task from successor lists of other tasks
        for (Task t : tasks) {
            t.removeSuccessorTask(task);
        }
    }

    public void setTaskSuccessors(Task task, List<Task> successors) {
        task.setSuccessorTasks(successors);
    }

    public List<Task> getTaskSuccessors(Task task) {
        return task.getSuccessorTasks();
    }
}
