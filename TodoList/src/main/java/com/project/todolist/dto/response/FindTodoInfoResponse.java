package com.project.todolist.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class FindTodoInfoResponse {

    private Long id;

    private String title;

    private String writer;

    private LocalDate todoDate;

    private String completedDate;

    private Boolean iscompleted;

    private Boolean isliked;

    private Integer likeCount;

    public static FindTodoInfoResponse of(Todo todo, boolean isLiked) {
        assert todo.getCompletedDateTime() != null;
        if(todo.getIsCompleted()){
            return FindTodoInfoResponse
                    .builder()
                    .id(todo.getId())
                    .title(todo.getTitle())
                    .todoDate(todo.getTodoDate())
                    .writer(todo.getWriter().getName())
                    .completedDate(todo.getCompletedDateTime()
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .iscompleted(todo.getIsCompleted())
                    .isliked(isLiked)
                    .likeCount(todo.getLikes().size())
                    .build();
        }else{
            return FindTodoInfoResponse
                    .builder()
                    .id(todo.getId())
                    .title(todo.getTitle())
                    .todoDate(todo.getTodoDate())
                    .writer(todo.getWriter().getName())
                    .iscompleted(todo.getIsCompleted())
                    .isliked(isLiked)
                    .likeCount(todo.getLikes().size())
                    .build();
        }
    }
}