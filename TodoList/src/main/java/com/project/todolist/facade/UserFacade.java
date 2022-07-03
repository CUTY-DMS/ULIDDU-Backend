package com.project.todolist.facade;

import com.project.todolist.entity.user.User;
import com.project.todolist.entity.user.UserRepository;
import com.project.todolist.exception.TokenInvalidException;
import com.project.todolist.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;

    public User currentUser() {
        return SecurityUtil.getCurrentUserId()
                .flatMap(userRepository::findByUserId)
                .orElseThrow(()-> TokenInvalidException.EXCEPTION);
    }
}