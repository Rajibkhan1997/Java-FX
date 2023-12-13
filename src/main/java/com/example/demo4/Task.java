package com.example.demo4;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private int duration;
    private List<Task> successorTasks;

    public Task(String name, String description, LocalDate startDate, LocalDate endDate, int duration) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.successorTasks = new ArrayList<>();
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Task> getSuccessorTasks() {
        return successorTasks;
    }

    public void setSuccessorTasks(List<Task> successorTasks) {
        this.successorTasks = successorTasks;
    }

    public void addSuccessorTask(Task task) {
        successorTasks.add(task);
    }

    public void removeSuccessorTask(Task task) {
        successorTasks.remove(task);
    }
}