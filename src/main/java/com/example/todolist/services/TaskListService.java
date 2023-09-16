package com.example.todolist.services;

import com.example.todolist.models.Task;
import com.example.todolist.models.TaskList;
import com.example.todolist.repositories.TaskListRepository;
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
public class TaskListService {

   @Autowired
   TaskListRepository taskListRepository;
   @Autowired
   ValidationService validationService;

   @GetMapping(value = "")
   public List<TaskList> getAllTaskList(){
      return taskListRepository.findAll();
   }

   @GetMapping(value = "/{id}")
   public TaskList getTaskList(@PathVariable String id){
      Optional<TaskList> taskListOptional = taskListRepository.findById(id);
      return taskListOptional.orElse(null);
   }

   @PostMapping(value = "")
   public TaskList createTaskList(@RequestBody TaskList taskList){
      return taskListRepository.save(taskList);
   }

   @PatchMapping(value = "")
   public TaskList updateTaskList(@RequestBody TaskList taskList) throws Exception {
      validationService.validateUpdateTaskList(taskList);
      Optional<TaskList> taskListOptional = taskListRepository.findById(taskList.getId());
      if(!taskListOptional.isPresent()){
         return null;
      }

      TaskList updatedTaskList = taskListOptional.get();
      updatedTaskList.setTitle(taskList.getTitle());
      return taskListRepository.save(updatedTaskList);
   }

   @DeleteMapping(value = "")
   public void deleteTaskList(@RequestParam String id){
      taskListRepository.deleteById(id);
   }

   /*Task Related APIs*/
   @PostMapping(value = "/{id}/tasks")
   public TaskList createTask(@PathVariable("id") String taskListId, @RequestBody Task task) throws Exception {
      validationService.validateCreateTaskRequest(taskListId, task);

      Optional<TaskList> taskListOptional = taskListRepository.findById(taskListId);
      if(!taskListOptional.isPresent()){
         return null;
      }

      TaskList taskList = taskListOptional.get();
      if(taskList.getTaskList() == null){
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
      if(!taskListOptional.isPresent()){
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
      if(!taskListOptional.isPresent()){
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

}
