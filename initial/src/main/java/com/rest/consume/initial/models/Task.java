package com.rest.consume.initial.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {
    private String name;
    private String command;
    @JsonIgnore
    private List<String> requires = new ArrayList<>();

    public Task(String name, String command) {
        setName(name);
        setCommand(command);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<String> getRequires() {
        return requires;
    }

    public void setRequires(List<String> requires) {
        this.requires = requires;
    }

    @Override
    public String toString() {
        return String.format("Task with name: %n %s, %n with command: %n %s ", this.getName(), this.getCommand());
    }
}
