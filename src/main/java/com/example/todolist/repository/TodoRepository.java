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

    Optional<Todo> findByIdAndDeleteFlag(Long id, Boolean deleteFlag);

//    @Query("SELECT t FROM Todo t WHERE t.taskName LIKE %?1% AND t.deleteFlag = ?2" )
//    public List<Todo> findTodoByTaskName(String name , Boolean deleteFlag);

    @Query(value = "SELECT * FROM Todo t WHERE utl_raw.cast_to_nvarchar2(nlssort(t.taskname, 'nls_sort=binary_ai')) LIKE %?1% AND t.flag = ?2", nativeQuery = true )
    public List<Todo> findTodoByTaskName(String name , Boolean deleteFlag);

    @Query("SELECT t FROM Todo t WHERE t.deleteFlag = ?1")
    public List<Todo> findAllAndFlag(Boolean deleteFlag);

}
