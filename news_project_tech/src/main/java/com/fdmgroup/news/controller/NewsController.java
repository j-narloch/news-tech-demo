package com.fdmgroup.news.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.fdmgroup.news.services.ArticleService;
import com.fdmgroup.news.model.Article;
import com.fdmgroup.news.services.LogService;
import com.fdmgroup.news.services.RatingService;

@Controller
public class NewsController {
	
	@Autowired
	private LogService login;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private RatingService ratingService;
	
	@GetMapping(value = "/")
	public String goToIndex(ModelMap model, Principal principal) {
		List<Article> allArticles = articleService.findAllArticles();
		
		Collections.shuffle(allArticles);
	    List<Article> listOfArticles = allArticles.subList(5, 8);
		model.addAttribute("listOfArticles", listOfArticles);
		
        List<Article> filteredArticles = new ArrayList<>();
        if (login.isLoggedIn(model)) {
	        for (Article article : allArticles) {
	            if (ratingService.getAverageArticleRating(article) > 4.0) {
	                filteredArticles.add(article);
	            }
	        }
        }
        if (filteredArticles.isEmpty()) {
            Collections.shuffle(allArticles);
            filteredArticles = allArticles.subList(0, 3);
        }
        model.addAttribute("filteredArticles", filteredArticles);
        
        if (principal != null) {
            model.addAttribute("hotNews", "Top rated news!");
        }
        
		return "index";
	}
	
	@GetMapping("/faq")
	public String faq(ModelMap model) {
		login.isLoggedIn(model);
		return "faq";
	}
	
	@GetMapping("/aboutUs")
	public String aboutUs(ModelMap model) {
		login.isLoggedIn(model);
		return "aboutUs";
	}
	
	@GetMapping("/terms")
	public String terms(ModelMap model) {
		login.isLoggedIn(model);
		return "terms";
	}
	

}
