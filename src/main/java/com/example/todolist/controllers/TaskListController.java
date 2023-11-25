package com.example.todolist.controllers;

import com.example.todolist.models.Reward;
import com.example.todolist.models.Task;
import com.example.todolist.models.TaskList;
import com.example.todolist.services.TaskListService;
import com.example.todolist.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/taskList")
public class TaskListController {

    @GetMapping("/home")
    public String home() {
        return "Hello World!";
    }

    @Autowired
    TaskListService taskListService;
    @Autowired
    ValidationService validationService;

    @GetMapping(value = "")
    public List<TaskList> getAllTaskList() {
        return taskListService.getAllTaskList();
    }

    @GetMapping(value = "/{id}")
    public TaskList getTaskList(@PathVariable String id) {
        return taskListService.getTaskList(id);
    }

    @PostMapping(value = "")
    public TaskList createTaskList(@RequestBody TaskList taskList) {
        return taskListService.createTaskList(taskList);
    }

    @PatchMapping(value = "")
    public TaskList updateTaskList(@RequestBody TaskList taskList) throws Exception {
        validationService.validateUpdateTaskList(taskList);
        return taskListService.updateTaskList(taskList);
    }

    @DeleteMapping(value = "")
    public void deleteTaskList(@RequestParam String id) {
        taskListService.deleteTaskList(id);
    }

    /* Task Related APIs */
    @PostMapping(value = "/{id}/tasks")
    public TaskList createTask(@PathVariable("id") String taskListId, @RequestBody Task task) throws Exception {
        validationService.validateCreateTaskRequest(taskListId, task);
        return taskListService.createTask(taskListId, task);
    }

    @PatchMapping(value = "/{id}/tasks")
    public TaskList updateTask(@PathVariable("id") String taskListId, @RequestBody Task updatedTask) throws Exception {
        validationService.validateUpdateTaskRequest(taskListId, updatedTask);
        return taskListService.updateTask(taskListId, updatedTask);
    }

    @DeleteMapping(value = "/{id}/tasks")
    public TaskList deleteTask(@PathVariable("id") String taskListId, @RequestParam("id") String taskId) throws Exception {
        validationService.validateDeleteTaskRequest(taskListId, taskId);
        return taskListService.deleteTask(taskListId, taskId);
    }

    /* Rewards related API */
    @PostMapping(value = "/{id}/rewards")
    public TaskList createReward(@PathVariable("id") String taskListId, @RequestBody Reward reward) throws Exception {
        validationService.validateCreateRewardRequest(taskListId, reward);
        return taskListService.createReward(taskListId, reward);
    }

    @PatchMapping(value = "/{id}/rewards")
    public TaskList updateReward(@PathVariable("id") String taskListId, @RequestBody Reward updatedReward) throws Exception {
        validationService.validateUpdateRewardRequest(taskListId, updatedReward);
        return taskListService.updateReward(taskListId, updatedReward);
    }

    @DeleteMapping(value = "/{id}/rewards")
    public TaskList deleteReward(@PathVariable("id") String taskListId, @RequestParam("id") String rewardId) throws Exception {
        validationService.validateDeleteRewardRequest(taskListId, rewardId);
        return taskListService.deleteReward(taskListId, rewardId);
    }

}
