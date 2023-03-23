package com.fdmgroup.news.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.news.model.Article;
import com.fdmgroup.news.model.Comment;
import com.fdmgroup.news.model.User;
import com.fdmgroup.news.services.ArticleService;
import com.fdmgroup.news.services.CommentService;
import com.fdmgroup.news.services.UserService;

@Controller
public class CommentControler {
	
	
	    @Autowired
	    private CommentService commentService;
	    
	    @Autowired
	    private UserService userService;
	    
	    @Autowired
	    
	    private ArticleService articleService;

	    @PostMapping("/articlePage/{articleId}")
	    public String addComment(@ModelAttribute("comment") Comment comment, Principal principal, @RequestParam("articleId") int articleId, Model model) {
	        User user = userService.findByUsername(principal.getName());
	        Article article = articleService.findArticleById(articleId);
	        comment.setUser(user);
	        comment.setArticle(article);
	        commentService.addComment(comment);
	        model.addAttribute("article", article);
	        model.addAttribute("comments", article.getComments());
	        return "redirect:/articlePage/" + article.getId();
	        
	    }


//	    @GetMapping("/articlePage")
//	    public ResponseEntity<List<Comment>> getCommentsByArticle(@RequestParam Long articleId) {
//	        Article article = articleService.getArticleById(articleId);
//	        List<Comment> comments = commentService.getCommentsByArticle(article);
//	        return ResponseEntity.ok(comments);
//	    }
	}


