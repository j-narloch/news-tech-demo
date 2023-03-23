package com.fdmgroup.news.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fdmgroup.news.model.Article;
import com.fdmgroup.news.model.User;
import com.fdmgroup.news.repository.ArticleRepository;

@SpringBootTest
public class ArticleServiceTest {
 
    @Autowired
    private ArticleService articleService;

    @MockBean
    private ArticleRepository articleRepository;

    @Test
    public void testFindByArticleName() {
        String articleName = "Test Article";
        Article article = new Article(articleName, "Test Category");
        when(articleRepository.findByArticleName(articleName)).thenReturn(Optional.of(article));

        Article result = articleService.findByArticleName(articleName);

        assertThat(result).isEqualTo(article);
    }

    @Test
    public void testFindAllArticles() {
        List<Article> articles = Arrays.asList(new Article("Test Article 1", "Test Category 1"), new Article("Test Article 2", "Test Category 2"));
        when(articleRepository.findAll()).thenReturn(articles);

        List<Article> result = articleService.findAllArticles();

        assertThat(result).isEqualTo(articles);
    }

    @Test
    public void testCreateNewArticle() {
        Article article = new Article("Test Article", "Test Category");

        articleService.createNewArticle(article);

        verify(articleRepository, times(1)).save(article);
    }

    @Test
    public void testFindArticleById() {
        int articleId = 1;
        Article article = new Article("Test Article", "Test Category");
        article.setId(articleId);
        when(articleRepository.findById(articleId)).thenReturn(Optional.of(article));

        Article result = articleService.findArticleById(articleId);

        assertThat(result).isEqualTo(article);
    }

    @Test
    public void testFilterArticles() {
        List<Article> articles1 = Arrays.asList(new Article("Test Article 1", "Test Category"), new Article("Test Article 2", "Test Category 2"));
        List<Article> articles2 = Arrays.asList(new Article("Test Article 3", "Test Category"), new Article("Test Article 4", "Test Category 2"));
        List<Article> articles3 = Arrays.asList(new Article("Test Article 5", "Test Category"), new Article("Test Article 6", "Test Category 2"));

        when(articleRepository.findByArticleNameIgnoreCaseContaining("Test")).thenReturn(articles1);
        when(articleRepository.findByCategoryIgnoreCase("Category")).thenReturn(articles2);
        when(articleRepository.findByArticleNameIgnoreCaseContaining("Article")).thenReturn(articles3);

        List<Article> result = articleService.filterArticles("Test Category Article");
    }

    @Test
    public void testFindArticleByCategory() {
        String category = "Test Category";
        List<Article> articles = Arrays.asList(new Article("Test Article 1", category), new Article("Test Article 2", category));
        when(articleRepository.findByCategoryIgnoreCase(category)).thenReturn(articles);

        List<Article> result = articleService.findArticleByCategory(category);

        assertThat(result).isEqualTo(articles);
    }
    
    @Test
    public void testFindArticleByOwner() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");
 
        Article article1 = new Article("Article 1", "Category 1");
        article1.setId(1);
        article1.setOwner(user);
 
        Article article2 = new Article("Article 2", "Category 2");
        article2.setId(2);
        article2.setOwner(user);
 
        List<Article> articles = new ArrayList<>();
        articles.add(article1);
        articles.add(article2);
 
        when(articleRepository.findByOwner(user)).thenReturn(articles);
 
        List<Article> result = articleService.findArticleByOwner(user);
 
        assertEquals(2, result.size());
        assertEquals("Article 1", result.get(0).getArticleName());
        assertEquals("Category 1", result.get(0).getCategory());
        assertEquals("Article 2", result.get(1).getArticleName());
        assertEquals("Category 2", result.get(1).getCategory());
    }

}
