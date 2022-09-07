package com.example.todolist.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "todo")
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Todo implements Serializable {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqe")
    @SequenceGenerator(name = "sqe", sequenceName = "todo_sqe", allocationSize = 1, initialValue = 1)
    Long id;

    @Basic
    @Column(name = "TASKNAME")
    String taskName;

    @Basic
    @Column(name = "DESCRIPTION")
    String description;

    @Basic
    @Column(name = "FLAG")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    Boolean deleteFlag;

}
