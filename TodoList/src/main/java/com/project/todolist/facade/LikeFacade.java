package com.project.todolist.facade;

import com.project.todolist.entity.todo.Like;
import com.project.todolist.entity.todo.LikeRepository;
import com.project.todolist.entity.todo.Todo;
import com.project.todolist.entity.user.User;
import com.project.todolist.entity.user.UserRepository;
import com.project.todolist.exception.TokenInvalidException;
import com.project.todolist.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class LikeFacade {

    private final LikeRepository likeRepository;

    private final UserRepository userRepository;

    public Boolean isLiked(User user, Todo todo) {

        Optional<Like> like = likeRepository.findByUserAndTodo(user,todo);

        return like.isPresent();
    }
}