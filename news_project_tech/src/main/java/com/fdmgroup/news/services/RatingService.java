package com.fdmgroup.news.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.news.model.Article;
import com.fdmgroup.news.model.Rating;
import com.fdmgroup.news.repository.RatingRepository;


@Service
public class RatingService implements IRatingService {
	
	  @Autowired	
	  private RatingRepository ratingRepository;
	  
	  @Override
	  public void create(Rating rating) {
		 ratingRepository.save(rating);
	  }
	  
	  public Double getAverageArticleRating(Article article) {
		    List<Rating> ratings = ratingRepository.findByArticle(article);
		    return ratings.stream()
		        .mapToInt(Rating::getArticleValue)
		        .average()
		        .orElse(0.0);
	  }

}

