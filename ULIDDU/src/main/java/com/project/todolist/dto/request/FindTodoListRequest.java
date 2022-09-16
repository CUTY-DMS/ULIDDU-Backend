package com.project.todolist.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.YearMonth;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindTodoListRequest {
    @NotNull(message = "조회할 기간을 입력해주세요")
    YearMonth todoYearMonth;
}