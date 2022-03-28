package com.rest.consume.initial.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

// class Tasks is created to may map Json request with list of Tasks
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tasks {
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
