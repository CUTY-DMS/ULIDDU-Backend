package com.project.todolist.service;

import com.project.todolist.dto.request.CreateTodoRequest;
import com.project.todolist.dto.request.FindTodoListRequest;
import com.project.todolist.dto.request.ModifyTodoRequest;
import com.project.todolist.dto.response.CreateTodoResponse;
import com.project.todolist.dto.response.ModifyTodoResponse;
import com.project.todolist.dto.response.ToggleTodoCompleteResponse;
import com.project.todolist.dto.response.FindTodoInfoResponse;
import com.project.todolist.dto.response.FindTodoResponse;
import com.project.todolist.entity.todo.Todo;
import com.project.todolist.entity.todo.TodoRepository;
import com.project.todolist.entity.user.User;
import com.project.todolist.exception.ForbiddenException;
import com.project.todolist.exception.TodoNotFoundException;
import com.project.todolist.facade.LikeFacade;
import com.project.todolist.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    private final UserFacade userFacade;

    private final LikeFacade likeFacade;

    public CreateTodoResponse createTodo(CreateTodoRequest request) {

        User user = userFacade.currentUser();

        Todo todo = Todo
                .builder()
                .writer(user)
                .title(request.getTitle())
                .todoDate(request.getTodoDate())
                .content(request.getContent())
                .isCompleted(false)
                .build();

        return new CreateTodoResponse(todoRepository.save(todo).getId());
    }

    public ToggleTodoCompleteResponse completeTodo(Long id) {

        User user = userFacade.currentUser();

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> TodoNotFoundException.EXCEPTION);

        writerCheck(user, todo);

        if (todo.getIsCompleted()) {
            todo.setIsNotCompleted();
        } else {
            todo.setIsCompleted();
        }

        todoRepository.save(todo);
        return new ToggleTodoCompleteResponse(todo.getIsCompleted());
    }

    private void writerCheck(User user, Todo todo) {
        if (todo.getWriter() != user) throw ForbiddenException.EXCEPTION;
    }

    public ModifyTodoResponse modifyTodo(Long id, ModifyTodoRequest request) {

        User user = userFacade.currentUser();

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> TodoNotFoundException.EXCEPTION);

        writerCheck(user, todo);

        todo.modifyTitleAndContent(request.getTitle(), request.getContent());

        return new ModifyTodoResponse(todoRepository.save(todo).getId());
    }

    public void deleteTodo(Long id) {

        User user = userFacade.currentUser();

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> TodoNotFoundException.EXCEPTION);

        writerCheck(user, todo);

        todoRepository.save(todo);
    }

    public FindTodoInfoResponse findTodoInfo(Long id) {

        User user = userFacade.currentUser();

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> TodoNotFoundException.EXCEPTION);

        Boolean isLiked = likeFacade.isLiked(user, todo);

        return FindTodoInfoResponse.of(todo, isLiked);
    }

    public List<FindTodoResponse> findUserTodo(Long id, FindTodoListRequest request) {

        LocalDate startDate = request.getTodoYearMonth().atDay(1);
        LocalDate endDate = request.getTodoYearMonth().atEndOfMonth();

        Sort sort = Sort.by(Sort.Direction.ASC, "todoDate");

        return todoRepository.findByWriter_IdAndTodoDateBetween(id, startDate, endDate, sort)
                .stream()
                .map(FindTodoResponse::of)
                .collect(Collectors.toList());
    }

    public List<FindTodoResponse> findMyTodo(FindTodoListRequest request) {

        User user = userFacade.currentUser();

        LocalDate startDate = request.getTodoYearMonth().atDay(1);
        LocalDate endDate = request.getTodoYearMonth().atEndOfMonth();

        Sort sort = Sort.by(Sort.Direction.ASC, "todoDate");

        return todoRepository.findByWriter_IdAndTodoDateBetween(user.getId(), startDate, endDate, sort)
                .stream()
                .map(FindTodoResponse::of)
                .collect(Collectors.toList());
    }

}