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
      <div class="main__title">Register</div>
      <div class="main__login-container">
      <div class="main__login">
        <form action="" method="post" class="form">
          <label for="username">Create username:</label><br>
          <input 
            type="text" 
            id="username" 
            name="username"
            class="second"
            placeholder="Username"
          ><br>
          <label for="password">Create password:</label><br>
          <input 
            type="password" 
            id="password" 
            name="password"
            class="second"
            placeholder="Password"
          ><br>
          <label for="confrimPassword">Confirm password:</label><br>
          <input 
            type="password"
            id="confirmPassword"
            name="confirmPassword"
            class="second"
            placeholder="Confirm password"
          ><br>
          <label for="email">Enter your email:</label><br>
          <input 
            type="email" 
            id="email" 
            name="email"
            class="second"
            placeholder="Email"
          ><br>
          <label for="email">Enter your phone number:</label><br>
          <input 
            type="text" 
            id="phoneNumber" 
            name="phoneNumber"
            class="second"
            placeholder="Phone number"
          ><br>
          <label for="firstName">First name:</label><br>
          <input 
          type="text"
          id="firstName" 
          name="firstName" 
          class="second"
          placeholder="Name"
          ><br>
          <label for="surName">Last name:</label><br>
          <input
          id="surName" 
          class="second"
          type="text"
          name="surName" 
          placeholder="Surname"
          ><br>
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