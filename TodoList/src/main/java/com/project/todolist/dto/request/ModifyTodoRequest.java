package com.project.todolist.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ModifyTodoRequest {

    @NotNull(message = "TODO 제목을 입력하세요.")
    private String title;

    @NotNull(message = "TODO 내용을 입력하세요.")
    private String content;
}