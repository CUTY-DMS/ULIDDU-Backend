package com.project.todolist.dto.response;


import com.project.todolist.entity.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class SearchUserResponse {

    Long id;

    public static SearchUserResponse of(User user) {
        return SearchUserResponse
                .builder()
                .id(user.getId())
                .build();
    }
}