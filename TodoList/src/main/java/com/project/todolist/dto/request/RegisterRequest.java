package com.project.todolist.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class RegisterRequest {
    @NotNull(message = "Id empty")
    @Size(max = 10, message = "not Id.length() <= 10")
    private String userId;

    @NotNull(message = "Name empty")
    @Size(max = 10, message = "not name.length() <= 10")
    private String name;

    @NotNull(message = "Age empty")
    private Integer age;

    @NotNull(message = "Password empty")
    @Size(min = 8, max = 15, message = "not 8 <= password.length() <= 15")
    private String password;

}