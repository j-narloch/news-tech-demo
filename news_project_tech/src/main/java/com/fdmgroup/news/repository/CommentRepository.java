package com.fdmgroup.news.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.news.model.Article;
import com.fdmgroup.news.model.Comment;

@Repository
	public interface CommentRepository extends JpaRepository<Comment, Long> {
	    List<Comment> findByArticle(Article article);
	}


