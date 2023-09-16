package com.example.todolist.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskList {
   private String id;
   private String title;
   private List<Task> taskList;
   private List<Reward> rewardList;
}
