package com.example.todolist.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TodoRequest {
    @NonNull
    Long id;

    @NonNull
    String taskName;

    String description;

    @NonNull
    Boolean flag;
}
