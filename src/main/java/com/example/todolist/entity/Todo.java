package com.example.todolist.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table (name = "todo")
@NoArgsConstructor
@Data
public class Todo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private short id;
    @Basic
    @Column(name = "TASKNAME")
    private String taskname;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic
    @Column(name = "FLAG")
    private BigInteger flag;

//    public short getId() {
//        return id;
//    }
//
//    public void setId(short id) {
//        this.id = id;
//    }
//
//    public String getTaskname() {
//        return taskname;
//    }
//
//    public void setTaskname(String taskname) {
//        this.taskname = taskname;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public BigInteger getFlag() {
//        return flag;
//    }
//
//    public void setFlag(BigInteger flag) {
//        this.flag = flag;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Todo todo = (Todo) o;
//        return id == todo.id && Objects.equals(taskname, todo.taskname) && Objects.equals(description, todo.description) && Objects.equals(flag, todo.flag);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, taskname, description, flag);
//    }
}
