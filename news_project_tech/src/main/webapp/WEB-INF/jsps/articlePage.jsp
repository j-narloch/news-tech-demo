<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="/css/style.css">
  <title>Document</title>
</head>
<body background="${article.photos[0]}">
  <div class="wrapper">
    <header class="header">
     <%@ include file="headerBar.jsp" %>
    </header>
    <main class="main">
       <div class="main__article--container">
        <div class="main__title-article">${article.articleName}</div>
        <div class="article__holder">
	        <div class="article">
	         <p class="main__subtitle-article">Rating: ${articleRating}</p>
	        <div class="main__article--text">
	          <p class="text--one">${article.articleTextOne}</p>
	          <p class="text--two">${article.articleTextTwo}</p>
	          <p class="text--three">${article.articleTextThree}</p>
	          <p class="text--four">${article.articleTextFour}</p>
	        </div>
	            <div class="product-button-row-two">
	              <div class="main__rate-article">Rate the article:</div>
	                <div class="main__rate-article--input" id="rate">
	                
	                  <form action="/rate/${id}" method="post" class="rate-form">                   
	                    <label for="value"></label><br>
						  <input 
						  	id="value" 
						  	class="input_rate"
						  	type="number" 
						  	name="articleValue"
						  	min="0"
						  	max="5"
							placeholder="Place article rating here"
							required
						  >
	                    <input type="submit" value="Rate" class="rate-button">
	                  </form>  
	                </div>
	            </div>
	        </div> 
	         <h2>Comments</h2>
    <c:forEach items="${comments}" var="comment">
        <p>${comment.user.username}: ${comment.text}</p>
    </c:forEach>
    <h2>Add Comment</h2>
    <form action="/articlePage/{id}" method="post">
        <input type="hidden" name="articleId" value="${article.id}">
        <textarea name="text"></textarea>
        <button type="submit">Submit</button>
    </form>
	        <div class="related__articles">
	           <p class="main__rate-titleRat">Other articles in the ${category} category:</p>
			        <div class="main__rate-container">
			          <c:forEach var="article" items="${resultsOfSearchCat}" varStatus="status">
			          <div class="main__rate-item">
			            <img class="main__rate-item--img" src="${article.photos[0]}" alt="">
			            <div class="main__rate-item--description">
			              <div class="main__rate-item--container">
			                <p class="main__rate-item--title">${article.articleName}</p>
			                <p class="main__rate-item--rating">Rating: ${article.averageRating}</p>
			                <p class="main__rate-item--text">${article.description}</p>
			              </div>
			              <div class="main__rate-item--button">
			                <a href="/articlePage/${article.id}">Read more...</a>
			              </div>
			            </div>
			          </div>
			          </c:forEach>
			        </div>
	        </div>
        </div> 
      </div>
    </main>
    <footer class="footer">
    <%@ include file="footerBar.jsp" %>
    </footer>
  </div>
</body>
</html>