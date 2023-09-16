package com.example.todolist.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StatusEnum {
   PENDING("pending"),
   STASHED("stashed"),
   DONE("done");

   private String value;

   @JsonCreator
   public StatusEnum fromValue(String status) {
      for (StatusEnum b : StatusEnum.values()) {
         if(b.getValue().equalsIgnoreCase(status)) {
            return b;
         }
      }
      return null;
   }

   @JsonValue
   public String getValue() {
      return value;
   }

}
