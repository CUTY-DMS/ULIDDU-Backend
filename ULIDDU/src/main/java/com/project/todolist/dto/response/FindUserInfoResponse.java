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
public class FindUserInfoResponse {

    private String name;

    private String userId;

    private int age;

    public static FindUserInfoResponse of(User user) {

        return FindUserInfoResponse
                .builder()
                .name(user.getName())
                .userId(user.getUserId())
                .age(user.getAge())
                .build();
    }
}