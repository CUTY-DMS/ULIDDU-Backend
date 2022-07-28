package com.project.todolist.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class RegisterRequest {
    @NotNull(message = "userId를 입력해주세요")
    @Size(max = 10, message = "userId는 10자 이하여야합니다")
    private String userId;

    @NotNull(message = "name을 입력해주세요")
    @Size(max = 10, message = "name은 10자 이하여야합니다")
    private String name;

    @NotNull(message = "age를 입력해주세요")
    private Integer age;

    @NotNull(message = "password를 입력해주세요")
    @Size(min = 8, max = 15, message = "password는 8자 이상 15자 이하여야합니다.")
    private String password;

}