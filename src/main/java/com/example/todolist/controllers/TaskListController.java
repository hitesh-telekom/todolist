package com.example.todolist.controllers;

import com.example.todolist.models.Reward;
import com.example.todolist.models.Task;
import com.example.todolist.models.TaskList;
import com.example.todolist.repositories.TaskListRepository;
import com.example.todolist.services.ValidationService;
import org.apache.commons.collections4.CollectionUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/taskList")
public class TaskListController {

    @GetMapping("/home")
    public String home() {
        return "Hello World!";
    }

    @Autowired
    TaskListRepository taskListRepository;
    @Autowired
    ValidationService validationService;

    @GetMapping(value = "")
    public List<TaskList> getAllTaskList() {
        return taskListRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public TaskList getTaskList(@PathVariable String id) {
        Optional<TaskList> taskListOptional = taskListRepository.findById(id);
        return taskListOptional.orElse(null);
    }

    @PostMapping(value = "")
    public TaskList createTaskList(@RequestBody TaskList taskList) {
        return taskListRepository.save(taskList);
    }

    @PatchMapping(value = "")
    public TaskList updateTaskList(@RequestBody TaskList taskList) throws Exception {
        validationService.validateUpdateTaskList(taskList);
        Optional<TaskList> taskListOptional = taskListRepository.findById(taskList.getId());
        if (taskListOptional.isEmpty()) {
            return null;
        }

        TaskList updatedTaskList = taskListOptional.get();
        updatedTaskList.setTitle(taskList.getTitle());
        return taskListRepository.save(updatedTaskList);
    }

    @DeleteMapping(value = "")
    public void deleteTaskList(@RequestParam String id) {
        taskListRepository.deleteById(id);
    }

    /* Task Related APIs */
    @PostMapping(value = "/{id}/tasks")
    public TaskList createTask(@PathVariable("id") String taskListId, @RequestBody Task task) throws Exception {
        validationService.validateCreateTaskRequest(taskListId, task);

        Optional<TaskList> taskListOptional = taskListRepository.findById(taskListId);
        if (taskListOptional.isEmpty()) {
            return null;
        }

        TaskList taskList = taskListOptional.get();
        if (taskList.getTaskList() == null) {
            taskList.setTaskList(new ArrayList<>());
        }

        task.setId(new ObjectId().toString());
        taskList.getTaskList().add(task);
        return taskListRepository.save(taskList);
    }

    @PatchMapping(value = "/{id}/tasks")
    public TaskList updateTask(@PathVariable("id") String taskListId, @RequestBody Task updatedTask) throws Exception {
        validationService.validateUpdateTaskRequest(taskListId, updatedTask);

        Optional<TaskList> taskListOptional = taskListRepository.findById(taskListId);
        if (taskListOptional.isEmpty()) {
            return null;
        }

        TaskList taskList = taskListOptional.get();

        List<Task> updatedTasks = CollectionUtils.emptyIfNull(taskList.getTaskList())
                .stream()
                .map(task -> Objects.equals(task.getId(), updatedTask.getId()) ? updatedTask : task)
                .collect(Collectors.toList());

        taskList.setTaskList(updatedTasks);
        return taskListRepository.save(taskList);
    }

    @DeleteMapping(value = "/{id}/tasks")
    public TaskList deleteTask(@PathVariable("id") String taskListId, @RequestParam("id") String taskId) throws Exception {
        validationService.validateDeleteTaskRequest(taskListId, taskId);

        Optional<TaskList> taskListOptional = taskListRepository.findById(taskListId);
        if (taskListOptional.isEmpty()) {
            return null;
        }

        TaskList taskList = taskListOptional.get();

        List<Task> updatedTasks = CollectionUtils.emptyIfNull(taskList.getTaskList())
                .stream()
                .filter(task1 -> !task1.getId().equals(taskId))
                .collect(Collectors.toList());

        taskList.setTaskList(updatedTasks);
        return taskListRepository.save(taskList);
    }

    /* Rewards related API */
    @PostMapping(value = "/{id}/rewards")
    public TaskList createReward(@PathVariable("id") String taskListId, @RequestBody Reward reward) throws Exception {
        validationService.validateCreateRewardRequest(taskListId, reward);

        Optional<TaskList> taskListOptional = taskListRepository.findById(taskListId);
        if (taskListOptional.isEmpty()) {
            return null;
        }

        TaskList taskList = taskListOptional.get();
        if (taskList.getRewardList() == null) {
            taskList.setRewardList(new ArrayList<>());
        }

        reward.setId(new ObjectId().toString());
        taskList.getRewardList().add(reward);
        return taskListRepository.save(taskList);
    }

    @PatchMapping(value = "/{id}/rewards")
    public TaskList updateReward(@PathVariable("id") String taskListId, @RequestBody Reward updatedReward) throws Exception {
        validationService.validateUpdateRewardRequest(taskListId, updatedReward);

        Optional<TaskList> taskListOptional = taskListRepository.findById(taskListId);
        if (taskListOptional.isEmpty()) {
            return null;
        }

        TaskList taskList = taskListOptional.get();
        List<Reward> updatedRewards = CollectionUtils.emptyIfNull(taskList.getRewardList())
                .stream()
                .map(reward -> Objects.equals(reward.getId(), updatedReward.getId()) ? updatedReward : reward)
                .collect(Collectors.toList());
        taskList.setRewardList(updatedRewards);
        return taskListRepository.save(taskList);
    }

    @DeleteMapping(value = "/{id}/rewards")
    public TaskList deleteReward(@PathVariable("id") String taskListId, @RequestParam("id") String rewardId) throws Exception {
        validationService.validateDeleteRewardRequest(taskListId, rewardId);
        Optional<TaskList> taskListOptional = taskListRepository.findById(taskListId);
        if (taskListOptional.isEmpty()) {
            return null;
        }
        TaskList taskList = taskListOptional.get();
        List<Reward> updatedRewards = CollectionUtils.emptyIfNull(taskList.getRewardList())
                .stream()
                .filter(reward -> !reward.getId().equals(rewardId))
                .collect(Collectors.toList());
        taskList.setRewardList(updatedRewards);
        return taskListRepository.save(taskList);
    }

}
