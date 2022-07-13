package com.project.todolist.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CreateTodoRequest {

    @NotNull(message = "TODO 제목을 입력해주세요")
    private String title;

    @NotNull(message = "TODO 내용을 입력해주세요")
    private String content;

    @NotNull(message = "공개여부를 설정해주세요")
    private Boolean isPublic;

    @NotNull(message = "TODO 날짜를 입력해주세요")
    private LocalDate todoDate;

}