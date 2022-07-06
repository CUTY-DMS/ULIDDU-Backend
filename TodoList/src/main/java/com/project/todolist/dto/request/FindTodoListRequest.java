package com.project.todolist.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class FindTodoListRequest {
    @NotNull(message = "조회할 날짜를 입력해주세요")
    LocalDate todoYearAndMonth;
}