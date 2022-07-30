package com.project.todolist.facade;

import com.project.todolist.entity.user.User;
import com.project.todolist.entity.user.UserRepository;
import com.project.todolist.exception.TokenInvalidException;
import com.project.todolist.exception.UserNotFoundException;
import com.project.todolist.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;

    public User currentUser() {

        Object detail = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!(detail instanceof UserDetails)) throw TokenInvalidException.EXCEPTION;

        return userRepository.findByUserId(((UserDetails) detail).getUsername())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}