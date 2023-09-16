package com.example.todolist.services;

import com.example.todolist.models.TaskList;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ValidationService {

   public void validateUpdateTaskList(TaskList taskList) throws Exception {
      if(StringUtils.isEmpty(taskList)){
         throw new Exception();
      }
   }
}
