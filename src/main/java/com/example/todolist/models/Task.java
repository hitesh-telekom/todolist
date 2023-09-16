package com.example.todolist.models;

import com.example.todolist.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {
   private String id;
   private String title;
   private String description;
   private StatusEnum status;
   private int points;
}
