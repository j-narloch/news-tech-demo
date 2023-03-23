package com.fdmgroup.news.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.news.model.Article;
import com.fdmgroup.news.model.Comment;
import com.fdmgroup.news.repository.CommentRepository;

@Service
	public class CommentService implements ICommentService {
	    @Autowired
	    private CommentRepository commentRepository;

	    @Override
	    public Comment addComment(Comment comment) {
	        return commentRepository.save(comment);
	    }

	    @Override
	    public List<Comment> getCommentsByArticle(Article article) {
	        return commentRepository.findByArticle(article);
	    }
	    
}


