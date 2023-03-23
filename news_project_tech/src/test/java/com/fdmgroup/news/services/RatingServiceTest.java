package com.fdmgroup.news.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.fdmgroup.news.model.Article;
import com.fdmgroup.news.model.Rating;
import com.fdmgroup.news.repository.RatingRepository;
import com.fdmgroup.news.services.RatingService;

@SpringBootTest
public class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingService ratingService;

    @Test
    public void testCreateRating() {
        Rating rating = new Rating();
        ratingService.create(rating);
        assertEquals(rating, ratingRepository.save(rating));
    }

    @Test
    public void testGetAverageArticleRating() {
        Article article = new Article();
        Rating rating1 = new Rating();
        rating1.setArticleValue(4);
        Rating rating2 = new Rating();
        rating2.setArticleValue(5);
        List<Rating> ratings = new ArrayList<>();
        ratings.add(rating1);
        ratings.add(rating2);

        when(ratingRepository.findByArticle(article)).thenReturn(ratings);
        double expectedAverage = (4 + 5) / 2.0;
        double actualAverage = ratingService.getAverageArticleRating(article);
        assertEquals(expectedAverage, actualAverage, 0.001);
    }
}


