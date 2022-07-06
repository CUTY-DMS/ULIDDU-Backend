package com.project.todolist.dto.response;

import com.project.todolist.entity.todo.Todo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
@AllArgsConstructor
public class FindTodoResponse {

    private Long id;

    private String title;

    private LocalDate todoDate;

    private Boolean iscompleted;

    public static FindTodoResponse of(Todo todo) {
        return FindTodoResponse
                .builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .todoDate(todo.getTodoDate())
                .iscompleted(todo.getIsCompleted())
                .build();
    }
}