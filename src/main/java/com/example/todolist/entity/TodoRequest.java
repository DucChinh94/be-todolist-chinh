package com.example.todolist.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TodoRequest {
    @NonNull
    Short id;

    @NonNull
    String taskName;

    String description;

    @NonNull
    Long flag;
}
