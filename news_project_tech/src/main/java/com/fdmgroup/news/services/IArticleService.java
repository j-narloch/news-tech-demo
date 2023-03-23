package com.fdmgroup.news.services;

import java.util.List;

import com.fdmgroup.news.model.Article;
import com.fdmgroup.news.model.User;

public interface IArticleService {
	Article findByArticleName(String articleName);
	List<Article> findAllArticles();
	void createNewArticle(Article article);
	Article findArticleById(int id); //throws ProductNotFoundException;
	List<Article> filterArticles(String filter);
	List<Article> findArticleByCategory(String filter);
	List<Article> findArticleByOwner(User loggedInUser);
	
}
