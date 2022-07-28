package com.project.todolist.exception;

import com.project.todolist.error.exception.BusinessException;
import com.project.todolist.error.exception.ErrorCode;

public class UserForbiddenException extends BusinessException {
    public static final BusinessException EXCEPTION = new UserForbiddenException();
    public UserForbiddenException(){
        super(ErrorCode.USER_FORBIDDEN);
    }
}