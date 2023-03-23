<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="/css/style.css">
  <title>Document</title>
</head>
<body>
  <div class="wrapper">
    <header class="header">
     <%@ include file="headerBar.jsp" %>
    </header>
    <main class="main">
      <div class="main__title">Your profile</div>
      <div class="main__user-container">
        <p class="about__us">Username: ${userName}</p>
        <p class="about__us">Email: ${userEmail}</p>
        <p class="about__us">Phone number: ${userPhone}</p>
        <p class="about__us">First name: ${userFirstName}</p>
        <p class="about__us">Last name: ${userSurName}</p>
      </div>
      <div class="settings__button">
        <a href="/editProfile">Edit your profile</a>
        <a href="/logout">Log out</a>
        <a href="/addArticle">Add new article</a>
      </div> 
    </main>
    <footer class="footer">
    <%@ include file="footerBar.jsp" %>
    </footer>
  </div>
</body>
</html>