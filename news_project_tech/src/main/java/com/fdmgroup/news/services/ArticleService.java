package com.fdmgroup.news.services;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.news.model.Article;
import com.fdmgroup.news.model.User;
import com.fdmgroup.news.repository.ArticleRepository;

@Service
public class ArticleService implements IArticleService {
	
	@Autowired
	private ArticleRepository repo;
	
	@Override
	public Article findByArticleName(String articleName) {
		Optional<Article> optArticle = repo.findByArticleName(articleName);
		
		return optArticle.orElse(new Article("default article", "default category"));
	}

	@Override
	public List<Article> findAllArticles() {
		return repo.findAll();
	}

	@Override
	public void createNewArticle(Article article) {
		repo.save(article);
		
	}

	@Override
	public Article findArticleById(int id) {
		Optional<Article> optArticle = repo.findById(id);
		
		return optArticle.orElse(new Article("default article", "default category"));
	}
	
	@Override
	public List<Article> filterArticles(String filter) {
		String[] filtersArray = filter.split(" ");
		List<List<Article>> results = new ArrayList<List<Article>>();
		
		for (String filters : filtersArray) {
			List<Article> filteredByProductName = repo.findByArticleNameIgnoreCaseContaining(filters);
			List<Article> filteredByCatogory = repo.findByCategoryIgnoreCase(filters);
			
			List<Article> filteredArticles = new ArrayList<>();
			Stream.of(filteredByProductName, filteredByCatogory).forEach(filteredArticles::addAll);
			results.add(filteredArticles);
		}
		
		List<Article> finalFilteredArticles = new ArrayList<Article>();
		finalFilteredArticles.addAll(results.get(0));
	    for (ListIterator<List<Article>> iter = results.listIterator(0); iter.hasNext(); ) {
	    	finalFilteredArticles.retainAll(iter.next());
	    }
		
		return finalFilteredArticles;
	}

	@Override
	public List<Article> findArticleByCategory(String filter) {
		return repo.findByCategoryIgnoreCase(filter);
	}
	
	@Override
	public List<Article> findArticleByOwner(User loggedInUser) {
		return repo.findByOwner(loggedInUser);
	}

}
