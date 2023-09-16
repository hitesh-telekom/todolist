package com.example.todolist.repositories;

import com.example.todolist.models.TaskList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskListRepository extends MongoRepository<TaskList, String> {

}
