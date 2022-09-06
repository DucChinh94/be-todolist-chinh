package com.example.todolist.repository;

import com.example.todolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>, JpaSpecificationExecutor<Todo> {

    Optional<Todo> findByIdAndFlag(Long id, Boolean flag);

    @Query("SELECT t FROM Todo t WHERE t.taskname LIKE %?1% AND t.flag = ?2" )
    public List<Todo> findTodoByTaskname(String name , Boolean flag);

    @Query("SELECT t FROM Todo t WHERE t.flag = ?1")
    public List<Todo> findAllAndFlag(Boolean flag);

}
