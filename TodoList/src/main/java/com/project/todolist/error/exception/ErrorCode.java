package com.project.todolist.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    //요청 형식 오류
    INVALID_PARAMETER(400, "요청정보가 잘못됐습니다"),
    METHOD_NOT_ALLOWED(405, "해당 메서드를 찾을 수 없습니다"),

    //토큰
    TOKEN_EXPIRED(401 , "만료된 토큰입니다"),
    TOKEN_INVALID(401, "인증되지 않은 토큰입니다"),

    //유저
    USER_NOT_FOUND(404,"유저를 찾을 수 없습니다"),
    USER_ALREADY_EXISTS(409, "이미 존재하는 유저입니다"),
    FORBIDDEN(403, "권한이 없습니다"),

    //투두
    TODO_NOT_FOUND(404, "해당 투두를 찾을 수 없습니다"),

    //서버문제
    INTERNAL_SERVER_ERROR(500,"서버 에러");

    private final int statusCode;
    private final String message;
}