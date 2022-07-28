package com.project.todolist.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CreateTodoRequest {

    @NotNull(message = "title을 입력해주세요")
    private String title;

    @NotNull(message = "content를 입력해주세요")
    private String content;

    @NotNull(message = "공개여부를 입력해주세요")
    private Boolean ispublic;

    @NotNull(message = "todoDate를 입력해주세요")
    private LocalDate todoDate;

}