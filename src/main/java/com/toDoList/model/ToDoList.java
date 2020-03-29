package com.toDoList.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {

    @JsonProperty("name")
    private String name;

    @JsonProperty("tasks")
    private List<Task> tasks;

    public ToDoList() { }

    public ToDoList(String name) {
        this.name = name;
        tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public String getName() {
        return name;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

}
