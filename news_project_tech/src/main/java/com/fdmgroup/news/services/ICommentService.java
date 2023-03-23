package com.fdmgroup.news.services;

import java.util.List;

import com.fdmgroup.news.model.Article;
import com.fdmgroup.news.model.Comment;

public interface ICommentService {
	
    Comment addComment(Comment comment);
    List<Comment> getCommentsByArticle(Article article);
}



