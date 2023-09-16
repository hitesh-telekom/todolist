package com.example.todolist.services;

import com.example.todolist.models.Reward;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/rewards")
public class RewardService {

   @PostMapping(value = "")
   public void createReward(@RequestBody Reward reward){

   }

   @PatchMapping(value = "")
   public void updateReward(@RequestBody Reward reward){

   }

   @DeleteMapping(value = "")
   public void deleteReward(@RequestParam String id){

   }

}
