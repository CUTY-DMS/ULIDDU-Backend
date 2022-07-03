package com.project.todolist.entity.todo;

import com.project.todolist.entity.user.User;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface LikeRepository extends CrudRepository<Like, Long> {

    Optional<Like> findByUserAndTodo(User user, Todo todo);

}