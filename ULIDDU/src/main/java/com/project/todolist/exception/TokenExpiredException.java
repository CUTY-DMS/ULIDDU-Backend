package com.project.todolist.exception;

import com.project.todolist.error.exception.BusinessException;
import com.project.todolist.error.exception.ErrorCode;

public class TokenExpiredException extends BusinessException {
    public static final BusinessException EXCEPTION = new TokenExpiredException();
    public TokenExpiredException(){
        super(ErrorCode.TOKEN_EXPIRED);
    }
}