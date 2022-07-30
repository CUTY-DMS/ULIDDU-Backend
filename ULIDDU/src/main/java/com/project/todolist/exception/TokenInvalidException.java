package com.project.todolist.exception;

import com.project.todolist.error.exception.BusinessException;
import com.project.todolist.error.exception.ErrorCode;

public class TokenInvalidException extends BusinessException {
    public static final BusinessException EXCEPTION = new TokenInvalidException();
    public TokenInvalidException(){
        super(ErrorCode.TOKEN_INVALID);
    }
}