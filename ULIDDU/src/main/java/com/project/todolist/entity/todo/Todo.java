package com.project.todolist.entity.todo;

import com.project.todolist.entity.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Date;
import java.time.LocalDate;
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

    private LocalDate todoDate;

    @Nullable
    private LocalDateTime completedDateTime;

    private Boolean isPublic;

    private Boolean isCompleted;

    @OneToMany(mappedBy = "todo")
    private List<Like> likes;

    public void modifyTitleAndContent(String title, String content, Boolean isPublic) {
        this.title = title;
        this.content = content;
        this.isPublic = isPublic;
    }

    public void setIsCompleted() {
        this.completedDateTime = LocalDateTime.now();
        this.isCompleted = true;
    }

    public void setIsNotCompleted() {
        this.completedDateTime = null;
        this.isCompleted = false;
    }
}