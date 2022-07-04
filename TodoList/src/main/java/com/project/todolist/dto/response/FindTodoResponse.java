package com.project.todolist.dto.response;

import com.project.todolist.entity.todo.Todo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
@AllArgsConstructor
public class FindTodoResponse {

    private Long id;

    private String title;

    private String createdDate;

    private String writer;

    private Boolean iscompleted;

    public static FindTodoResponse of(Todo todo) {
        return FindTodoResponse
                .builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .createdDate(todo.getCreatedDate()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .writer(todo.getWriter().getName())
                .iscompleted(todo.getIsCompleted())
                .build();
    }
}