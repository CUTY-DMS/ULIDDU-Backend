package com.project.todolist.entity.todo;


import com.project.todolist.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {

    List<Todo> findByWriter_IdAndTodoDateBetween(Long writer_id, LocalDate startDate, LocalDate endDate, Sort sort);

}