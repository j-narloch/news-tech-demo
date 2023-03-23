package com.fdmgroup.news.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ModelMap;

import com.fdmgroup.news.controller.RatingController;
import com.fdmgroup.news.model.Article;
import com.fdmgroup.news.model.Rating;
import com.fdmgroup.news.services.ArticleService;
import com.fdmgroup.news.services.LogService;
import com.fdmgroup.news.services.RatingService;

@SpringBootTest
public class RatingControllerTest {
	
	@InjectMocks
	private RatingController ratingController;
	
	@Mock
	private RatingService ratingService;
	
	@Mock
	private ArticleService articleService;
	
	@Mock
	private LogService logService;
	
	@Mock
	private ModelMap modelMap;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
  @Test
  public void testGoToRate() {
    String result = ratingController.save(modelMap);
    
    assertEquals("redirect:/ArticlePage", result);
  }
	
	@Test
	public void testSave() {
	  Rating rating = new Rating();
	  Article article = new Article();
	  article.setId(1);
	  
	  given(articleService.findArticleById(1)).willReturn(article);
	  
	  String result = ratingController.save(modelMap, rating, 1);
	  
	  verify(articleService, times(1)).createNewArticle(article);
	  verify(ratingService, times(1)).create(rating);
	  assertEquals("redirect:/articlePage/1", result);
	}

}
