package com.example.todolist.services;

import com.example.todolist.models.TaskList;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/api/taskList")
public class TaskListService {

   @PostMapping(value = "")
   public void createTaskList(@RequestBody TaskList taskList){

   }

   @PatchMapping(value = "")
   public void updateTaskList(@RequestBody TaskList taskList){

   }

   @DeleteMapping(value = "")
   public void deleteTaskList(@RequestParam String id){

   }

}
