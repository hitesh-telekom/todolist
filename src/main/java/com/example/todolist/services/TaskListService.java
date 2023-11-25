package com.example.todolist.services;

import com.example.todolist.models.Reward;
import com.example.todolist.models.Task;
import com.example.todolist.models.TaskList;
import com.example.todolist.repositories.TaskListRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskListService {
    @Autowired
    TaskListRepository taskListRepository;

    public List<TaskList> getAllTaskList() {
        return taskListRepository.findAll();
    }

    public TaskList getTaskList(String id){
        return taskListRepository.findById(id).orElse(null);
    }

    public TaskList createTaskList(TaskList taskList){
        return taskListRepository.save(taskList);
    }

    public Optional<TaskList> getTaskListById(String id){
        return taskListRepository.findById(id);
    }

    public TaskList updateTaskList(TaskList taskList){
        Optional<TaskList> taskListOptional = getTaskListById(taskList.getId());
        if (taskListOptional.isEmpty()) {
            return null;
        }

        TaskList updatedTaskList = taskListOptional.get();
        updatedTaskList.setTitle(taskList.getTitle());
        return taskListRepository.save(updatedTaskList);
    }

    public void deleteTaskList(String id){
        taskListRepository.deleteById(id);
    }

    public TaskList createTask(String taskListId, Task task){
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

    public TaskList updateTask(String taskListId, Task updatedTask){
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

    public TaskList deleteTask(String taskListId, String taskId){
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

    public TaskList createReward(String taskListId, Reward reward){
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

    public TaskList updateReward(String taskListId, Reward updatedReward){
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

    public TaskList deleteReward(String taskListId, String rewardId){
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
