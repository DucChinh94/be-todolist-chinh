package com.example.todolist.security.jwt;

import com.example.todolist.entity.Todo;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JWTTokenResponse {
    String jwt;
    Todo todoEntity;
}
