package com.example.todolist.controller;

import com.example.todolist.core.Constants;
import com.example.todolist.entity.Todo;
import com.example.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(value = "/todo")
    public ResponseEntity<List<Todo>> getAllTodo() {
        return ResponseEntity.ok().body(todoService.getAllTodo());
    }

}
