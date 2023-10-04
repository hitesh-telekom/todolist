package com.example.todolist.services;

import com.example.todolist.models.Task;
import com.example.todolist.models.TaskList;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.Objects;

@Service
public class ValidationService {

   public void validateUpdateTaskList(TaskList taskList) throws Exception {
      if(ObjectUtils.isEmpty(taskList)){
         throw new Exception();
      }
   }

   public void validateCreateTaskRequest(String taskListId, Task task) throws Exception {
      if(ObjectUtils.isEmpty(taskListId) || Objects.isNull(task) || ObjectUtils.isEmpty(task.getTitle())){
         throw new Exception();
      }
   }

   public void validateUpdateTaskRequest(String taskListId, Task task) throws Exception {
      if(ObjectUtils.isEmpty(taskListId) || Objects.isNull(task) || ObjectUtils.isEmpty(task.getId())){
         throw new Exception();
      }
   }

   public void validateDeleteTaskRequest(String taskListId, String taskId) throws Exception {
      if(ObjectUtils.isEmpty(taskListId) || ObjectUtils.isEmpty(taskId)){
         throw new Exception();
      }
   }
}
