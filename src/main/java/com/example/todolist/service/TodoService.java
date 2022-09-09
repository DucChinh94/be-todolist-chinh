package com.example.todolist.service;

import com.example.todolist.dto.TodoRequest;
import com.example.todolist.dto.respones.ResponseObject;
import com.example.todolist.entity.Todo;

import java.util.List;

public interface TodoService {

    List<Todo> getAllTodo();

    Todo getTodoById(Long id);

    ResponseObject createAndUpdateTodo(TodoRequest todoRequest);

      ResponseObject deleteTodo(long id);

    List<Todo> getDeleteTodoList();

    List<Todo> findTodoByTaskName(String taskname);

    List<Todo> findDeletedByTaskName(String taskname);

    ResponseObject deleteAllTodo();

    ResponseObject deleteALLTodoTrash();

    ResponseObject deleteTodoTrash(long id);

    ResponseObject responseAllTodo();

    ResponseObject responseTodo(long id);
}
