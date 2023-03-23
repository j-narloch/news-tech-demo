package com.fdmgroup.news.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Rating {
	
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  
	  private Integer articleValue;
	  
	public Integer getArticleValue() {
		return articleValue;
	}

	public void setArticleValue(Integer articleValue) {
		this.articleValue = articleValue;
	}
	
	@ManyToOne
    @JoinColumn(name="article_id")
    private Article article;

	@ManyToOne
	  private User user;
	  
	  public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Rating() {};
	  
	public Rating(Long id, User user, Article article, Integer articleValue) {
		super();
		this.id = id;
		this.articleValue = articleValue;
		this.user = user;
		this.article = article;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", articleValue=" + articleValue + ", user=" + user
				+ ", article=" + article + "]";
	}

}
