package com.project.todolist.controller;

import com.project.todolist.dto.request.LoginRequest;
import com.project.todolist.dto.request.RegisterRequest;
import com.project.todolist.dto.response.FindUserInfoResponse;
import com.project.todolist.dto.response.SearchUserResponse;
import com.project.todolist.dto.response.TokensResponse;
import com.project.todolist.service.AuthService;
import com.project.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterRequest request) {
        userService.register(request);
    }

    @GetMapping("/user")
    public FindUserInfoResponse findMyInfo() {
        return userService.findMyInfo();
    }

    @GetMapping("/user/{userId}")
    public FindUserInfoResponse findUserInfo(@PathVariable Long userId) {
        return userService.findUserInfo(userId);
    }

    @GetMapping("/search/{userId}")
    public SearchUserResponse searchUser(@PathVariable String userId){
        return userService.searchUser(userId);
    }

    private final AuthService authService;

    @PostMapping("/login")
    public TokensResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PutMapping("/reissue")
    public TokensResponse reissue(@RequestHeader(name = "X-Refresh-Token") String refreshToken) {
        return authService.reissue(refreshToken);
    }
}