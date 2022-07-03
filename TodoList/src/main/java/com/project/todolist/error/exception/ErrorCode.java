package com.project.todolist.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    //요청 형식 오류
    INVALID_PARAMETER(400, "Invalid Parameter"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),

    //토큰
    TOKEN_EXPIRED(401 , "Expired Token"),
    TOKEN_INVALID(401, "Invalid Token"),

    //유저
    USER_NOT_FOUND(404,"User Nor Found"),
    USER_ALREADY_EXISTS(409, "User Already Exists"),
    FORBIDDEN(403, "Forbidden"),

    //투두
    TODO_NOT_FOUND(404, "Todo Not Found"),

    //서버문제
    INTERNAL_SERVER_ERROR(500,"Server Error");

    private final int statusCode;
    private final String message;
}