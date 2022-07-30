package com.project.todolist.exception;

import com.project.todolist.error.exception.BusinessException;
import com.project.todolist.error.exception.ErrorCode;

public class TodoNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new TodoNotFoundException();
    private TodoNotFoundException(){
        super(ErrorCode.TODO_NOT_FOUND);
    }
}