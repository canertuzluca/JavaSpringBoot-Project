package io.github.canertuzluca.javaspringboot.service;

import io.github.canertuzluca.javaspringboot.model.News;
import io.github.canertuzluca.javaspringboot.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;


    public List<News> getAllNews(){
        List<News> newsList = newsRepository.findAll();
        return newsList;
    }


    public News createNews(News newNews) {
        return newsRepository.save(newNews);
    }


    public void deleteNews(int id) {
        newsRepository.deleteById(id);
    }

    public News getNewsById(int id) {
        return newsRepository.findById(id).orElseThrow(() -> new RuntimeException("News not found"));
    }


    public News updateNewsById(News news_, int id) {
        News news = newsRepository.findById(id).orElseThrow(() -> new RuntimeException("News not be updated"));

        news.setTitle(news_.getTitle());
        news.setContent(news_.getContent());
        news.setAuthor(news_.getAuthor());
        news.setDate(news_.getDate());

        News updateNews = newsRepository.save(news);
        return updateNews;
    }

    // For Date

    public List<News> getNewsByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) +1; // Ay sıfırdan başladığı için +1
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return newsRepository.findAllByDate(year, month, day);
    }


    public List<News> getNewsBetweenDates(Date startDate, Date endDate) {
        return newsRepository.findAllBetweenDates(startDate, endDate);
    }


    // For image

    public News createNewsWithImg(String title, String content, String author, MultipartFile image) throws IOException {
        News news = new News();
        news.setTitle(title);
        news.setContent(content);
        news.setAuthor(author);
        String base64Image = Base64.getEncoder().encodeToString(image.getBytes());
        news.setImage(base64Image);
        return newsRepository.save(news);
    }
}
