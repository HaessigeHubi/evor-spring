package com.evor.evor.repository;


import com.evor.evor.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/*
Only additional functions for Users needed to be added
 */
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findEventsByUsersId(Long tutorialId);
}