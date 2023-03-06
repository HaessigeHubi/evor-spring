package com.evor.evor.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.evor.evor.entity.Event;
import com.evor.evor.entity.User;
import com.evor.evor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class EventController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<Event>();
        eventRepository.findAll().forEach(events::add);
        return events;
    }

    @GetMapping("/users/{userId}/events")
    public List<Event> getAllEventsByUserId(@PathVariable(value = "userId") Long userId) {

        List<Event> events = eventRepository.findEventsByUsersId(userId);
        return events;
    }

    @GetMapping("/events/{id}")
    public Optional<Event> getEventsById(@PathVariable(value = "id") Long id) {
        return eventRepository.findById(id);
    }

    @GetMapping("/events/{eventId}/users")
    public List<User> getAllUsersByEventId(@PathVariable(value = "eventId") Long eventId) {
        List<User> users = userRepository.findUsersByEventsId(eventId);
        return users;
    }
    //Adding data to many to many Relationship between users and event
    @PostMapping("/users/{userId}/events")
    public Event addEvent(@PathVariable(value = "userId") Long userId, @RequestBody Event eventRequest) {
        Event event = userRepository.findById(userId).map(user -> {
            long eventId = eventRequest.getId();

            // event is existed
            if (eventId != 0L) {
                Event _event = eventRepository.findById(eventId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Event with id = " + eventId));
                user.addEvent(_event);
                userRepository.save(user);
                return _event;
            }

            // add and create new Event
            user.addEvent(eventRequest);
            return eventRepository.save(eventRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + userId));

        return event;
    }

    @PutMapping("/events/{id}")
    public Event updateEvent(@PathVariable("id") long id, @RequestBody Event eventRequest) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EventId " + id + "not found"));

        event.setName(eventRequest.getName());

        return eventRepository.save(event);
    }
    //Deleting data to many to many Relationship between users and event
    @DeleteMapping("/users/{userId}/events/{eventId}")
    public HttpStatus deleteEventFromUser(@PathVariable(value = "userId") Long userId, @PathVariable(value = "eventId") Long eventId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + userId));

        user.removeEvent(eventId);
        userRepository.save(user);

        return HttpStatus.NO_CONTENT;
    }

    @DeleteMapping("/events/{id}")
    public HttpStatus deleteEvent(@PathVariable("id") long id) {
        eventRepository.deleteById(id);

        return HttpStatus.NO_CONTENT;
    }

    //Adding data to many to many Relationship between users and event
    @PostMapping("/events/{eventId}/users")
    public User addUsers(@PathVariable(value = "eventId") Long eventId, @RequestBody User userRequest) {
        User user = eventRepository.findById(eventId).map(event -> {
            long userId = userRequest.getId();

            // event is existed
            if (userId != 0L) {
                User _user = userRepository.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Event with id = " + userId));
                event.addUser(_user);
                eventRepository.save(event);
                return _user;
            }

            // add and create new Event
            event.addUser(userRequest);
            return userRepository.save(userRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + eventId));

        return user;
    }

    @PostMapping("/events")
    public Event createEvent(@RequestBody Event event) {
        return eventRepository.save(new Event(event.getEventname(), event.getDescription(), event.getLat(), event.getLng(), event.getAddress(), event.getOwner(),event.getStartDate(),event.getEndDate(), event.getTag(), event.getMaxParticipation(), event.getAgeRestriction()));
    }


}