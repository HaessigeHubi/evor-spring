package com.evor.evor.seeder;

import com.evor.evor.entity.Event;
import com.evor.evor.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EventDataLoader implements CommandLineRunner {
    @Autowired
    EventRepository eventRepository;
    @Override
    public void run(String... args) throws Exception {
        loadEventData();
    }
    private void loadEventData(){
        if (eventRepository.count() == 0){
            Event event1 = new Event("Konzert1","test",47.376888, 8.541694,"Bahnhofstrasse 84\nZürich 8312", "lol@gmail.com");
            Event event2 = new Event("Konzert2","test",47.377888, 8.541694,"Bahnhofstrasse 84\nZürich 8712", "lol@gmail.com");
            Event event3 = new Event("Konzert3","test",47.378888, 8.541694,"Bahnhofstrasse 84\nZürich 83312", "ojhw18@gmail.com");
            eventRepository.save(event1);
            eventRepository.save(event2);
            eventRepository.save(event3);
        }
    }
}
