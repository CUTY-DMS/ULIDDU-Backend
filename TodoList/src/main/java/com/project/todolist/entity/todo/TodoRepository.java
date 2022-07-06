package com.project.todolist.entity.todo;


import com.project.todolist.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {

    List<Todo> findAllBy();

    List<Todo> findByWriter_Id(Long id);


}