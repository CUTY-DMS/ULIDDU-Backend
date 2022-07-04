package com.project.todolist.entity.todo;

import com.project.todolist.entity.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User writer;

    private String title;

    private String content;

    private LocalDateTime createdDate;

    @Setter
    private Boolean isCompleted;

    @OneToMany(mappedBy = "todo")
    private List<Like> likes = new ArrayList<>();

    public void modifyTitleAndContent(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void addLike(User user) {

        Like like = Like.builder()
                .user(user)
                .todo(this)
                .build();

        likes.add(like);
    }
}