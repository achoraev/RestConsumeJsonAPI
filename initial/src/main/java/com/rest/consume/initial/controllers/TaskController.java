package com.rest.consume.initial.controllers;

import com.rest.consume.initial.models.Task;
import com.rest.consume.initial.models.Tasks;
import com.rest.consume.initial.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(value = "/tasks",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Task> postTask(@RequestBody Tasks tasks){
        // sort tasks with requirements
        List<Task> orderedTasks = this.taskService.sortingTasks(tasks.getTasks());

        // execute each command
        orderedTasks.forEach(x -> this.taskService.executeTask(x.getCommand()));

        orderedTasks.forEach(System.out::println);
        return orderedTasks;
    }

    @GetMapping("/tasks")
    public String getTask(){
        return "Service for consume JSON request and return sorted response.";
    }
}