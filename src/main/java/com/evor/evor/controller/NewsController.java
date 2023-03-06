package com.evor.evor.controller;

import com.evor.evor.entity.News;
import com.evor.evor.repository.NewsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
NOT in use, preperation for furure
 */
@RestController
public class NewsController {
    private NewsRepository newsRepository;

    public NewsController(NewsRepository newsRepository) { this.newsRepository= newsRepository;}

    @GetMapping("/news")
    List<News> all(){ return newsRepository.findAll();}

    @GetMapping("/news/{id}")
    News one(@PathVariable Long id) throws Exception {
        return newsRepository.findById(id)
                .orElseThrow(() -> new Exception());
    }

    @PostMapping("/news")
    News newEvent(@RequestBody News newEvent) {return newsRepository.save(newEvent);}

    @DeleteMapping("news")
    void deleteNews(@RequestBody News deleteNews) {newsRepository.delete(deleteNews);}
}
