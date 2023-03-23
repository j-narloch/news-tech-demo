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
      <div class="main__title">Login</div>
      <div class="main__login-container">
      <p class="warning">${message2}</p>
      <div class="main__login">
        <form action="/login" method="post" class="form">
          <label for="username">Username:</label><br>
          <input 
            type="text" 
            id="username" 
            name="username"
            class="second"
            placeholder="type here..."
          ><br>
          <label for="password">Password:</label><br>
          <input 
            type="password" 
            id="password" 
            name="password"
            class="second"
            placeholder="type here..."
          >
          <div class="main__login-button">
            <input type="submit" value="Log in">
          </div>
        </form>
      </div>
      </div>
    </main>
    <footer class="footer">
    <%@ include file="footerBar.jsp" %>
    </footer>
  </div>
</body>
</html>