package com.rest.consume.initial.services;

import com.rest.consume.initial.models.Task;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TaskService {

    // sortingTask method receive List of tasks, sort them and return new List of tasks
    public List<Task> sortingTasks(List<Task> taskList){

        Map<String, String> mapOfTasks = new HashMap<>();

        for (Task task : taskList) {

            if (task.getRequires().size() == 0){
                mapOfTasks.put(task.getName(), task.getCommand());
            } else {
                List<String> requiredTasks = task.getRequires();

                // iterate over required tasks and add them before current
                for (String requiredTaskName : requiredTasks) {
                    if (!mapOfTasks.containsKey(requiredTaskName)) {
                        for (Task currentTask : taskList) {
                            if (currentTask.getName().equals(requiredTaskName)) {
                                mapOfTasks.put(currentTask.getName(), currentTask.getCommand());
                                break;
                            }
                        }
                    }
                }
            }
            mapOfTasks.put(task.getName(), task.getCommand());
        }

        // create list of sorted Tasks from HashMap
        List<Task> sortedTasks = mapOfTasks.entrySet().stream()
                .map(m->new Task(m.getKey(), m.getValue()))
                .collect(Collectors.toList());
        // reverse ordering of the Tasks
        List<Task> reversedList = new ArrayList<>();
        for (int i = sortedTasks.size() - 1; i >= 0; i--) {
            reversedList.add(sortedTasks.get(i));
        }

        return reversedList;
    }

    // those method execiting bash commands and print result on console
    public void executeTask(String command) {

        ProcessBuilder pb = new ProcessBuilder();
        pb.command("bash ", command);

        try {
            Process process = pb.start();

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Success!");
                System.out.println(output);
                System.exit(0);
            } else {
                System.out.println("Failed");
                System.out.println(output);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}