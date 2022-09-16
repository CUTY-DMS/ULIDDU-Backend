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
import com.project.todolist.exception.TodoNotFoundException;
import com.project.todolist.exception.UserForbiddenException;
import com.project.todolist.facade.LikeFacade;
import com.project.todolist.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    private final UserFacade userFacade;

    private final LikeFacade likeFacade;

    @Transactional
    public CreateTodoResponse createTodo(CreateTodoRequest request) {

        User user = userFacade.currentUser();

        Todo todo = Todo
                .builder()
                .writer(user)
                .title(request.getTitle())
                .todoDate(request.getTodoDate())
                .content(request.getContent())
                .isPublic(request.getIspublic())
                .isCompleted(false)
                .build();

        return new CreateTodoResponse(todoRepository.save(todo).getId());
    }

    @Transactional
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
        if (todo.getWriter() != user) {
            throw UserForbiddenException.EXCEPTION;
        }
    }

    @Transactional
    public ModifyTodoResponse modifyTodo(Long id, ModifyTodoRequest request) {

        User user = userFacade.currentUser();

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> TodoNotFoundException.EXCEPTION);

        writerCheck(user, todo);

        todo.modifyTitleAndContent(request.getTitle(), request.getContent(), request.getIsPublic());

        return new ModifyTodoResponse(todoRepository.save(todo).getId());
    }

    public void deleteTodo(Long id) {

        User user = userFacade.currentUser();

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> TodoNotFoundException.EXCEPTION);

        writerCheck(user, todo);

        todoRepository.delete(todo);
    }

    @Transactional(readOnly = true)
    public FindTodoInfoResponse findTodoInfo(Long id) {

        User user = userFacade.currentUser();

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> TodoNotFoundException.EXCEPTION);

        Boolean isLiked = likeFacade.isLiked(user, todo);

        return FindTodoInfoResponse.of(todo, isLiked);
    }

    @Transactional(readOnly = true)
    public List<FindTodoResponse> findUserTodo(Long userId, YearMonth todoYearMonth) {

        LocalDate startDate = todoYearMonth.atDay(1);
        LocalDate endDate = todoYearMonth.atEndOfMonth();

        Sort sort = sortTodo();

        List<Todo> todos = new ArrayList<>(
                todoRepository.findByWriter_IdAndTodoDateBetweenAndIsPublicTrue(userId, startDate, endDate, sort));

        return todos.stream()
                .map(todo-> {
                    Boolean isLiked = likeFacade.isLiked(todo.getWriter(), todo);
                    return FindTodoResponse.of(todo,isLiked);
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FindTodoResponse> findMyTodo(YearMonth todoYearMonth) {

        User user = userFacade.currentUser();

        LocalDate startDate = todoYearMonth.atDay(1);
        LocalDate endDate = todoYearMonth.atEndOfMonth();

        Sort sort = sortTodo();

        List<Todo> todos = new ArrayList<>(
                todoRepository.findByWriter_IdAndTodoDateBetween(user.getId(), startDate, endDate, sort));

        return todos.stream()
                .map(todo-> {
                    Boolean isLiked = likeFacade.isLiked(todo.getWriter(), todo);
                    return FindTodoResponse.of(todo,isLiked);
                })
                .collect(Collectors.toList());
    }

    private Sort sortTodo(){

        Sort sortTodoDateAsc = Sort.by(Sort.Direction.ASC, "todoDate");
        Sort sortCompletedDateByAsc = Sort.by(Sort.Direction.ASC,"completedDateTime");
        return sortTodoDateAsc.and(sortCompletedDateByAsc);
    }

}