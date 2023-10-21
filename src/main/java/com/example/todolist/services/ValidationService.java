package com.example.todolist.services;

import com.example.todolist.models.Reward;
import com.example.todolist.models.Task;
import com.example.todolist.models.TaskList;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

@Service
public class ValidationService {

    public void validateUpdateTaskList(TaskList taskList) throws Exception {
        if (ObjectUtils.isEmpty(taskList)) {
            throw new Exception();
        }
    }

    public void validateCreateTaskRequest(String taskListId, Task task) throws Exception {
        if (ObjectUtils.isEmpty(taskListId) || Objects.isNull(task) || ObjectUtils.isEmpty(task.getTitle())) {
            throw new Exception();
        }
    }

    public void validateUpdateTaskRequest(String taskListId, Task task) throws Exception {
        if (ObjectUtils.isEmpty(taskListId) || Objects.isNull(task) || ObjectUtils.isEmpty(task.getId())) {
            throw new Exception();
        }
    }

    public void validateDeleteTaskRequest(String taskListId, String taskId) throws Exception {
        if (ObjectUtils.isEmpty(taskListId) || ObjectUtils.isEmpty(taskId)) {
            throw new Exception();
        }
    }

    public void validateCreateRewardRequest(String taskListId, Reward reward) throws Exception {
        if (ObjectUtils.isEmpty(taskListId) || Objects.isNull(reward) || ObjectUtils.isEmpty(reward.getTitle()) || reward.getPoints() <= 0) {
            throw new Exception();
        }
    }

    public void validateUpdateRewardRequest(String taskListId, Reward reward) throws Exception {
        if (ObjectUtils.isEmpty(taskListId) || Objects.isNull(reward) || ObjectUtils.isEmpty(reward.getId())) {
            throw new Exception();
        }
    }

    public void validateDeleteRewardRequest(String taskListId, String rewardId) throws Exception {
        if (ObjectUtils.isEmpty(taskListId) || ObjectUtils.isEmpty(rewardId)) {
            throw new Exception();
        }
    }
}
