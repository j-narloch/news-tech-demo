package com.fdmgroup.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fdmgroup.news.model.Article;
import com.fdmgroup.news.model.Rating;
import com.fdmgroup.news.services.ArticleService;
import com.fdmgroup.news.services.LogService;
import com.fdmgroup.news.services.RatingService;


@Controller
public class RatingController {
	
  @Autowired
  private RatingService ratingService;
  
  @Autowired
  private ArticleService articleService;
  
  @Autowired
  private LogService login;
  
  public RatingController(RatingService ratingService) {
    this.ratingService = ratingService;
  }
  
  @GetMapping("/rate")
  public String save(ModelMap model) {
	login.isLoggedIn(model);
	return "redirect:/ArticlePage";
  }
  
  @PostMapping ("/rate/{articleId}")
  public String save(ModelMap model, @ModelAttribute Rating rating, @PathVariable int articleId) {
	Article article = articleService.findArticleById(articleId);  
	rating.setArticle(article);
    ratingService.create(rating);
    article.addRating(rating);
    articleService.createNewArticle(article);
    return "redirect:/articlePage/" + article.getId();
  }


}