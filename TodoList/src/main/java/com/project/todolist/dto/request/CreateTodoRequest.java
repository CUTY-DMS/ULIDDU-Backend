package com.project.todolist.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CreateTodoRequest {

    @NotNull(message = "TODO 제목을 입력하세요.")
    private String title;

    @NotNull(message = "TODO 내용을 입력하세요.")
    private String content;

    @NotNull(message = "날짜를 지정해주세요.")
    private LocalDate todoDate;

}