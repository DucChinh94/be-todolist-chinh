package com.example.todolist.service;

import com.example.todolist.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TodoService {

    List<Todo> getAllTodo();

//    TodoEntity getById(long id);
//    void saveTodo(TodoEntity todoEntity);
//    void deleteTodo(Long id);
//    Optional<TodoEntity> findTodoById(Long id);
}
