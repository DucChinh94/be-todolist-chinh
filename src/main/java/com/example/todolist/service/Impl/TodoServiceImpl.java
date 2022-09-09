package com.example.todolist.service.Impl;

import com.example.todolist.core.Constants;
import com.example.todolist.dto.TodoRequest;
import com.example.todolist.dto.respones.ResponseObject;
import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoRepository;
import com.example.todolist.service.TodoService;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Todo> getAllTodo() {
        return todoRepository.findAllAndFlag(Constants.FALSE_DELETE);
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoRepository.findByIdAndDeleteFlag(id, Constants.FALSE_DELETE)
                .orElseThrow(() -> new IllegalStateException(Constants.RECORD_NOT_FOUND));
    }

    @Override
    public ResponseObject createAndUpdateTodo(TodoRequest todoRequest) {
        Todo todo = modelMapper.map(todoRequest, Todo.class);
        Optional<Todo> updateTodo = todoRepository.findById(todoRequest.getId());
        if (updateTodo.isPresent()) {
            updateTodo.get().setTaskName(todo.getTaskName());
            updateTodo.get().setDescription(todo.getDescription());
            updateTodo.get().setDeleteFlag(todo.getDeleteFlag());
            todoRepository.save(updateTodo.get());
            return new ResponseObject(HttpStatus.OK, Constants.UPDATE_SUCCESS, todoRequest);
        }else {
            var todoUpdated = todoRepository.save(todo);
            return new ResponseObject(HttpStatus.OK, Constants.CREATE_SUCCESS, todoUpdated);
        }
    }

    @Override
    public ResponseObject deleteTodo(long id) {
        Optional<Todo> todoDelete = todoRepository.findById(id);
        if (todoDelete.isPresent()) {
            Todo todo =  new Todo();
            todo.setId(todoDelete.get().getId());
            todo.setDescription(todoDelete.get().getDescription());
            todo.setTaskName(todoDelete.get().getTaskName());
            todo.setDeleteFlag(true);
            todoRepository.save(todo);
            return new ResponseObject(HttpStatus.OK, Constants.DELETE_SUCCESS, "");
        }else {
            throw new IllegalStateException(Constants.RECORD_NOT_FOUND);
        }
    }

    @Override
    public ResponseObject deleteAllTodo() {
        List<Todo>todoList = this.getAllTodo();
        for (Todo todo: todoList) {
            Todo todoDelete = new Todo();
            todoDelete.setId(todo.getId());
            todoDelete.setDescription(todo.getDescription());
            todoDelete.setTaskName(todo.getTaskName());
            todoDelete.setDeleteFlag(true);
            todoRepository.save(todoDelete);
        }
        return new ResponseObject(HttpStatus.OK, Constants.DELETE_SUCCESS, "");
    }

    @Override
    public ResponseObject deleteALLTodoTrash() {
        List<Todo> todoList = this.getDeleteTodoList();
        todoRepository.deleteAll(todoList);
        return new ResponseObject(HttpStatus.OK, Constants.DELETE_SUCCESS, "");
    }

    @Override
    public ResponseObject deleteTodoTrash(long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()){
                todoRepository.deleteById(id);
            return new ResponseObject(HttpStatus.OK, Constants.DELETE_SUCCESS, "");
        } else {
            throw new IllegalStateException(Constants.RECORD_NOT_FOUND);
        }
    }

    @Override
    public ResponseObject responseAllTodo() {
        List<Todo> todoList = this.getDeleteTodoList();
        for (Todo todo: todoList) {
            Todo todoResponse = new Todo();
            todoResponse.setId(todo.getId());
            todoResponse.setTaskName(todo.getTaskName());
            todoResponse.setDescription(todo.getDescription());
            todoResponse.setDeleteFlag(false);
            todoRepository.save(todoResponse);
        }
        return new ResponseObject(HttpStatus.OK, Constants.DELETE_SUCCESS, "");
    }

    @Override
    public ResponseObject responseTodo(long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            Todo todoResponse =  new Todo();
            todoResponse.setId(todoOptional.get().getId());
            todoResponse.setDescription(todoOptional.get().getDescription());
            todoResponse.setTaskName(todoOptional.get().getTaskName());
            todoResponse.setDeleteFlag(false);
            todoRepository.save(todoResponse);
            return new ResponseObject(HttpStatus.OK, Constants.DELETE_SUCCESS, "");
        }else {
            throw new IllegalStateException(Constants.RECORD_NOT_FOUND);
        }
    }

    @Override
    public List<Todo> getDeleteTodoList() {
        return todoRepository.findAllAndFlag(Constants.TRUE_DELETE);
    }

    @Override
    public List<Todo> findTodoByTaskName(String taskname) {
        String nameStripAccented = StringUtils.stripAccents(taskname);
        // TODO replace đ/ Đ
        List<Todo> todos = todoRepository.findTodoByTaskName(nameStripAccented, Constants.FALSE_DELETE );
        return  todos;
    }

    @Override
    public List<Todo> findDeletedByTaskName(String taskname) {
        List<Todo> todos = todoRepository.findTodoByTaskName(taskname, Constants.TRUE_DELETE );
        return  todos;
    }
}
