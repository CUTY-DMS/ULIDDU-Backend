package com.project.todolist.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class LoginRequest {

    @NotNull(message = "userId를 입력해주세요")
    @Size(max = 10, message = "userId의 길이는 10자이하여야합니다")
    private String userId;

    @NotNull(message = "Password를 입력해주세요")
    @Size(min = 8, max = 15, message = "Password의 길이는 8자이상 15자이하여야합니다")
    private String password;
}