package com.example.todolist.services;

import com.example.todolist.models.Task;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/tasks")
public class TaskService {

   @PostMapping(value = "")
   public void createTask(@RequestBody Task task){

   }

   @PatchMapping(value = "")
   public void updateTask(@RequestBody Task task){

   }

   @DeleteMapping(value = "")
   public void deleteTask(@RequestParam String id){

   }

}
