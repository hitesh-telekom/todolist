package com.example.todolist.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskList {
   @Id
   private String id;
   private String title;
   private List<Task> taskList;
   private List<Reward> rewardList;
}
