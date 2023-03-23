package com.fdmgroup.news.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ModelMap;

import com.fdmgroup.news.controller.NewsController;
import com.fdmgroup.news.model.Article;
import com.fdmgroup.news.services.ArticleService;
import com.fdmgroup.news.services.LogService;
import com.fdmgroup.news.services.RatingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsControllerTest {

    @Mock
    private LogService logServiceMock;

    @Mock
    private ArticleService articleServiceMock;

    @Mock
    private RatingService ratingServiceMock;

    @Autowired
    private NewsController newsController;

    @Test
    public void testGoToIndex() {
        List<Article> allArticles = Arrays.asList(new Article(), new Article(), new Article(), new Article(), new Article());
        List<Article> filteredArticles = Arrays.asList(new Article(), new Article(), new Article());
        List<Article> listOfArticles = Arrays.asList(new Article(), new Article(), new Article());

        when(articleServiceMock.findAllArticles()).thenReturn(allArticles);
        when(logServiceMock.isLoggedIn(Mockito.any(ModelMap.class))).thenReturn(true);
        when(ratingServiceMock.getAverageArticleRating(Mockito.any(Article.class))).thenReturn(4.5);
        when(articleServiceMock.findAllArticles()).thenReturn(allArticles);
        when(articleServiceMock.findAllArticles()).thenReturn(allArticles);
        when(articleServiceMock.findAllArticles()).thenReturn(allArticles);
        when(articleServiceMock.findAllArticles()).thenReturn(allArticles);

        ModelMap model = new ModelMap();
        String viewName = newsController.goToIndex(model, null);

        // Verify the results
        assertEquals("index", viewName);
//        assertEquals(listOfArticles, model.get("listOfArticles"));
//        assertEquals(filteredArticles, model.get("filteredArticles"));
    }

    @Test
    public void testFaq() {
        when(logServiceMock.isLoggedIn(Mockito.any(ModelMap.class))).thenReturn(true);

        ModelMap model = new ModelMap();
        String viewName = newsController.faq(model);

        assertEquals("faq", viewName);
    }

    @Test
    public void testAboutUs() {
        when(logServiceMock.isLoggedIn(Mockito.any(ModelMap.class))).thenReturn(true);

        ModelMap model = new ModelMap();
        String viewName = newsController.aboutUs(model);

        assertEquals("aboutUs", viewName);
    }

    @Test
    public void testTerms() {
        when(logServiceMock.isLoggedIn(Mockito.any(ModelMap.class))).thenReturn(true);

        ModelMap model = new ModelMap();
        String viewName = newsController.terms(model);

        assertEquals("terms", viewName);
    }
}



