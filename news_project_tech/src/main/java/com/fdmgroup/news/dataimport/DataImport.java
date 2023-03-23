package com.fdmgroup.news.dataimport;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import com.fdmgroup.news.model.Article;
import com.fdmgroup.news.model.User;
import com.fdmgroup.news.repository.ArticleRepository;
import com.fdmgroup.news.repository.UserRepository;

@Component
public class DataImport implements ApplicationRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	@Lazy
	private PasswordEncoder encoder;

	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (!userRepository.findByUsername("admin").isPresent()) {

			//Dummy users
			User admin = new User("admin", encoder.encode("123"), "admin@admin.pl", "John", "Doe", "123456789");
			userRepository.save(admin);
			
			User user1 = new User("asia", encoder.encode("123"), "joanna.narloch@example.com", "Joanna", "Narloch", "123456789");
			userRepository.save(user1);
			
			User user2 = new User("anna", encoder.encode("123"), "anna.peretiatko@example.com", "Anna", "Peretiatko", "987654321");
			userRepository.save(user2);
			
			//Add dummy articles
			List<Article> articles = new ArrayList<>();

			String[] names = {"The Future of Smartphone Technology", "An Introduction to Artificial Intelligence", "Using Technology to Make Your Life Easier", "The Benefits of Keeping Up With the Latest Tech",
							  "Tech Trends: What's Hot and What's Not", "The Impact of Technology on Our Lives", "Protecting Your Data with the Latest Security Technologies", "The Latest Developments in Artificial Intelligence",
							  "Understanding the Benefits of Cloud Computing", "What Is the Future of Technology?","The Pros and Cons of the Internet of Things","The Benefits of Keeping Up With the Latest Tech"};
			String[] descriptions = {"A look at the smartphone and its future.", "This article is an introduction to artificial intelligence and its application in many areas.", 
									 "Technology is changing the way we live.", "Discover the latest technology news and tips.", 
									 "A monthly technology newsletter with the latest updates to tech gadgets, apps, and software. ", "Technology can impact the way we live, work, and play.", 
									 "Here, you'll learn how to protect your data from the lowest to the highest level.", "Learn more about the latest developments in artificial intelligence and how AI is transforming industries.", 
									 "Learn about the benefits of cloud computing and how to get started with it.", "The future of technology is filled with potential. What will it be like?",
									 "Internet of Things: Pros and Cons of smart devices.","Keeping up with the latest technology is a must nowadays. Here's you'll learn how to be up to date."};
			String[] categories = {"IOS", "IOS", "IOS", "Android", "Android", "Android", "IT", "IT", "IT", "Global", "Global", "Global"};
			String[] paragraphs = {"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis,",
					               "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis,",
					               "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis,",
					               "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis,",
					               "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis,",
					               "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis,",
					               "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis,",
					               "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis,",
					               "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis,",
					               "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis,",
					               "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis,",
					               "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis,"};
			
			User[] owners = {user1, user1, user2, user2, user1, user2, user1, user2, user2, user2, user1, user1};

			List<List<String>> listOfLists = new ArrayList<>();
			for (int i = 0; i < 12; i++) {
			    List<String> list = new ArrayList<>();
			    list.add("/img/" + i + "_1.jpg");
			    listOfLists.add(list);
			}
			for (int i = 0; i < 12; i++) {
				Article article = new Article();
				article.setArticleName(names[i]);
				article.setDescription(descriptions[i]);
				article.setCategory(categories[i]);
				article.setOwner(owners[i]);
				article.setArticleTextOne(paragraphs[i]);
				article.setArticleTextTwo(paragraphs[i]);
				article.setArticleTextThree(paragraphs[i]);
				article.setArticleTextFour(paragraphs[i]);
				article.setPhotos(listOfLists.get(i).get(0));;
				articles.add(article);
			}

			articleRepository.saveAll(articles);

			
		}
		
		
		
	}

}