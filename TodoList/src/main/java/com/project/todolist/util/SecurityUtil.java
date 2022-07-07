package com.project.todolist.util;

import com.project.todolist.exception.ForbiddenException;
import com.project.todolist.exception.TokenInvalidException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class SecurityUtil {
    public static String getCurrentUserId() {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = Optional.ofNullable((UserDetails) authentication.getPrincipal())
                .orElseThrow(() -> TokenInvalidException.EXCEPTION);

        return userDetails.getUsername();
    }
}