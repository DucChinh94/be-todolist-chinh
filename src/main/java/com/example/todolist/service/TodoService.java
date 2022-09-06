package com.example.todolist.service;

import com.example.todolist.dto.TodoRequest;
import com.example.todolist.dto.respones.ResponseObject;
import com.example.todolist.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TodoService {

    List<Todo> getAllTodo();

    Todo getTodoById(Long id);

    ResponseObject createAndUpdateTodo(TodoRequest todoRequest);

      ResponseObject deleteTodo(long id);

    List<Todo> getDeleteTodoList();

    List<Todo> findTodoByTaskname(String taskname);

    List<Todo> findDeletedByTaskname(String taskname);
}
