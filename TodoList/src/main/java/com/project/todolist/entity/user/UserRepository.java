package com.project.todolist.entity.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findById(Long id);

    Optional<User> findByUserId(String userId);
}