package com.evor.evor.repository;

import java.util.List;

import com.evor.evor.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


/*
Only additional functions for Users needed to be added
 */
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByPublished(boolean published);

    List<User> findByTitleContaining(String title);

    List<User> findUsersByEventsId(Long tagId);
    List<User> findByEmail(String email);
}