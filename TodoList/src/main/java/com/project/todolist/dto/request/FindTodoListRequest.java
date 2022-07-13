package com.project.todolist.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.YearMonth;

@Getter
@NoArgsConstructor
public class FindTodoListRequest {
    @NotNull(message = "조회할 기간을 설정해주세요")
    YearMonth todoYearMonth;
}