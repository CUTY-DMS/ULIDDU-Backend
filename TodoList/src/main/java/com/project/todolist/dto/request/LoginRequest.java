package com.project.todolist.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class LoginRequest {

    @NotNull(message = "아이디를 입력해주세요")
    @Size(max = 10, message = "아이디의 길이는 10자이하여야합니다")
    private String userId;

    @NotNull(message = "Password empty")
    @Size(min = 8, max = 15, message = "비밀번호의 길이는 8자이상 15자이하")
    private String password;
}