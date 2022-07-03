package com.project.todolist.exception;

import com.project.todolist.error.exception.BusinessException;
import com.project.todolist.error.exception.ErrorCode;

public class ForbiddenException extends BusinessException {
    public static final BusinessException EXCEPTION = new ForbiddenException();
    private ForbiddenException(){
        super(ErrorCode.FORBIDDEN);
    }
}