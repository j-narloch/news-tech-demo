package com.fdmgroup.news.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fdmgroup.news.services.LogService;
import com.fdmgroup.news.services.RatingService;
import com.fdmgroup.news.model.Article;
import com.fdmgroup.news.model.Comment;
import com.fdmgroup.news.model.User;
import com.fdmgroup.news.util.FileUploadUtil;
import com.fdmgroup.news.util.Filtering;
import com.fdmgroup.news.services.ArticleService;
import com.fdmgroup.news.services.CommentService;
import com.fdmgroup.news.services.IArticleService;


@Controller
public class ArticleController {
	
	@Autowired
	private IArticleService service;
	
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private LogService login;
	
    @Autowired
    private CommentService commentService;
    
    @Autowired
    private ArticleService articleService;
	
	User user;

	@GetMapping(value = "/articlePage")
	public String goArticlePage(ModelMap model) {
		login.isLoggedIn(model);

		Article article = new Article();
		model.addAttribute("article", article);
		model.addAttribute("filtering", new Filtering());
//		 Article article = articleService.getArticleById(articleId);
//	        List<Comment> comments = commentService.getCommentsByArticle(article);
		return "articlePage";
	}
	
//	@GetMapping(value = "/articlePage")
//	public String goArticlePage(@RequestParam("articleId") Integer articleId, ModelMap model) {
//	    login.isLoggedIn(model);
//
//	    Article article = articleService.findArticleById(articleId);
//	    List<Comment> comments = commentService.getCommentsByArticle(article);
//	    model.addAttribute("article", article);
//	    model.addAttribute("comments", comments);
//	    model.addAttribute("filtering", new Filtering());
//	    return "articlePage";
//	}

	
	@GetMapping(value = "/addArticle")
	public String goAddArticle(ModelMap model) {
		login.isLoggedIn(model);
		return "addArticle";
	}
	
	@PostMapping(value="/addArticle")
	public String createNewArticle( ModelMap model, 
			                        @RequestParam String articleName, 
			                        @RequestParam(required = false) String description, 
			                        @RequestParam String category,
			                        @RequestParam String articleTextOne,
			                        @RequestParam String articleTextTwo,
			                        @RequestParam String articleTextThree,
			                        @RequestParam String articleTextFour,
	                                @RequestParam(value = "image", required = false) MultipartFile[] multipartFiles) throws IOException {
	    Article article = new Article();
	    article.setArticleName(articleName);
	    article.setDescription(description);
	    article.setArticleTextOne(articleTextOne);
	    article.setArticleTextTwo(articleTextTwo);
	    article.setArticleTextThree(articleTextThree);
	    article.setArticleTextFour(articleTextFour);
	    article.setCategory(category);    
		user = login.getLoggedUser();
	    article.setOwner(user);
	    service.createNewArticle(article);
	    for(MultipartFile multipartFile: multipartFiles) {
	    	
		    if (multipartFile != null && !multipartFile.isEmpty()) {
		        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		        System.out.println("Path to file: " + fileName);
		        String uploadDir = "./src/main/webapp/img/" + article.getId();
		        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		        article.setPhotos("/img/"+ article.getId() + "/" + fileName);
		    }
	    }
	    
	    service.createNewArticle(article);
	    populateModel(model);
	    
	    model.addAttribute("article", article);
	    model.addAttribute("articleName", article.getArticleName());
	    model.addAttribute("articleDescription", article.getDescription());
	    
	    model.addAttribute("articleOwner", article.getOwner());
	    
	    model.addAttribute("articleCategory", article.getCategory());
	    model.addAttribute("articleTextOne", article.getArticleTextOne());
	    model.addAttribute("articleTextTwo", article.getArticleTextTwo());
	    model.addAttribute("articleTextThree", article.getArticleTextThree());
	    model.addAttribute("articleTextFour", article.getArticleTextFour());
	    
	    return "redirect:/articlePage/" + article.getId();
	}
	
	
	@GetMapping(value="/articlePage/{id}")
	public String seeDetails(ModelMap model, @PathVariable int id)
	{
		login.isLoggedIn(model);
		Article article = service.findArticleById(id);
		model.addAttribute("article", article);

		model.addAttribute("articleName", article.getArticleName());
		model.addAttribute("articleDescription", article.getDescription());
		model.addAttribute("articleCategory", article.getCategory());
		
	    model.addAttribute("articleOwner", article.getOwner());
		
		model.addAttribute("articleTextOne", article.getArticleTextOne());
		model.addAttribute("articleTextTwo", article.getArticleTextTwo());
		model.addAttribute("articleTextThree", article.getArticleTextThree());
		model.addAttribute("articleTextFour", article.getArticleTextFour());
		if(!article.getPhotos().isEmpty()) {
			model.addAttribute("mainPhotoUrl",article.getPhotos().get(0));
		}else {model.addAttribute("mainPhotoUrl","");}
		model.addAttribute("pictureUrls", article.getPhotos());
			
		model.addAttribute("articleRating", ratingService.getAverageArticleRating(article));
		
		String category = article.getCategory();
		Filtering filtering = new Filtering(category);
		
		List<Article> searchedByCategory = new ArrayList<>(0);
		List<Article> searchedArticles = new ArrayList<>();
		
		if (category != "") {
			searchedByCategory = service.findArticleByCategory(category);
			if (searchedArticles.isEmpty())
				searchedArticles.addAll(searchedByCategory);
		}
		
		if (category != "") {
			searchedArticles.retainAll(searchedByCategory);
		}
		
		searchedArticles.remove(article);
		
		
		model.addAttribute("resultsOfSearchCat", searchedArticles);
		model.addAttribute("filtering", filtering);
		model.addAttribute("category", category);
		
		 Article articleA = articleService.findArticleById(id);
        List<Comment> comments = commentService.getCommentsByArticle(articleA);
	    model.addAttribute("comments", comments);
		
		return "articlePage";
	}

	    @GetMapping("/dropDownFilters")
	    public String dropDownFilters(ModelMap model) {
	    	login.isLoggedIn(model);
	        model.addAttribute("filtering", new Filtering());
	        return "dropDownFilters";
	    }

	    @PostMapping("/filterByIOS")
	    public String filterByIOS(ModelMap model) {
	    	login.isLoggedIn(model);
	        model.addAttribute("filtering", new Filtering("IOS"));
	        return "dropDownFilters";
	    }

	    @PostMapping("/filterByAndroid")
	    public String filterByAndroid(ModelMap model) {
	    	login.isLoggedIn(model);
	        model.addAttribute("filtering", new Filtering("Android"));
	        return "dropDownFilters";
	    }

	    @PostMapping("/filterByIT")
	    public String filterByIT(ModelMap model) {
	    	login.isLoggedIn(model);
	        model.addAttribute("filtering", new Filtering("IT"));
	        return "dropDownFilters";
	    }

	    @PostMapping("/filterByGlobalNews")
	    public String filterByGlobalNews(ModelMap model) {
	    	login.isLoggedIn(model);
	        model.addAttribute("filtering", new Filtering("Global"));
	        return "dropDownFilters";
	    }
	    
	    @PostMapping("/filterByYourArticles")
	    public String filterByYourArticles(ModelMap model) {
	    	login.isLoggedIn(model);
	        model.addAttribute("filtering", new Filtering("Owner"));
	        return "dropDownFilters";
	    }

	
	@PostMapping("/dropDownFilters")
	public String filteringFunction(ModelMap model, HttpServletRequest request, @RequestParam String filter ) {
		login.isLoggedIn(model);
		String category = request.getParameter("category");
		Filtering filtering = new Filtering(category);
		
		List<Article> searchedByCategory = new ArrayList<>(0);
		List<Article> searchedArticles = new ArrayList<>();
		
		if (category != "") {
			searchedByCategory = service.findArticleByCategory(category);
			if (searchedArticles.isEmpty())
				searchedArticles.addAll(searchedByCategory);
		}
		
		if (category != "") {
			searchedArticles.retainAll(searchedByCategory);
		}
		
		
		model.addAttribute("resultsOfSearch", searchedArticles);
		model.addAttribute("filter", filter);
		model.addAttribute("filtering", filtering);
		
		return "dropDownFilters";
	}
	
	private void populateModel(ModelMap model) {
		login.isLoggedIn(model);
		model.addAttribute("articles", service.findAllArticles());
	}

}
