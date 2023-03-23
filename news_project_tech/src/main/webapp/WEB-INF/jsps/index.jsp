<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="css/style.css">
  <title>News</title>
</head>
<body>
  <div class="wrapper">
    <header class="header">
     <%@ include file="headerBar.jsp" %>
    </header>
    <main class="main--index">
      <div class="main__collage">
        <div class="main__collage-title">
          <c:if test="${not empty hotNews}">
            <div>${hotNews}</div>
          </c:if>
        </div>
            <div class="main__news--container"> 
              <c:forEach var="article" items="${filteredArticles}" varStatus="status">
              <div class="main__news-item">
                <div class="main__rate-item--description">
                <img class="main__news-item--img--last" src="${article.photos[0]}" alt="">
                  <div class="main__rate-item--container">
                    <p class="main__rate-item--title">${article.articleName}</p>
                    <p class="main__rate-item--rating">Rating: ${article.averageRating}</p>
                    <p class="main__rate-item--text">${article.description}</p>
                  </div>
                </div>
                <div class="main__rate-item--button">
                    <a href="/articlePage/${article.id}">Read more...</a>
                </div>
              </div>
              </c:forEach>
             </div>
        </div> 
      <div class="main__rate">
        <p class="main__rate-title">We need your opinion!</p>
        <p class="main__rate-subtitle">Rate these articles and let us know if they are worth your time.</p>
        <div class="main__rate-container">
          <c:forEach var="article" items="${listOfArticles}" varStatus="status">
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
    </main>
    <footer class="footer">
    <%@ include file="footerBar.jsp" %>
    </footer>
  </div>
</body>
</html>