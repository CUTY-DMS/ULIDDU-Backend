package com.project.todolist.controller;

import com.project.todolist.dto.response.ToggleLikeResponse;
import com.project.todolist.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PutMapping("/todo/{id}/like")
    public ToggleLikeResponse doLike(@PathVariable Long id){
        return likeService.doLike(id);
    }
}