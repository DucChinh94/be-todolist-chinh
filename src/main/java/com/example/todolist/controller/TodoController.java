package com.example.todolist.controller;

import com.example.todolist.core.Constants;
import com.example.todolist.dto.TodoRequest;
import com.example.todolist.dto.respones.ResponseObject;
import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoRepository;
import com.example.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class TodoController {

    @Autowired
    ModelMapper modelMapper;

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject>getAllTodo(){
        List<Todo>todoList = todoService.getAllTodo();
        List<TodoRequest> todoDtoList = modelMapper.map(todoList, new TypeToken<List<Todo>>() {}.getType());
        return ResponseEntity.ok(new ResponseObject(HttpStatus.OK, Constants.SUCCESS, todoDtoList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getTodoById(@PathVariable Long id) {
        Todo todo = todoService.getTodoById(id);
        TodoRequest todoDto = modelMapper.map(todo, TodoRequest.class);
        return ResponseEntity.ok(new ResponseObject(HttpStatus.OK, Constants.SUCCESS,todoDto));
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseObject>  addTodo(@RequestBody @Valid TodoRequest todoRequest) {
        return  ResponseEntity.ok().body(todoService.createAndUpdateTodo(todoRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteTodo(@PathVariable long id) {
        return  ResponseEntity.ok().body(todoService.deleteTodo(id));
    }

    @DeleteMapping("/deleteAllTodo")
    public ResponseEntity<ResponseObject> deleteAllTodo() {
        return ResponseEntity.ok().body(todoService.deleteAllTodo());
    }

    @DeleteMapping("/deleteAllTodoTrash")
    public ResponseEntity<ResponseObject> deleteALLTodoTrash() {
        return  ResponseEntity.ok().body(todoService.deleteALLTodoTrash());
    }

    @GetMapping ("/search")
    public  ResponseEntity<ResponseObject>  getByTaskName(@RequestParam String name){
        List<Todo> todos = todoService.findTodoByTaskName(name);
        return ResponseEntity.ok(new ResponseObject(HttpStatus.OK, Constants.SUCCESS,todos));
    }

    @GetMapping ("/searchDeleted")
    public  ResponseEntity<ResponseObject>  getDeletedByName(@RequestParam String name){
        List<Todo> todos = todoService.findDeletedByTaskName(name);
        return ResponseEntity.ok(new ResponseObject(HttpStatus.OK, Constants.SUCCESS,todos));
    }

    @GetMapping ("/deletedList")
    public ResponseEntity<ResponseObject>  getDeleteTodoList(){
        List<Todo> deletedTodoList = todoService.getDeleteTodoList();
        return ResponseEntity.ok(new ResponseObject(HttpStatus.OK, Constants.SUCCESS,deletedTodoList));
    }
}
