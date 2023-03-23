package com.fdmgroup.news.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RatingTest {
	
	private Rating rating;
	private User user;
	private Article article;
	
	@BeforeEach
	public void setUp() {
		rating = new Rating();
		user = new User("Test User");
		article = new Article("Test Article", "Test category");
	}
	
	@Test
	public void testGetSetId() {
		Long id = 123L;
		rating.setId(id);
		assertEquals(id, rating.getId());
	}
	
	@Test
	public void testGetSetArticleValue() {
		Integer articleValue = 3;
		rating.setArticleValue(articleValue);
		assertEquals(articleValue, rating.getArticleValue());
	}
		
	@Test
	public void testGetSetArticle() {
		rating.setArticle(article);
		assertEquals(article, rating.getArticle());
	}
	
	@Test
	public void testConstructor() {
		Rating newRating = new Rating(123L, user, article, 4);
		assertEquals(123L, newRating.getId());
		assertEquals(article, newRating.getArticle());
		assertEquals(4, newRating.getArticleValue());
	}
	
	@Test
	public void testToString() {
		rating.setId(123L);
		rating.setArticle(article);
		rating.setArticleValue(2);
		assertNotNull(rating.toString());
	}

}
