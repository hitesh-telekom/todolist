package com.example.todolist.services;

import com.example.todolist.models.TaskList;
import com.example.todolist.repositories.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
      if(!taskListOptional.isPresent()){
         return null;
      }
      return taskListOptional.get();
   }

   @PostMapping(value = "")
   public TaskList createTaskList(@RequestBody TaskList taskList){
      TaskList savedTaskList = taskListRepository.save(taskList);
      return savedTaskList;
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

}
