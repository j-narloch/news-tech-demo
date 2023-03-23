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
      <div class="main__title">Edit your profile</div>
      <div class="main__login-container">
      <div class="main__login">
        <form action="/editProfile" method="post" class="form">
          <label for="email">Email:</label><br>
          <input 
            type="email" 
            id="email" 
            name="email"
            class="second"
            value="${user.email}"
          ><br>
          <label for="email">Phone number:</label><br>
          <input 
            type="text" 
            id="phoneNumber" 
            name="phoneNumber"
            class="second"
            value="${user.phoneNumber}"
          ><br>
          <label for="firstName">First name:</label><br>
          <input 
          type="text"
          id="firstName" 
          name="firstName" 
          class="second"
          value="${user.firstName}"
          ><br>
          <label for="surName">Last name:</label><br>
          <input
          id="surName" 
          class="second"
          type="text"
          name="surName" 
          value="${user.surName}"
          ><br>
          <div class="main__login-button">
            <input type="submit" value="Update">
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