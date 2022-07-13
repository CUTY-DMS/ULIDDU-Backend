package com.project.todolist.controller;

import com.project.todolist.dto.request.CreateTodoRequest;
import com.project.todolist.dto.request.FindTodoListRequest;
import com.project.todolist.dto.request.ModifyTodoRequest;
import com.project.todolist.dto.response.CreateTodoResponse;
import com.project.todolist.dto.response.FindTodoInfoResponse;
import com.project.todolist.dto.response.FindTodoResponse;
import com.project.todolist.dto.response.ModifyTodoResponse;
import com.project.todolist.dto.response.ToggleTodoCompleteResponse;
import com.project.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/todo")
    public CreateTodoResponse createTodo(@RequestBody @Valid CreateTodoRequest request){
        return todoService.createTodo(request);
    }

    @PutMapping("/todo/{id}")
    public ToggleTodoCompleteResponse completeTodo(@PathVariable Long id){
        return todoService.completeTodo(id);
    }

    @PatchMapping("/todo/{id}")
    public ModifyTodoResponse modifyTodo(@PathVariable Long id, @RequestBody @Valid ModifyTodoRequest request){
        return todoService.modifyTodo(id, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/todo/{id}")
    public void deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
    }

    @GetMapping("/todo/list/user/{id}")
    public List<FindTodoResponse> findUserTodo(@PathVariable Long id, @RequestBody @Valid FindTodoListRequest request) {
        return todoService.findUserTodo(id, request);
    }

    @GetMapping("/todo/list")
    public List<FindTodoResponse> findMyTodo(@RequestBody @Valid FindTodoListRequest request) {
        return todoService.findMyTodo(request);
    }

    @GetMapping("/todo/{id}")
    public FindTodoInfoResponse findTodo(@PathVariable Long id) {
        return todoService.findTodoInfo(id);
    }
}