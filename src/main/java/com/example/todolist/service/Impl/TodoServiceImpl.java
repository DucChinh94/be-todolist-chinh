package com.example.todolist.service.Impl;

import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoRepository;
import com.example.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<Todo> getAllTodo() {
        return (List<Todo>) todoRepository.findAll();
    }
//
////    @Override
////    public TodoEntity getById(long id) {
////        return (TodoEntity) todoRepository.getAllTodo(id);
////    }
//
//    @Override
//    public void saveTodo(TodoEntity todoEntity) {
//        todoRepository.save(todoEntity);
//    }
//
//    @Override
//    public void deleteTodo(Long id) {
//        todoRepository.deleteById(id);
//    }
//
//
//    @Override
//    public Optional<TodoEntity> findTodoById(Long id) {
//        return todoRepository.findById(id);
//    }

}
