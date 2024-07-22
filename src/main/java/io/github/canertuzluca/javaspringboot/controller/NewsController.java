package io.github.canertuzluca.javaspringboot.controller;

import io.github.canertuzluca.javaspringboot.model.News;
import io.github.canertuzluca.javaspringboot.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/news")
@AllArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public ResponseEntity<List<News>> getAllNews(){
        return new ResponseEntity<>(newsService.getAllNews(), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> getNews(@PathVariable int id){
        return new ResponseEntity<>(getNewsById(id), OK);
    }

    @PostMapping
    public ResponseEntity<News> createNews(@RequestBody News newNews){
        return new ResponseEntity<>(newsService.createNews(newNews), CREATED);
    }

    @PutMapping("/{id}")

    public ResponseEntity<News> updateNews(@PathVariable int id, @RequestBody News newNews){
        News response= newsService.updateNewsById(newNews, id);
        return new ResponseEntity<>(response, OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable int id){
        newsService.deleteNews(id);
        return new ResponseEntity<>(OK);
    }

    private News getNewsById(int id){
        return newsService.getNewsById(id);
    }

    // Date

    @GetMapping("/date")
    public List<News> getNewsByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date)
    {
        return newsService.getNewsByDate(date);
    }

    @GetMapping("/dates")
    public List<News> getNewsArticlesBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return newsService.getNewsBetweenDates(startDate, endDate);
    }


    // image

    @PostMapping("/image")
    public ResponseEntity<News> createNewsWithImage(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("author") String author,
            @RequestParam("image") MultipartFile image) throws IOException {
        News news = newsService.createNewsWithImg(title, content, author, image);
        return new ResponseEntity<>(news, HttpStatus.CREATED);
    }





}
