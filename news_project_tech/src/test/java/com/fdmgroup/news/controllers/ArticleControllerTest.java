package com.fdmgroup.news.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;

import com.fdmgroup.news.controller.ArticleController;
import com.fdmgroup.news.controller.LoginAndRegisterController;
import com.fdmgroup.news.model.Article;
import com.fdmgroup.news.services.ArticleService;
import com.fdmgroup.news.services.IArticleService;
import com.fdmgroup.news.services.LogService;
import com.fdmgroup.news.services.RatingService;
import com.fdmgroup.news.util.Filtering;

@SpringBootTest
public class ArticleControllerTest {
	
	private MockMvc mockMvc;
	
    @Mock
    private ModelMap model;

    @Mock
    private IArticleService articleService;

    @Mock
    private RatingService ratingService;

    @Mock
    private LogService logService;
    
    @Mock
    private ArticleService service;

    @InjectMocks
    private ArticleController articleController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
    }
    
    @Test
    public void testGoArticlePage() {
        String view = articleController.goArticlePage(model);
        assertEquals("articlePage", view);

        Article article = new Article();
        article.setArticleName("article name");

        Filtering filtering = new Filtering();

        model.addAttribute("article", article);
        model.addAttribute("filtering", filtering);
    }
    
    @Test
    public void testGoAddArticle() {
        when(logService.isLoggedIn(model)).thenReturn(true);
        
        String result = articleController.goAddArticle(model);
        assertEquals("addArticle", result);
        
        verify(logService).isLoggedIn(model);
    }
    
    @Test
    public void createNewArticleTest() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();

    MockMultipartFile image = new MockMultipartFile("image", "test-image.jpg", "image/jpeg", "test image".getBytes());

    mockMvc.perform(MockMvcRequestBuilders.multipart("/addArticle")
            .file("image", image.getBytes())
            .param("articleName", "Test Article")
            .param("description", "This is a test article")
            .param("category", "Technology")
            .param("articleTextOne", "This is a test article.")
            .param("articleTextTwo", "This is a test article.")
            .param("articleTextThree", "This is a test article.")
            .param("articleTextFour", "This is a test article."))
            .andExpect(MockMvcResultMatchers.model().attributeExists("article"))
            .andExpect(MockMvcResultMatchers.model().attribute("articleName", "Test Article"))
            .andExpect(MockMvcResultMatchers.model().attribute("articleDescription", "This is a test article"))
            .andExpect(MockMvcResultMatchers.model().attribute("articleCategory", "Technology"))
            .andExpect(MockMvcResultMatchers.model().attribute("articleTextOne", "This is a test article."))
            .andExpect(MockMvcResultMatchers.model().attribute("articleTextTwo", "This is a test article."))
            .andExpect(MockMvcResultMatchers.model().attribute("articleTextThree", "This is a test article."))
            .andExpect(MockMvcResultMatchers.model().attribute("articleTextFour", "This is a test article."));
    }
    
  @Test
  public void testSeeDetails() throws Exception {
      Article article = new Article();
      article.setId(22);
      article.setArticleName("Test Article");
//      when(articleService.findArticleById(22)).thenReturn(article);
//
//      mockMvc.perform(get("/articlePage/22"))
//             .andExpect(status().isOk())
//             .andExpect(view().name("articlePage"))
//             .andExpect(model().attribute("article", article))
//             .andExpect(model().attribute("articleName", "Test Article"));
  }
  
  @Test
  public void testDropDownFilters() {
      ModelMap model = new ModelMap();
      String viewName = articleController.dropDownFilters(model);

      assertEquals("dropDownFilters", viewName);
      Filtering filtering = (Filtering) model.get("filtering");
      assertNotNull(filtering);
  }
  
  @Test
  public void testFilterByIOS() {
      when(logService.isLoggedIn(model)).thenReturn(true);

      articleController.filterByIOS(model);
//      Filtering expectedFiltering = new Filtering("Android");
//      Filtering actualFiltering = (Filtering) model.get("filtering");

      verify(logService).isLoggedIn(model);
//      assertEquals(expectedFiltering, actualFiltering);
      String result = articleController.filterByIOS(model);

      assertEquals("dropDownFilters", result);
  }
  
  @Test
  public void testFilterByAndroid() {
      when(logService.isLoggedIn(model)).thenReturn(true);

      articleController.filterByAndroid(model);
//      Filtering expectedFiltering = new Filtering("Android");
//      Filtering actualFiltering = (Filtering) model.get("filtering");

      verify(logService).isLoggedIn(model);
//      assertEquals(expectedFiltering, actualFiltering);
      String result = articleController.filterByAndroid(model);

      assertEquals("dropDownFilters", result);
  }
  
  @Test
  public void testFilterByIT() {
      when(logService.isLoggedIn(model)).thenReturn(true);

      articleController.filterByIT(model);
//      Filtering expectedFiltering = new Filtering("Android");
//      Filtering actualFiltering = (Filtering) model.get("filtering");

      verify(logService).isLoggedIn(model);
//      assertEquals(expectedFiltering, actualFiltering);
      String result = articleController.filterByIT(model);

      assertEquals("dropDownFilters", result);
  }
  
  @Test
  public void testFilterByGlobalNews() {
      when(logService.isLoggedIn(model)).thenReturn(true);

      articleController.filterByGlobalNews(model);
//      Filtering expectedFiltering = new Filtering("Android");
//      Filtering actualFiltering = (Filtering) model.get("filtering");

      verify(logService).isLoggedIn(model);
//      assertEquals(expectedFiltering, actualFiltering);
      String result = articleController.filterByGlobalNews(model);

      assertEquals("dropDownFilters", result);
  }
  
  @Test
  public void testFilteringFunction() throws Exception {
      // create some dummy articles
      Article article1 = new Article("Test article 1", "Test content 1");
      Article article2 = new Article("Test article 2", "Test content 2");
      Article article3 = new Article("Test article 3", "Test content 3");
      List<Article> articles = new ArrayList<>();
      articles.add(article1);
      articles.add(article2);
      articles.add(article3);

      // mock the article service to return the dummy articles
      when(articleService.findArticleByCategory(anyString())).thenReturn(articles);

      // make the request to the controller
      mockMvc.perform(post("/dropDownFilters")
              .param("category", "category1")
              .param("filter", "someFilter"))
              .andExpect(status().isOk())
              .andExpect(model().attributeExists("resultsOfSearch"))
              .andExpect(model().attribute("resultsOfSearch", articles))
              .andExpect(model().attribute("filter", "someFilter"))
              .andExpect(model().attribute("filtering", articles));

      // verify that the article service was called with the correct category
      verify(articleService, times(1)).findArticleByCategory("category1");

      // verify that the login service was called to check if user is logged in
      verify(logService, times(1)).isLoggedIn(any(ModelMap.class));
  }

}
