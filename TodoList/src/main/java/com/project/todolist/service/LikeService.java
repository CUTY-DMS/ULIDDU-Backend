package com.project.todolist.service;


import com.project.todolist.dto.response.ToggleLikeResponse;
import com.project.todolist.entity.todo.Like;
import com.project.todolist.entity.todo.LikeRepository;
import com.project.todolist.entity.todo.Todo;
import com.project.todolist.entity.todo.TodoRepository;
import com.project.todolist.entity.user.User;
import com.project.todolist.exception.TodoNotFoundException;
import com.project.todolist.facade.LikeFacade;
import com.project.todolist.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final TodoRepository todoRepository;

    private final LikeRepository likeRepository;

    private final UserFacade userFacade;

    private final LikeFacade likeFacade;

    public ToggleLikeResponse doLike(Long id) {

        User user = userFacade.currentUser();

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> TodoNotFoundException.EXCEPTION);

        if (likeFacade.isLiked(user, todo)) return removeLike(user, todo);
        else return addLike(user, todo);
    }

    private ToggleLikeResponse addLike(User user, Todo todo) {

        Like like = Like
                .builder()
                .user(user)
                .todo(todo)
                .build();

        likeRepository.save(like);

        return new ToggleLikeResponse(true);
    }

    private ToggleLikeResponse removeLike(User user, Todo todo) {

        Optional<Like> like = likeRepository.findByUserAndTodo(user,todo);

        like.ifPresent(likeRepository::delete);

        return new ToggleLikeResponse(false);
    }
}