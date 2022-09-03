package com.example.todolist.repository;

import com.example.todolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
//    @Query("SELECT e FROM job e  ORDER BY jobs.dueDate ASC")
//    List<TodoEntity> findAllTodo();

//    @Query("SELECT u FROM Todo u WHERE true ")
//@Query("SELECT t FROM Todo t WHERE t.taskname LIKE %?1%")
@Query(value = "SELECT u FROM Todo u")
    List<Todo> findAll();

    Todo findTodoByTaskname(String taskName);
}
