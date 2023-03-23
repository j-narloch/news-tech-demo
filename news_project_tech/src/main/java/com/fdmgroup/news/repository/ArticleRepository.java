package com.fdmgroup.news.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.news.model.Article;
import com.fdmgroup.news.model.User;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
	Optional<Article> findByArticleName(String articleName);
	List<Article> findByArticleNameIgnoreCaseContaining(String filter);
	List<Article> findByCategoryIgnoreCase(String filter);
	List<Article> findByOwner(User loggedInUser);
	Article findArticleById(Integer id);
	
}
