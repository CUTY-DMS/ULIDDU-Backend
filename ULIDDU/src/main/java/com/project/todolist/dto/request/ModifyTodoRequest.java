package com.project.todolist.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ModifyTodoRequest {

    @NotNull(message = "title을 입력해주세요")
    private String title;

    @NotNull(message = "content를 입력해주세요")
    private String content;

    @NotNull(message = "공개여부를 설정해주세요")
    private Boolean isPublic;
}