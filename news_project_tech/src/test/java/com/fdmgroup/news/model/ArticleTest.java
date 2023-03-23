package com.fdmgroup.news.model;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArticleTest {

    @InjectMocks
    private Article article;

    @Test
    public void testSetAndGetId() {
        article.setId(1);
        assertEquals(1, article.getId());
    }

    @Test
    public void testSetAndGetArticleName() {
        String articleName = "Breaking News";
        article.setArticleName(articleName);
        assertEquals(articleName, article.getArticleName());
    }

    @Test
    public void testSetAndGetDescription() {
        String description = "This is a breaking news story.";
        article.setDescription(description);
        assertEquals(description, article.getDescription());
    }

    @Test
    public void testSetAndGetCategory() {
        String category = "Politics";
        article.setCategory(category);
        assertEquals(category, article.getCategory());
    }

    @Test
    public void testSetAndGetArticleTextOne() {
        String articleTextOne = "This is the first paragraph of the article.";
        article.setArticleTextOne(articleTextOne);
        assertEquals(articleTextOne, article.getArticleTextOne());
    }

    @Test
    public void testSetAndGetArticleTextTwo() {
        String articleTextTwo = "This is the second paragraph of the article.";
        article.setArticleTextTwo(articleTextTwo);
        assertEquals(articleTextTwo, article.getArticleTextTwo());
    }

    @Test
    public void testSetAndGetArticleTextThree() {
        String articleTextThree = "This is the third paragraph of the article.";
        article.setArticleTextThree(articleTextThree);
        assertEquals(articleTextThree, article.getArticleTextThree());
    }

    @Test
    public void testSetAndGetArticleTextFour() {
        String articleTextFour = "This is the fourth paragraph of the article.";
        article.setArticleTextFour(articleTextFour);
        assertEquals(articleTextFour, article.getArticleTextFour());
    }

    @Test
    public void testSetAndGetOwner() {
        User owner = new User();
        article.setOwner(owner);
        assertNotNull(article.getOwner());
    }

    @Test
    public void testSetAndGetRating() {
        Rating rating1 = new Rating();
        Rating rating2 = new Rating();
        article.setRating(Arrays.asList(rating1, rating2));
        assertEquals(Arrays.asList(rating1, rating2), article.getRating());
    }

    @Test
    public void testAddRating() {
        Rating rating = new Rating();
        article.addRating(rating);
        assertEquals(1, article.getRating().size());
    }
    
    @Test
    void testGetAverageRating() {
        Article article = new Article("Article 1", "News");
        
        Rating rating1 = new Rating();
        rating1.setArticleValue(3);
        article.addRating(rating1);

        Rating rating2 = new Rating();
        rating2.setArticleValue(5);
        article.addRating(rating2);

        assertEquals(4.0, article.getAverageRating());
    }
    
    @Test
    void testEqualsEqualObjects() {
        Article otherArticle = new Article();
        otherArticle.setArticleName(article.getArticleName());
        otherArticle.setArticleTextFour(article.getArticleTextFour());
        otherArticle.setArticleTextOne(article.getArticleTextOne());
        otherArticle.setArticleTextThree(article.getArticleTextThree());
        otherArticle.setArticleTextTwo(article.getArticleTextTwo());
        otherArticle.setCategory(article.getCategory());
        otherArticle.setDescription(article.getDescription());
        otherArticle.setId(article.getId());
        otherArticle.setOwner(article.getOwner());
        otherArticle.setRating(article.getRating());

        assertTrue(article.equals(otherArticle));
    }
    
    @Test
    void testEqualsSameObjects() {
        assertTrue(article.equals(article));
    }

    @Test
    void testEqualsDifferentClasses() {
        assertFalse(article.equals(new Object()));
    }

}


